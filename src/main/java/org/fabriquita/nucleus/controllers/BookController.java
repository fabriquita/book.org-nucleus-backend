package org.fabriquita.nucleus.controllers;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.fabriquita.nucleus.NucleusConstants;
import org.fabriquita.nucleus.models.Book;
import org.fabriquita.nucleus.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    BookService bookService;

    @RequiresAuthentication
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Book> list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = NucleusConstants.PAGE_SIZE;
        }
        Page<Book> bookPage = bookService.list(page, size);
        bookPage.getSize();
        return bookService.list(page, size);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Book get(@PathVariable(value = "id") Long id) {
        return bookService.get(id);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Book add(@RequestBody Map<String, Object> data) {
        String title = null;
        String author = null;
        String collection = null;
        String content = null;
        if (data.get("title") != null) {
            title = (String) data.get("title");
        }
        if (data.get("author") != null) {
            author = (String) data.get("author");
        }
        if (data.get("collection") != null) {
            collection = (String) data.get("collection");
        }
        if (data.get("content") != null) {
            content = (String) data.get("content");
        }
        return bookService.add(title, author, collection, content);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Book update(@PathVariable(value = "id") Long id,
            @RequestBody Map<String, Object> data) {
        String title = null;
        String author = null;
        String collection = null;
        String content = null;
        if (data.get("title") != null) {
            title = (String) data.get("title");
        }
        if (data.get("author") != null) {
            author = (String) data.get("author");
        }
        if (data.get("collection") != null) {
            collection = (String) data.get("collection");
        }
        if (data.get("content") != null) {
            content = (String) data.get("content");
        }
        return bookService.update(id, title, author, collection, content);
    }

    @RequiresAuthentication
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Long id) {
        bookService.delete(id);
    }
}
