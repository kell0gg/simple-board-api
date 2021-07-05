package org.simpleboard.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDTO {
	private Long pno;
	private String title;
	private String content;
	private String writer;
	private LocalDateTime createdAt, modifiedAt;
}
