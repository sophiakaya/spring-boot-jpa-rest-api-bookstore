package com.demo.bookstore.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;

import com.demo.bookstore.dto.ObjectDto;
import com.demo.bookstore.repository.ObjectEntity;

public class DtoMapper {

	public static List<? extends ObjectDto> convertToDtos(List<? extends ObjectEntity> objectEntities) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

		List<ObjectDto> objectDtos = modelMapper.map(objectEntities, new TypeToken<List<ObjectEntity>>() {
		}.getType());

		return objectDtos;

	}

}
