package com.albba.board.service;


import com.albba.albbaUser.entity.User;
import com.albba.board.dto.MemoDto;
import com.albba.board.model.Memo;
import com.albba.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;


    //게시판 글 쓰기
    public Memo createMemo(MemoDto memoDto)
    {

        Memo memo = new Memo(memoDto);
        boardRepository.save(memo);

        return memo;
    }

    //게시판 글 수정
    public Memo modifyMemo(Long id,MemoDto memoDto)
    {
        Memo memo = boardRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 메모가 존재하지 않습니다."));
        memo.setContents(memoDto.getTitle());
        memo.setTitle(memoDto.getTitle());
        boardRepository.save(memo);
        return memo;
    }

    //저장된 공지 전체 조회
    public List<Memo> getMemos(Long storeId) throws SQLException {
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        List<Memo> memos = boardRepository.findMemosByStoreIdAndModifiedAtBetweenOrderByModifiedAtDesc(storeId,start, end);
        return memos;
    }

    public Memo getMemo(Long memoId) {

        Optional<Memo> found = boardRepository.findById(memoId);
        if(found.isPresent())
            return found.get();

        else
            throw new IllegalArgumentException("해당하는 메모가 없습니다.");


    }

    //게시판 글 삭제
    public void deleteById(Long id)
    {
        boardRepository.deleteById(id);

    }
    //
}