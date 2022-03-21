package com.encore.AI_Posturecoaching.board;


import com.encore.AI_Posturecoaching.board.dto.BoardUpdateRequestDto;
import com.encore.AI_Posturecoaching.category.Category;
import com.encore.AI_Posturecoaching.member.Member;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "board_title")
    private String title;

    @Column(name = "board_content")
    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    @Column(name = "board_hit")
    private int hit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    @OneToMany(mappedBy = "board", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Image> images;

    public Board(String title, String content,Member member, Category category, List<Image> images) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.category = category;
        this.images = new ArrayList<>();
        addImages(images);
    }

    public ImageUpdatedResult update(BoardUpdateRequestDto boardUpdateRequestDto) {
        this.title = boardUpdateRequestDto.getTitle();
        this.content = boardUpdateRequestDto.getContent();
        ImageUpdatedResult result = findImageUpdatedResult(boardUpdateRequestDto.getAddedImages(), boardUpdateRequestDto.getDeletedImages());
        addImages(result.getAddedImages());
        deleteImages(result.getDeletedImages());
        return result;
    }

    private void addImages(List<Image> added) {
        added.stream().forEach(i -> {
            images.add(i);
            i.initPost(this);
        });
    }

    private void deleteImages(List<Image> deleted) {
        deleted.stream().forEach(di -> this.images.remove(di));
    }

    private ImageUpdatedResult findImageUpdatedResult(List<MultipartFile> addedImageFiles, List<Long> deletedImageIds) {
        List<Image> addedImages = convertImageFilesToImages(addedImageFiles);
        List<Image> deletedImages = convertImageIdsToImages(deletedImageIds);
        return new ImageUpdatedResult(addedImageFiles, addedImages, deletedImages);
    }

    private List<Image> convertImageIdsToImages(List<Long> imageIds) {
        return imageIds.stream()
                .map(id -> convertImageIdToImage(id))
                .filter(i -> i.isPresent())
                .map(i -> i.get())
                .collect(toList());
    }

    private Optional<Image> convertImageIdToImage(Long id) {
        return this.images.stream().filter(i -> i.getId().equals(id)).findAny();
    }

    private List<Image> convertImageFilesToImages(List<MultipartFile> imageFiles) {
        return imageFiles.stream().map(imageFile -> new Image(imageFile.getOriginalFilename())).collect(toList());
    }

    @Getter
    @AllArgsConstructor
    public static class ImageUpdatedResult {
        private List<MultipartFile> addedImageFiles;
        private List<Image> addedImages;
        private List<Image> deletedImages;
    }

//    //좋아요
//    @OneToMany(mappedBy = "board" )
//    private List<Like> likeList = new ArrayList<>();

//    //중고 상품 등록 파일
//    @OneToMany(mappedBy = "board")
//    private List<File> files = new ArrayList<>();

    
}
