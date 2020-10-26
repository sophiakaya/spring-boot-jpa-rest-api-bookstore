package com.demo.bookstore.repository;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "author")
public class AuthorEntity extends ObjectEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String firstName;

	@Column(nullable = false, length = 50)
	private String lastName;

	@JsonIgnore
	@OneToMany(mappedBy = "author", fetch = FetchType.LAZY, orphanRemoval = true)
	@Column
	private Set<BookEntity> books = new HashSet<>();

	public AuthorEntity() {
	}

	public AuthorEntity(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<BookEntity> getBooks() {
		return books;
	}

	public void addBook(BookEntity book) {
		this.books.add(book);
	}

	public void removeBook(BookEntity book) {
		this.books.remove(book);
	}
}
