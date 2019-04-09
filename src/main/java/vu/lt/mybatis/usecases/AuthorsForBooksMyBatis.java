package vu.lt.mybatis.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.mybatis.dao.AuthorMapper;
import vu.lt.mybatis.dao.BookAuthorMapper;
import vu.lt.mybatis.dao.BookMapper;
import vu.lt.mybatis.dao.LibraryMapper;
import vu.lt.mybatis.model.Author;
import vu.lt.mybatis.model.Book;
import vu.lt.mybatis.model.BookAuthor;
import vu.lt.mybatis.model.Library;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class AuthorsForBooksMyBatis {
    @Inject
    private AuthorMapper authorMapper;

    @Inject
    private BookMapper bookMapper;

    @Inject
    private BookAuthorMapper bookAuthorMapper;

    @Getter
    private List<Author> allAuthors;

    @Getter @Setter
    private Book book;

    @Getter @Setter
    private Author authorToAdd = new Author();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer bookId = Integer.parseInt(requestParameters.get("bookId"));
        this.book = bookMapper.selectByPrimaryKey(bookId);
    }

    @Transactional
    public String createAuthor() {

            authorMapper.insert(authorToAdd);
            BookAuthor bookAuthor = new BookAuthor();
            bookAuthor.setBookId(this.book.getId());
            bookAuthor.setAuthorId(authorToAdd.getId());
            bookAuthorMapper.insert(bookAuthor);

        return "/myBatis/authors?faces-redirect=true&bookId=" + this.book.getId();
    }
}
