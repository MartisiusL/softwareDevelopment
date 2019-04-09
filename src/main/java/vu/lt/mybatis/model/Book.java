package vu.lt.mybatis.model;

import java.util.List;

public class Book {

    private Integer id;
    private String name;
    private Integer libraryId;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getLibraryId() {
        return libraryId;
    }
    public void setLibraryId(Integer libraryId) {
        this.libraryId = libraryId;
    }


    private List<Author> authors;

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}