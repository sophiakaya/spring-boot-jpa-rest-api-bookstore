package com.demo.bookstore.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class BookEntity extends ObjectEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String title;

	@Column(nullable = false, length = 250)
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "author_id")
	private AuthorEntity author;

	public BookEntity() {
	}

	public BookEntity(String title, String description, AuthorEntity author) {
		super();
		this.title = title;
		this.description = description;
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AuthorEntity getAuthor() {
		return author;
	}

	public void setAuthor(AuthorEntity author) {
		this.author = author;
	}

}
