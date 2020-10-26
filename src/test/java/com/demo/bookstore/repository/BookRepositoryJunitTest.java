package com.demo.bookstore.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.bookstore.repository.AuthorEntity;
import com.demo.bookstore.repository.AuthorRepository;
import com.demo.bookstore.repository.BookEntity;
import com.demo.bookstore.repository.BookRepository;

/**
 * 
 * Test {@link BookRepository} with JUnit
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class BookRepositoryJunitTest {

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	BookRepository bookRepository;

	@Test
	public void findByIdTest() {

		Optional<BookEntity> bookEntity = bookRepository.findById(1L);
		assertEquals("Les Miserables", bookEntity.get().getTitle());
	}

	@Test
	@DirtiesContext
	public void deleteByIdTest() {

		bookRepository.deleteById(2L);
		assertTrue(bookRepository.findById(2L).isEmpty());
	}

	@Test
	@DirtiesContext
	public void saveTest() {

		Optional<AuthorEntity> authorEntity = authorRepository.findById(4L);

		BookEntity bookEntity = bookRepository
				.save(new BookEntity("Les vieux fourneaux 2", "French Comic book", authorEntity.get()));

		assertEquals("Les vieux fourneaux 2", bookEntity.getTitle());
	}

	@Test
	@DirtiesContext
	public void updateTest() {

		Optional<BookEntity> bookEntity = bookRepository.findById(4L);
		bookEntity.get().setTitle("Les vieux fourneaux 3");
		BookEntity bookEntityUpdated = bookRepository.save(bookEntity.get());
		assertEquals("Les vieux fourneaux 3", bookEntityUpdated.getTitle());
	}

	/**
	 * Test {@link BookRepository#findByAuthor(AuthorEntity)}
	 */
	@Test
	public void findByAuthor() {
		Optional<AuthorEntity> authorEntity = authorRepository.findById(4L);
		List<BookEntity> bookEntities = bookRepository.findByAuthor(authorEntity.get());
		assertEquals(1, bookEntities.size());
	}
}
