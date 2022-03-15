package com.encore.AI_Postureoaching.board.service;


import com.encore.AI_Postureoaching.board.Board;
import com.encore.AI_Postureoaching.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    //글 저장하기
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
    }

}
