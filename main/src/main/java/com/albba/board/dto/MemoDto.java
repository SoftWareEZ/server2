package com.albba.board.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sun.util.resources.cldr.ext.LocaleNames_es_GT;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemoDto {
    //어차피 게시판 권한 사장밖에 없으니깐?

    //제목
    private String title;
    //내용
    private String contents;
    //storeid
    private Long storeId;


}
