package com.demo.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.bookstore.repository.AuthorEntity;
import com.demo.bookstore.repository.AuthorRepository;
import com.demo.bookstore.repository.BookEntity;
import com.demo.bookstore.repository.BookRepository;

@SpringBootApplication
public class SpringBootJpaRestApiBookstoreApplication implements CommandLineRunner {

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	BookRepository bookRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaRestApiBookstoreApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		AuthorEntity author1 = authorRepository.save(new AuthorEntity("Victor", "Hugo"));
		AuthorEntity author2 = authorRepository.save(new AuthorEntity("Albert", "Camus"));
		AuthorEntity author3 = authorRepository.save(new AuthorEntity("Moliere", "Jean-Baptiste Poquelin"));
		AuthorEntity author4 = authorRepository.save(new AuthorEntity("Lupano", "Wilfrid"));

		bookRepository.save(new BookEntity("Les Miserables", "French historical novel", author1));
		bookRepository.save(new BookEntity("La Peste", "Psychological fiction novel", author2));
		bookRepository.save(new BookEntity("Le malade imaginaire", "Comédie-ballet", author3));
		bookRepository.save(new BookEntity("Les vieux fourneaux", "French Comic book", author4));

	}


}
