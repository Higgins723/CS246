package com.example.beesa.firstapp;

/**
 * Created by beesa on 10/16/2016.
 */
import java.io.Serializable;

public class Scripture implements Serializable {
    private static final long serialVersionUID = 1L;

    private String bookName;
    private String chapterName;
    private String verseName;

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public void setVerseName(String verseName) {
        this.verseName = verseName;
    }

    public String getBookName() {
        return bookName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public String getVerseName() {
        return verseName;
    }

    @Override
    public String toString(){
        return "Your favorite scripture is: " + bookName + " " + chapterName + ":" + verseName;
    }
}
