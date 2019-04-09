package vu.lt.persistence;

import vu.lt.entities.Author;
import vu.lt.entities.Book;
import vu.lt.entities.Library;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class AuthorDAO {

    @Inject
    private EntityManager em;

    public void persist(Author author){
        this.em.persist(author);
    }

    public List<Author> getAllAuthors() {
        return em.createNamedQuery("Author.findAll", Author.class).getResultList();
    }

    public Author getAuthorByName(String name) {
        List<Author> authors = em.createQuery(
                "SELECT e FROM Author e "+
                        "WHERE e.name LIKE :authName")
                .setParameter("authName", name).getResultList();
        if(authors.size() != 0) {
            return (Author) authors.get(0);
        } else {
            return null;
        }
    }

    public Author findOne(Integer id) {
        return em.find(Author.class, id);
    }
}
