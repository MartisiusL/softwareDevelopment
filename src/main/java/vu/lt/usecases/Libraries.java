package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Library;
import vu.lt.persistence.LibraryDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Libraries {

    @Inject
    private LibraryDAO libraryDAO;

    @Getter @Setter
    private Library libraryToCreate = new Library();

    @Getter
    private List<Library> allLibraries;

    @PostConstruct
    public void init(){
        loadAllLibraries();
    }

    @Transactional
    public String createLibrary(){
        this.libraryDAO.persist(libraryToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllLibraries(){
        this.allLibraries = libraryDAO.loadAll();
    }
}
