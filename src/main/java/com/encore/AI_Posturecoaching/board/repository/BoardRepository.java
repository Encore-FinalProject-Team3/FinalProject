package com.encore.AI_Posturecoaching.board.repository;


import com.encore.AI_Posturecoaching.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> ,CustomBoardRepository{
    @Query("select p from Board p join fetch p.member where p.id = :id")
    Optional<Board> findByIdWithMember(@Param("id") Long id);

    @Query("select b from Board b join fetch b.category c where c.id = :id")
    List<Board> findAllByCategoryId(@Param("id") Long id);

    @Query("select b from Board b join fetch b.member m where m.id = :id")
    List<Board> findAllByMemberId(@Param("id") Long id);
}
