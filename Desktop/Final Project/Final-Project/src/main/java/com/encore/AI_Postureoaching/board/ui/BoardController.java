package com.encore.AI_Postureoaching.board.ui;


import com.encore.AI_Postureoaching.board.Board;
import com.encore.AI_Postureoaching.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //쓴글 저장
    @PostMapping("/board")
    public ResponseEntity<?> save(@RequestBody Board board) {
        return new ResponseEntity<>(boardService.save(board), HttpStatus.OK);
    }
    //쓴글 상세
    @GetMapping("/board")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(boardService.findAll(), HttpStatus.OK);
    }
    //쓴글 모두 보기
    @GetMapping("/board/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return new ResponseEntity<>(boardService.find(id), HttpStatus.OK);
    }
    //쓴글 수정하기
    @PutMapping("/board/{id}")
    public ResponseEntity<?> update(@PathVariable Long id ,@RequestBody Board board){
        return new ResponseEntity<>(boardService.update(id,board), HttpStatus.OK);
    }
    //쓴글 삭제하기
    @DeleteMapping("/board/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return new ResponseEntity<>(boardService.delete(id), HttpStatus.OK);
    }

}
