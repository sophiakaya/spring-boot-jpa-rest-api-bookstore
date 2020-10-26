package com.demo.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long>, PagingAndSortingRepository<BookEntity, Long> {

	List<BookEntity> findByAuthor(AuthorEntity authorEntity);
}
