package org.simpleboard.service;

import org.junit.jupiter.api.Test;
import org.simpleboard.dto.PageRequestDTO;
import org.simpleboard.dto.PageResultDTO;
import org.simpleboard.dto.PostDTO;
import org.simpleboard.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostServiceImplTest {

	@Autowired
	private PostService service;

	// @Test
	public void testRegister() {
		PostDTO postDTO = PostDTO.builder().title("test title").content("test content").writer("test writer").build();
		System.out.println(service.register(postDTO));
	}

	// @Test
	public void testList() {
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
		PageResultDTO<PostDTO, Post> resultDTO = service.getList(pageRequestDTO);

		for (PostDTO p : resultDTO.getDtoList()) {
			System.out.println(p);
		}
	}

	@Test
	public void testPage() {
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
		PageResultDTO<PostDTO, Post> resultDTO = service.getList(pageRequestDTO);

		System.out.println("Prev ==>" + resultDTO.isPrev());
		System.out.println("Next ==>" + resultDTO.isNext());
		System.out.println("Total ==>" + resultDTO.getTotalPage());

		System.out.println("============================");
		for (PostDTO p : resultDTO.getDtoList()) {
			System.out.println(p);
		}

		System.out.println("----------------------------");
		resultDTO.getPageList().forEach(i -> System.out.println(i));
	}
}
