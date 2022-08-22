package org.example.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Chapter {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long bookid;

    private String name;

    private Long contentid;

    private String chaptertype;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookid() {
        return bookid;
    }

    public void setBookid(Long bookid) {
        this.bookid = bookid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getContentid() {
        return contentid;
    }

    public void setContentid(Long contentid) {
        this.contentid = contentid;
    }

    public String getChaptertype() {
        return chaptertype;
    }

    public void setChaptertype(String chaptertype) {
        this.chaptertype = chaptertype;
    }
}