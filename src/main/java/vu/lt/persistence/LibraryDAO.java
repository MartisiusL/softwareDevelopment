package vu.lt.persistence;

import vu.lt.entities.Library;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class LibraryDAO {

    @Inject
    private EntityManager em;

    public List<Library> loadAll() {
        return em.createNamedQuery("Library.findAll", Library.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Library library){
        this.em.persist(library);
    }

    public Library findOne(Integer id) {
        return em.find(Library.class, id);
    }
}
