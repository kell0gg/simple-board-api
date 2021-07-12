package org.simpleboard.controller;

import java.util.List;

import org.simpleboard.dto.PageRequestDTO;
import org.simpleboard.dto.PageResultDTO;
import org.simpleboard.dto.PostDTO;
import org.simpleboard.entity.Post;
import org.simpleboard.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class PostController {
	private final PostService service;

	@GetMapping("/")
	public String index() {
		return "hello";
	}

	@GetMapping("/post/{id}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable("id") Long id) {
		PostDTO postDto = service.read(id);
		if (postDto != null) {
			return new ResponseEntity<>(postDto, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/posts/size")
	public ResponseEntity<Long> getTotalSize() {
		Long totalPage = service.getTotalPage();
		log.info("totalPage ====>" + totalPage);
		return new ResponseEntity<>(totalPage, HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<List<PostDTO>> getPosts(PageRequestDTO pageRequestDTO) {
		log.info("get posts info");
		// parameter으로 size, page를 줘서 페이지에 대한 값 설정이 가능
		try {
			PageResultDTO<PostDTO, Post> resultDTO = service.getList(pageRequestDTO);
			return new ResponseEntity<>(resultDTO.getDtoList(), HttpStatus.OK);
		} catch (Exception e) {
			System.err.println(e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<PostDTO> registerPost(@RequestBody PostDTO dto) {
		log.info("regsiter post");
		try {
			service.register(dto);
			return new ResponseEntity<>(dto, HttpStatus.CREATED);
		} catch (Exception e) {
			System.err.println(e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/post/update/{id}")
	public ResponseEntity<PostDTO> updatePost(@PathVariable("id") Long id, @RequestBody PostDTO dto) {
		dto.setPno(id);
		try {
			PostDTO result = service.modify(dto);
			if (result != null) {
				return new ResponseEntity<>(result, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/post/delete/{id}")
	public ResponseEntity<HttpStatus> deletePost(@PathVariable("id") Long id) {
		try {
			service.remove(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
