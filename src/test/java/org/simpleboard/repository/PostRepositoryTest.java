package org.simpleboard.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.simpleboard.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostRepositoryTest {
	@Autowired
	private PostRepository postRepository;

	@Test
	public void insertTestData() {
		IntStream.range(0, 5000).forEach(i -> {
			Post post = Post.builder().title("TestTitle_" + i).content(
					"CONTENT@@@@@@@@@@@@@@@@@@@@@@@@@@@@CONTENT@@@@@@@@CONTENT@@@@@@@@CONTENT@@@@@@@@CONTENT@@@@@@@@CONTENT@@@@@@@@CONTENT@@@@@@@@CONTENT@@@@@@@@@@@@"
							+ i)
					.writer("writer_" + i).build();
			System.out.println(post.getContent());
			System.out.println(postRepository.save(post));
		});
	}

	// @Test
	public void updateTest() {
		Optional<Post> result = postRepository.findById(25L);
		if (result.isPresent()) {
			Post post = result.get();
			post.changeContent("ModifiedTest@__@");
			post.changeTitle("ModifiedTest@__@");
			postRepository.save(post);
		}
	}
}
