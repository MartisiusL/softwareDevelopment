package vu.lt.mybatis.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.mybatis.dao.LibraryMapper;
import vu.lt.mybatis.model.Library;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class LibrariesMyBatis {
    @Inject
    private LibraryMapper libraryMapper;

    @Getter
    private List<Library> allLibraries;

    @Getter @Setter
    private Library libraryToCreate = new Library();

    @PostConstruct
    public void init() {
        this.loadAllLibraries();
    }

    private void loadAllLibraries() {
        this.allLibraries = libraryMapper.selectAll();
    }

    @Transactional
    public String createLibrary() {
        libraryMapper.insert(libraryToCreate);
        return "/myBatis/libraries?faces-redirect=true";
    }
}
