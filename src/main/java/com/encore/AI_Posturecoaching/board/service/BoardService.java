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
    @Transactional
    public List<BoardDto> readAll() {
        List<BoardDto> boardList = boardRepository.findAll().stream().map(b -> BoardDto.toDto(b)).collect(toList());
        return boardList;
    }

    // 게시글 조회
    @Transactional
    public BoardDto read(Long id) {
        return BoardDto.toDto(boardRepository.findById(id).orElseThrow(PostNotFoundException::new));
    }

    // 카테고리별 게시글 조회
    @Transactional
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
        System.out.println(memberId + "," + id);
        System.out.println(Long.valueOf(memberId));

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



    /*// 게시글 생성
    public List<Board> create(final Board entity) {
        // Validations
        validate(entity);

        boardRepository.save(entity);
        log.info("Entity Id : {} is saved.", entity.getId());
        return boardRepository.findByMemberId(entity.getMemberId());
    }

    // 게시글 존재하는지 검증
    private void validate(final Board entity) {
        if(entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if(entity.getMemberId() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }

    // 게시글 검색
    public List<Board> retrieve(final String userId) {
        return boardRepository.findByMemberId(userId);
    }

    // 게시글 수정
    public List<Board> update(final Board entity) {
        // (1) 저장 할 엔티티가 유효한지 확인한다. 이 메서드는 2.3.1 Create Todo에서 구현했다.
        validate(entity);

        // (2) 넘겨받은 엔티티 id를 이용해 TodoEntity를 가져온다. 존재하지 않는 엔티티는 업데이트 할 수 없기 때문이다.
        final Optional<Board> original = boardRepository.findById(entity.getId());



        original.ifPresent(todo -> {
            // (3) 반환된 TodoEntity가 존재하면 값을 새 entity의 값으로 덮어 씌운다.
            todo.setTitle(entity.getTitle());
//            todo.setDone(entity.isDone());

            // (4) 데이터베이스에 새 값을 저장한다.
            boardRepository.save(todo);
        });

        // 2.3.2 Retrieve Todo에서 만든 메서드를 이용해 유저의 모든 Todo 리스트를 리턴한다.
        return retrieve(entity.getMemberId());
    }

    // 게시글 삭제
    public List<Board> delete(final Board entity) {
        // (1) 저장 할 엔티티가 유효한지 확인한다. 이 메서드는 2.3.1 Create Todo에서 구현했다.
        validate(entity);

        try {
            // (2) 엔티티를 삭제한다.
            boardRepository.delete(entity);
        } catch(Exception e) {
            // (3) exception 발생시 id와 exception을 로깅한다.
            log.error("error deleting entity ", entity.getId(), e);

            // (4) 컨트롤러로 exception을 날린다. 데이터베이스 내부 로직을 캡슐화 하기 위해 e를 리턴하지 않고 새 exception 오브젝트를 리턴한다.
            throw new RuntimeException("error deleting entity " + entity.getId());
        }
        // (5) 새 Todo리스트를 가져와 리턴한다.
        return retrieve(entity.getMemberId());
    }*/









    /*//글 저장하기
    @Transactional // 트렌젝션 쓰는이유 commit 할지 롤백 할지 씀
    public Board save(Board board){
        return boardRepository.save(board);
    }
    //게시물 상세보기
    @Transactional(readOnly = true) //오직 읽기만 가능
    public Board findById(Long id){
        return boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("로그인 해주세요!"));
    }
    //게시물 전부 다보기
    public List<Board> findAll(){
        return boardRepository.findAll();
    }
    //쓴글 수정하기 더티체킹  영속화
    public Board update(Long id , Board board){
        Board boardEntity = boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("로그인 해주세요 !"));
        boardEntity.setBoardTitle(board.getBoardTitle());
        boardEntity.setBoardContent(board.getBoardContent());
        return boardEntity;

    }
    //글 삭제하기
    public String delete(Long id){
        boardRepository.deleteById(id);
        return "ok";
    }*/

}
