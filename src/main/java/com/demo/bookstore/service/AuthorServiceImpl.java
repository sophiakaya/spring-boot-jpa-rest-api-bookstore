package com.demo.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.bookstore.dto.AuthorDto;
import com.demo.bookstore.mapper.DtoMapper;
import com.demo.bookstore.repository.AuthorEntity;
import com.demo.bookstore.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

	AuthorRepository authorRepository;

	@Autowired
	public AuthorServiceImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	/**
	 * See {@inheritDoc}
	 */
	@Override
	public List<AuthorDto> getAuthors(Pageable pageable) {

		Page<AuthorEntity> entityPages = authorRepository.findAll(pageable);

		List<AuthorDto> authorDtos = (List<AuthorDto>) DtoMapper.convertToDtos(entityPages.getContent());

		return authorDtos;
	}

	/**
	 * See {@inheritDoc}
	 */
	@Override
	public List<AuthorDto> getAuthors() {

		List<AuthorEntity> entities = (List<AuthorEntity>) authorRepository.findAll();

		List<AuthorDto> authorDtos = (List<AuthorDto>) DtoMapper.convertToDtos(entities);

		return authorDtos;
	}

	/**
	 * See {@inheritDoc}
	 */
	@Override
	public AuthorDto getAuthorById(long id) {

		Optional<AuthorEntity> authorEntity = authorRepository.findById(id);

		if (authorEntity.isEmpty())
			return null;

		AuthorDto authorDto = new ModelMapper().map(authorEntity.get(), AuthorDto.class);

		return authorDto;

	}

	/**
	 * See {@inheritDoc}
	 */
	@Override
	public AuthorDto createAuthor(AuthorDto authorDto) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

		AuthorEntity authorEntity = modelMapper.map(authorDto, AuthorEntity.class);

		authorRepository.save(authorEntity);

		AuthorDto authorDtoCreated = modelMapper.map(authorEntity, AuthorDto.class);

		return authorDtoCreated;

	}

	/**
	 * See {@inheritDoc}
	 */
	@Override
	public List<AuthorDto> findByFirstName(String firstName) {

		List<AuthorEntity> entities = (List<AuthorEntity>) authorRepository.findByFirstName(firstName);

		List<AuthorDto> authorDtos = (List<AuthorDto>) DtoMapper.convertToDtos(entities);

		return authorDtos;
	}

}
