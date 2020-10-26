package com.demo.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.bookstore.dto.BookDto;
import com.demo.bookstore.mapper.DtoMapper;
import com.demo.bookstore.repository.AuthorEntity;
import com.demo.bookstore.repository.AuthorRepository;
import com.demo.bookstore.repository.BookEntity;
import com.demo.bookstore.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	BookRepository bookRepository;

	AuthorRepository authorRepository;

	@Autowired
	public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}

	/**
	 * See {@inheritDoc}
	 */
	@Override
	public List<BookDto> getBooks(int page, int limit) {

		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<BookEntity> entityPages = bookRepository.findAll(pageableRequest);

		List<BookDto> bookDtos = (List<BookDto>) DtoMapper.convertToDtos(entityPages.getContent());

		return bookDtos;
	}

	/**
	 * See {@inheritDoc}
	 */
	@Override
	public BookDto getBookById(long id) {

		Optional<BookEntity> bookEntity = bookRepository.findById(id);

		if (bookEntity.isEmpty())
			return null;

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

		BookDto bookDto = modelMapper.map(bookEntity.get(), BookDto.class);

		return bookDto;
	}

	/**
	 * See {@inheritDoc}
	 */
	@Override
	public BookDto createBook(BookDto bookDto) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

		BookEntity bookEntity = modelMapper.map(bookDto, BookEntity.class);

		bookRepository.save(bookEntity);

		BookDto bookDtoCreated = modelMapper.map(bookEntity, BookDto.class);

		return bookDtoCreated;
	}

	/**
	 * See {@inheritDoc}
	 */
	@Override
	public List<BookDto> findByAuthor(long authorId) {

		Optional<AuthorEntity> authorEntity = authorRepository.findById(authorId);

		List<BookEntity> bookEntities = bookRepository.findByAuthor(authorEntity.get());

		List<BookDto> bookDtos = (List<BookDto>) DtoMapper.convertToDtos(bookEntities);

		return bookDtos;
	}

}
