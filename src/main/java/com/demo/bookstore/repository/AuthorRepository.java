package com.demo.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository
		extends CrudRepository<AuthorEntity, Long>, PagingAndSortingRepository<AuthorEntity, Long> {

	List<AuthorEntity> findByFirstName(String firstName);
}
