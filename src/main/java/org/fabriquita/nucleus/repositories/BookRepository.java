package org.fabriquita.nucleus.repositories;

import org.fabriquita.nucleus.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    public Page<Book> findAll(Pageable pageable);

}
