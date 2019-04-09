package vu.lt.persistence;

import vu.lt.entities.Book;
import vu.lt.entities.Library;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class BooksDAO {

    @Inject
    private EntityManager em;

    public List<Book> getAllBooks() {
        return em.createNamedQuery("Book.findAll", Book.class).getResultList();
    }

    public void persist(Book book){
        this.em.persist(book);
    }

    public Book findOne(Integer id) {
        return em.find(Book.class, id);
    }
}
