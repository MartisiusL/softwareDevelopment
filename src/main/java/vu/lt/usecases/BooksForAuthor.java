package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Author;
import vu.lt.entities.Book;
import vu.lt.entities.Library;
import vu.lt.persistence.AuthorDAO;
import vu.lt.persistence.BooksDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

@Model
public class BooksForAuthor implements Serializable {

    @Inject
    BooksDAO booksDAO;

    @Inject
    AuthorDAO authorDAO;

    @Getter
    @Setter
    private Author author;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer authorId = Integer.parseInt(requestParameters.get("authorId"));
        this.author = authorDAO.findOne(authorId);
    }
}
