package org.fabriquita.nucleus.services;

import java.util.List;

import org.fabriquita.nucleus.models.Book;
import org.fabriquita.nucleus.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> list() {
        return Lists.newLinkedList(bookRepository.findAll());
    }

    public Book get(Long id) {
        return bookRepository.findOne(id);
    }

    public Book add(String title, String author, String collection, String content) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setCollection(collection);
        book.setContent(content);
        return bookRepository.save(book);
    }

    public Book update(Long id, String title, String author, String collection, String content) {
        Book book = bookRepository.findOne(id);
        if(title != null) {
            book.setTitle(title);
        }
        if(title != null) {
            book.setTitle(title);
        }
        if(author != null) {
            book.setAuthor(author);
        }
        if(collection != null) {
            book.setCollection(collection);
        }
        if(content != null) {
            book.setContent(content);
        }
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        Book book = bookRepository.findOne(id);
        bookRepository.delete(book);
    }

    public Page<Book> list(Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);
        return bookRepository.findAll(pageRequest);
    }

}
