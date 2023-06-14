package com.yedam.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
	
	private String brdTitle;
	private String brdWriter;
	private String brdContent;
	
}
