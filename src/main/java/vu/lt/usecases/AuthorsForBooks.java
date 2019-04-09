package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Author;
import vu.lt.entities.Book;
import vu.lt.persistence.AuthorDAO;
import vu.lt.persistence.BooksDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class AuthorsForBooks implements Serializable {

    @Inject
    BooksDAO booksDAO;

    @Inject
    AuthorDAO authorDAO;

    @Getter @Setter
    private Book book;

    @Getter @Setter
    private Author authorToAdd = new Author();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer bookId = Integer.parseInt(requestParameters.get("bookId"));
        this.book = booksDAO.findOne(bookId);
    }

    @Transactional
    public String addAuhtorForBook() {
        Author existingAuthor = authorDAO.getAuthorByName(authorToAdd.getName());

        if(existingAuthor != null) {
            book.getAuthorList().add(existingAuthor);
        } else {
            authorDAO.persist(authorToAdd);
            book.getAuthorList().add(authorToAdd);
        }

        return "authors?faces-redirect=true&bookId=" + this.book.getId();
    }
}
