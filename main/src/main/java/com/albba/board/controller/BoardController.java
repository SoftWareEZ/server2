package com.albba.board.controller;


import com.albba.board.dto.MemoDto;
import com.albba.board.model.Memo;
import com.albba.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/albba")
@RestController
public class BoardController {
//ㅇㅇ
    private final BoardService boardService;

    //공지 전체 읽어오기 (개인 + 관리자 모두) storeId기준
    @GetMapping("/board/List/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Memo> getMemos(@PathVariable Long id) throws SQLException {
        return boardService.getMemos(id);
    }


    //특정 공지만 읽어오기
    @GetMapping("/board/View/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Memo getMemo(@PathVariable Long id) throws SQLException {
        return boardService.getMemo(id);
    }


    //공지 수정
    //@Secured(value= UserRoleEnum.Authority.ADMIN)
    @PutMapping("/board/Modify/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Long modifyMemo(@PathVariable Long id, @RequestBody MemoDto memoDto)
    {
        return boardService.modifyMemo(id,memoDto).getId();
    }
    //공지 저장하는거
    //@Secured(value= UserRoleEnum.Authority.ADMIN)
    @PostMapping("/board/Post")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Memo createMemo(@RequestBody MemoDto memoDto)
    {
        return boardService.createMemo(memoDto);
    }


    //공지 삭제하기
    @DeleteMapping("/board/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Long deleteMemo(@PathVariable Long id) {
        boardService.deleteById(id);
        return id;
    }

}
