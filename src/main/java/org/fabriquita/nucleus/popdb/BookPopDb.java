package org.fabriquita.nucleus.popdb;

import org.fabriquita.nucleus.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookPopDb {

    @Autowired
    BookService bookService;

    public void popDB() {
        bookService.add(
                "Introduccion a la Robotica",
                "Juan Ibanez Fernandez", 
                "Tecnologia",
                "Aca vendra el contenido del libro"
                );
        bookService.add(
                "Robotica Avanzada",
                "Juan Ibanez Fernandez", 
                "Tecnologia",
                "Aca vendra el contenido del libro"
                );
    }
}
