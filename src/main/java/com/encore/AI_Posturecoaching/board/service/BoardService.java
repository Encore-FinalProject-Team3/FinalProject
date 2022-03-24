package com.encore.AI_Posturecoaching.board.service;


import com.encore.AI_Posturecoaching.board.Board;
import com.encore.AI_Posturecoaching.board.Image;
import com.encore.AI_Posturecoaching.board.dto.*;
import com.encore.AI_Posturecoaching.board.repository.BoardRepository;
import com.encore.AI_Posturecoaching.category.Category;
import com.encore.AI_Posturecoaching.category.repository.CategoryRepository;
import com.encore.AI_Posturecoaching.exception.CategoryNotFoundException;
import com.encore.AI_Posturecoaching.exception.MemberNotFoundException;
import com.encore.AI_Posturecoaching.exception.PostNotFoundException;
import com.encore.AI_Posturecoaching.file.service.FileService;
import com.encore.AI_Posturecoaching.member.Member;
import com.encore.AI_Posturecoaching.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final FileService fileService;


    // 게시글 전체 조회
    public List<BoardDto> readAll() {
        List<BoardDto> boardList = boardRepository.findAll().stream().map(b -> BoardDto.toDto(b)).collect(toList());
        return boardList;
    }

    // 게시글 조회
    public BoardDto read(Long id) {
        return BoardDto.toDto(boardRepository.findById(id).orElseThrow(PostNotFoundException::new));
    }

    // 카테고리별 게시글 조회
    public List<BoardDto> readAllByCategoryId(Long id){
        List<BoardDto> boardList = boardRepository.findAllByCategoryId(id).stream().map(b -> BoardDto.toDto(b)).collect(toList());
        return boardList;
    }

    // 멤버별 작성 게시글 전체 조회
    @Transactional
    public List<BoardDto> readAllByMemberId(Long id) {
        List <BoardDto> boardList = boardRepository.findAllByMemberId(id).stream().map(b -> BoardDto.toDto(b)).collect(toList());
        return boardList;
    }

    @Transactional
    public BoardCreateResponseDto create(BoardCreateRequestDto boardCreateRequestDto){
        Member member =memberRepository.findById(boardCreateRequestDto.getMemberId()).orElseThrow(MemberNotFoundException::new);
        Category category = categoryRepository.findById(boardCreateRequestDto.getCategoryId()).orElseThrow(CategoryNotFoundException::new);
        List<Image> images = boardCreateRequestDto.getImages().stream().map(i -> new Image(i.getOriginalFilename())).collect(toList());
        Board board = boardRepository.save(
                new Board(boardCreateRequestDto.getTitle(), boardCreateRequestDto.getContent(),member, category, images)
        );
        uploadImages(board.getImages(), boardCreateRequestDto.getImages());
        return new BoardCreateResponseDto(board.getId());
    }

    @Transactional
    public void delete(String memberId, Long id) {

        Member member = memberRepository.findById(Long.valueOf(memberId)).orElseThrow(MemberNotFoundException::new);
        Board board = boardRepository.findById(id).orElseThrow(PostNotFoundException::new);
        if (board.getMember().getId() == Long.valueOf(memberId)|| member.getRole().equals("ADMIN")) {
            deleteImages(board.getImages());
            boardRepository.delete(board);
        } else {
            throw new RuntimeException("게시물 작성자가 다릅니다.");
        }
    }


    @Transactional
    public BoardUpdateResponseDto update(String memberId, Long id, BoardUpdateRequestDto boardUpdateRequest) {

        Member member = memberRepository.findById(Long.valueOf(memberId)).orElseThrow(MemberNotFoundException::new);
        Board board = boardRepository.findById(id).orElseThrow(PostNotFoundException::new);
        if (board.getMember().getId() == Long.valueOf(memberId)|| member.getRole().equals("ADMIN")) {
            Board.ImageUpdatedResult result = board.update(boardUpdateRequest);
            uploadImages(result.getAddedImages(), result.getAddedImageFiles());
            deleteImages(result.getDeletedImages());
        } else {
            throw new RuntimeException("게시물 작성자가 다릅니다.");
        }
        return new BoardUpdateResponseDto(id);
    }


    private void uploadImages(List<Image> images, List<MultipartFile> fileImages) {
        IntStream.range(0, images.size()).forEach(i -> fileService.upload(fileImages.get(i), images.get(i).getUniqueName()));
    }

    private void deleteImages(List<Image> images) {
        images.stream().forEach(i -> fileService.delete(i.getUniqueName()));
    }





}
