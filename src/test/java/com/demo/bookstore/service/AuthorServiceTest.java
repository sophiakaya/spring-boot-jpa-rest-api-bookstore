package com.demo.bookstore.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.bookstore.dto.AuthorDto;
import com.demo.bookstore.service.AuthorService;

/**
 * 
 * Test {@link AuthorService} with Mockito
 *
 */
@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

	@Mock
	AuthorService authorService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test {@link AuthorService#getAuthors()}
	 */
	@Test
	public void getAllAuthorsTest() {

		List<AuthorDto> list = new ArrayList<>();
		list.add(new AuthorDto(null, "Author 1", "Author 1"));
		list.add(new AuthorDto(null, "Author 2", "Author 2"));

		when(authorService.getAuthors()).thenReturn(list);

		List<AuthorDto> dtos = authorService.getAuthors();

		assertEquals(2, dtos.size());
		verify(authorService, times(1)).getAuthors();
	}

	/**
	 * Test {@link AuthorService#getAuthorById(long)}
	 */
	@Test
	public void getAuthorByIdTest() {

		AuthorDto authorDto = new AuthorDto(1L, "Author 1", "Author 1");

		when(authorService.getAuthorById(1L)).thenReturn(authorDto);

		AuthorDto dto = authorService.getAuthorById(1L);

		verify(authorService, times(1)).getAuthorById(1L);
	}

	/**
	 * Test {@link AuthorService#createAuthor(AuthorDto)}
	 */
	@Test
	public void createAuthorTest() {

		AuthorDto authorDto = new AuthorDto(null, "Author 1", "Author 1");
		AuthorDto authorDtoCreated = authorService.createAuthor(authorDto);

		verify(authorService, times(1)).createAuthor(authorDto);
	}

	/**
	 * Test {@link AuthorService#createAuthor(AuthorDto)}
	 */
	@Test
	public void getAuthorByNameTest() {

		List<AuthorDto> list = new ArrayList<>();
		list.add(new AuthorDto(null, "Author", "Author 1"));
		list.add(new AuthorDto(null, "Author", "Author 2"));

		when(authorService.findByFirstName("Author")).thenReturn(list);

		List<AuthorDto> dtos = authorService.findByFirstName("Author");

		assertEquals(2, dtos.size());
		verify(authorService, times(1)).findByFirstName("Author");
	}

}
