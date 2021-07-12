package org.simpleboard.service;

import java.util.Optional;
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
	private Long totalPage = 0L;

	@Override
	public PostDTO register(PostDTO dto) {
		log.info("post registered");
		Post entity = dtoToEntity(dto);
		repositroy.save(entity);
		dto.setPno(entity.getPno());
		return dto;
	}

	@Override
	public PageResultDTO<PostDTO, Post> getList(PageRequestDTO requestDTO) {
		Pageable pageable = requestDTO.getPageable(Sort.by("pno").descending());
		Page<Post> result = repositroy.findAll(pageable);
		Function<Post, PostDTO> fn = (entity -> entityToDto(entity));

		totalPage = Long.valueOf(result.getTotalPages());
		return new PageResultDTO<>(result, fn);
	}

	@Override
	public Long getTotalPage() {
		return totalPage;
	}

	@Override
	public PostDTO read(Long pno) {
		Optional<Post> result = repositroy.findById(pno);
		return result.isPresent() ? entityToDto(result.get()) : null;
	}

	@Override
	public void remove(Long pno) {
		log.info("delete post");
		repositroy.deleteById(pno);
	}

	@Override
	public PostDTO modify(PostDTO dto) {
		Optional<Post> result = repositroy.findById(dto.getPno());
		if (result.isPresent()) {
			Post entity = result.get();
			entity.changeContent(dto.getContent());
			entity.changeTitle(dto.getTitle());
			repositroy.save(entity);
			return entityToDto(entity);
		} else {
			return null;
		}
	}
}
