package com.demo.bookstore.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.bookstore.repository.AuthorEntity;
import com.demo.bookstore.repository.AuthorRepository;

/**
 * 
 * Test {@link AuthorRepository} with Mockito
 *
 */
@ExtendWith(MockitoExtension.class)
public class AuthorRepositoryMockitoTest {

	@Mock
	AuthorRepository authorRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test {@link AuthorRepository#findAll()}
	 */
	@Test
	public void getAllAuthorsTest() {

		List<AuthorEntity> list = new ArrayList<>();
		list.add(new AuthorEntity("Author 1", "Author 1"));
		list.add(new AuthorEntity("Author 2", "Author 2"));

		when(authorRepository.findAll()).thenReturn(list);

		List<AuthorEntity> entities = (List<AuthorEntity>) authorRepository.findAll();

		assertEquals(2, entities.size());
		verify(authorRepository, times(1)).findAll();
	}

	/**
	 * Test {@link AuthorRepository#findById(Long)}
	 */
	@Test
	public void getAuthorByIdTest() {

		Optional<AuthorEntity> entity = Optional.ofNullable(new AuthorEntity("Author 1", "Author 1"));

		when(authorRepository.findById(1L)).thenReturn(entity);

		Optional<AuthorEntity> result = authorRepository.findById(1L);

		verify(authorRepository, times(1)).findById(1L);
	}

	/**
	 * Test {@link AuthorRepository#save(AuthorEntity)}
	 */
	@Test
	public void createAuthorTest() {

		AuthorEntity entity = new AuthorEntity("Author 1", "Author 1");
		AuthorEntity entityCreated = authorRepository.save(entity);

		verify(authorRepository, times(1)).save(entity);
	}

	/**
	 * Test {@link AuthorRepository#findByFirstName(String)}
	 */
	@Test
	public void getAuthorByNameTest() {

		List<AuthorEntity> list = new ArrayList<>();
		list.add(new AuthorEntity("Author", "Author 1"));
		list.add(new AuthorEntity("Author", "Author 2"));

		when(authorRepository.findByFirstName("Author")).thenReturn(list);

		List<AuthorEntity> entities = authorRepository.findByFirstName("Author");

		assertEquals(2, entities.size());
		verify(authorRepository, times(1)).findByFirstName("Author");
	}

}
