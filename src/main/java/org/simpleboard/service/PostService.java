package org.simpleboard.service;

import org.simpleboard.dto.PageRequestDTO;
import org.simpleboard.dto.PageResultDTO;
import org.simpleboard.dto.PostDTO;
import org.simpleboard.entity.Post;

public interface PostService {
	PostDTO register(PostDTO dto);

	PageResultDTO<PostDTO, Post> getList(PageRequestDTO requestDTO);
	
	Long getTotalPage(PageRequestDTO requestDTO);

	default Post dtoToEntity(PostDTO dto) {
		Post entity = Post.builder().pno(dto.getPno()).title(dto.getTitle()).content(dto.getContent())
				.writer(dto.getWriter()).build();
		return entity;
	}

	default PostDTO entityToDto(Post entity) {
		PostDTO dto = PostDTO.builder().pno(entity.getPno()).title(entity.getTitle()).content(entity.getContent())
				.writer(entity.getWriter()).modifiedAt(entity.getModifiedAt()).createdAt(entity.getCreatedAt()).build();
		return dto;
	}
}
