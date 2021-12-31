package com.jaesa.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j;

@Data
@Log4j
public class Criteria {
//	페이징 처리
	// 현재 페이지 번호
	private int pageNum;
	// 페이지에 보여줄 게시물 개수
	private int amount;
	
//	검색 처리
	// 검색 유형
	private String type;
	// 검색 키워드
	private String keyword;
	
	public String[] getTypeArr() {
		log.info("---------------------");
		log.info("getTypeArr");
		log.info("---------------------");
		
		return type == null ? new String[] {} : type.split("");
	}
	
	
	public Criteria() {
		this(1, 10);
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	
}
