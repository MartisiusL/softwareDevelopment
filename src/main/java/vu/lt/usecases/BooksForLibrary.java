package vu.lt.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Book;
import vu.lt.entities.Library;
import vu.lt.persistence.BooksDAO;
import vu.lt.persistence.LibraryDAO;

@Model
public class BooksForLibrary implements Serializable {

    @Inject
    private LibraryDAO libraryDAO;

    @Inject
    private BooksDAO booksDAO;

    @Getter @Setter
    private Library library;

    @Getter @Setter
    private Book bookToCreate = new Book();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer libraryId = Integer.parseInt(requestParameters.get("libraryId"));
        this.library = libraryDAO.findOne(libraryId);
    }

    @Transactional
    public String createBook() {
        bookToCreate.setLibrary(this.library);
        booksDAO.persist(bookToCreate);
        return "books?faces-redirect=true&libraryId=" + this.library.getId();
    }
}
