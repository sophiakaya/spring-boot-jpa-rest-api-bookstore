package com.demo.bookstore.model;

import javax.validation.constraints.NotNull;

public class BookRequestModel {

	@NotNull(message = "Title cannot be null")
	private String title;

	@NotNull(message = "Description name cannot be null")
	private String description;

	@NotNull(message = "Author id cannot be null")
	private long authorId;

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

	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}
}
