package com.mahua.poetryovertea.model.dto;


import lombok.Data;

@Data
public class PoemInsertDTO {

	/**
	 * 古诗题目
	 */
	private String title;

	/**
	 * 诗人姓名
	 */
	private String poet;

	/**
	 * 古诗正文内容
	 */
	private String content;

	/**
	 * 古诗分类
	 */
	private String category;

	/**
	 * 古诗译文
	 */
	private String translation;

	/**
	 * 古诗赏析内容
	 */
	private String appreciation;

}
