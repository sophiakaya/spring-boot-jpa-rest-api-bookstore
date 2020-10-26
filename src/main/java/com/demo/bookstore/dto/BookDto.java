package com.demo.bookstore.dto;

public class BookDto extends ObjectDto {

	private Long id;
	private String title;
	private String description;
	private AuthorDto author;

	public BookDto() {
	}

	public BookDto(Long id, String title, String description, AuthorDto author) {
		super();
		this.id = id;
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

	public AuthorDto getAuthorDto() {
		return author;
	}

	public void setAuthorDto(AuthorDto authorDto) {
		this.author = authorDto;
	}

}
