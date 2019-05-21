package vu.lt.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BooksDto {

    private String Name;

    private Integer YearsWritten;

    private String LibraryName;
}
