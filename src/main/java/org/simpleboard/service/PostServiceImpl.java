package org.simpleboard.service;

import java.util.function.Function;

import org.simpleboard.dto.PageRequestDTO;
import org.simpleboard.dto.PageResultDTO;
import org.simpleboard.dto.PostDTO;
import org.simpleboard.entity.Post;
import org.simpleboard.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
	private final PostRepository repositroy;

	@Override
	public PostDTO register(PostDTO dto) {
		log.info("post registered");
		Post entity = dtoToEntity(dto);
		repositroy.save(entity);
		// return entity.getPno();
		return dto;
	}

	@Override
	public PageResultDTO<PostDTO, Post> getList(PageRequestDTO requestDTO) {
		Pageable pageable = requestDTO.getPageable(Sort.by("pno").descending());
		Page<Post> result = repositroy.findAll(pageable);
		Function<Post, PostDTO> fn = (entity -> entityToDto(entity));

		return new PageResultDTO<>(result, fn);
	}

	@Override
	public Long getTotalPage(PageRequestDTO requestDTO) {
		Pageable pageable = requestDTO.getPageable(Sort.by("pno").descending());
		return Long.valueOf((long) pageable.getPageSize());
	}
}
