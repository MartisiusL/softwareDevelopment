package vu.lt.mybatis.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.mybatis.dao.BookMapper;
import vu.lt.mybatis.dao.LibraryMapper;
import vu.lt.mybatis.model.Book;
import vu.lt.mybatis.model.Library;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class BooksForLibrariesMyBatis {

    @Inject
    private BookMapper bookMapper;

    @Inject
    private LibraryMapper libraryMapper;

    @Getter
    private List<Book> allBooks;

    @Getter @Setter
    private Library library;

    @Getter @Setter
    private Book bookToCreate = new Book();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer libraryId = Integer.parseInt(requestParameters.get("libraryId"));
        this.library = libraryMapper.selectByPrimaryKey(libraryId);
    }

    @Transactional
    public String creteBook() {
        bookToCreate.setLibraryId(library.getId());
        bookMapper.insert(bookToCreate);
        return "/myBatis/books?faces-redirect=true&libraryId=" + this.library.getId();
    }
}
