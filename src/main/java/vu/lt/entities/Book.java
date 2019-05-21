package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Many;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Book.findAll", query = "select a from Book as a")
})
@Table(name = "BOOK")
@Getter @Setter
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Column(name = "YEARS_WRITTEN")
    private Integer yearsWritten;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    @ManyToOne
    @JoinColumn(name="LIBRARY_ID")
    private Library library;

    public Book() {
    }

    @JoinTable(name = "BOOK_AUTHOR", joinColumns = {
            @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
            @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID")})
    @ManyToMany
    private List<Author> authorList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }


}
