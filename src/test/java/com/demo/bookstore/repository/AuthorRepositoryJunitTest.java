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

/**
 * 
 * Test {@link AuthorRepository} with JUnit
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class AuthorRepositoryJunitTest {

	@Autowired
	AuthorRepository authorRepository;

	@Test
	public void findByIdTest() {

		Optional<AuthorEntity> authorEntity = authorRepository.findById(1L);
		assertEquals("Victor", authorEntity.get().getFirstName());
	}

	@Test
	@DirtiesContext
	public void deleteByIdTest() {

		authorRepository.deleteById(2L);
		assertTrue(authorRepository.findById(2L).isEmpty());
	}

	@Test
	@DirtiesContext
	public void saveTest() {

		AuthorEntity authorEntity = authorRepository.save(new AuthorEntity("Lupano", "Wilfrid"));
		assertEquals("Lupano", authorEntity.getFirstName());
	}

	@Test
	@DirtiesContext
	public void updateTest() {

		Optional<AuthorEntity> authorEntity = authorRepository.findById(1L);
		authorEntity.get().setFirstName("NoName");
		AuthorEntity authorEntityUpdated = authorRepository.save(authorEntity.get());
		assertEquals("NoName", authorEntityUpdated.getFirstName());
	}

	@Test
	public void findByFirsNameTest() {
		List<AuthorEntity> authorEntities = authorRepository.findByFirstName("Victor");
		assertEquals(1, authorEntities.size());
	}

}
