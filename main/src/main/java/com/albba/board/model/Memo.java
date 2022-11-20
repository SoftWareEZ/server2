package com.albba.board.model;

import com.albba.board.dto.MemoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Memo extends Timestamp {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;


    @Column(nullable = false)
    private String title;

    @Column(nullable= false)
    private String contents;

    @Column(nullable = false)
    private Long storeId;

    /*
    @ManyToOne
    @JoinCOlumn(name = "storeId")
    private Store store;
*/


    public Memo(String title, String contents)
    {
        this.title = title;
        this.contents=contents;
    }
    public Memo(MemoDto memoDto)
    {
        this.title = memoDto.getTitle();
        this.contents = memoDto.getContents();
        this.storeId = memoDto.getStoreId();
    }

}
