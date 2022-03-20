package com.encore.AI_Posturecoaching.board.service;


import com.encore.AI_Posturecoaching.board.Board;
import com.encore.AI_Posturecoaching.board.dto.BoardListDto;
import com.encore.AI_Posturecoaching.board.dto.BoardReadCondition;
import com.encore.AI_Posturecoaching.board.repository.BoardRepository;
import com.encore.AI_Posturecoaching.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

//    public BoardListDto readAll(BoardReadCondition cond) {
//        return BoardListDto.toDto(
//                boardRepository.findAllById();
//        );
//    }






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
