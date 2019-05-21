package vu.lt.rest;

import lombok.*;
import vu.lt.entities.Book;
import vu.lt.persistence.BooksDAO;
import vu.lt.rest.contracts.BooksDto;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/books")
public class BooksController {

    @Inject
    @Setter @Getter
    private BooksDAO booksDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Book book = booksDAO.findOne(id);
        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        BooksDto booksDto = new BooksDto();
        booksDto.setName(book.getName());
        booksDto.setYearsWritten(book.getYearsWritten());
        booksDto.setLibraryName(book.getLibrary().getName());

        return Response.ok(booksDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer bookId,
            BooksDto bookData) {
        try {
            Book existingBook = booksDAO.findOne(bookId);
            if (existingBook == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingBook.setName(bookData.getName());
            existingBook.setYearsWritten(bookData.getYearsWritten());
            booksDAO.update(existingBook);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
