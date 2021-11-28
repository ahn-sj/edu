package com.jaesa.domain;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PageDTO {
	// 1개의 페이지는 게시물 10개 단위이고, 다음/이전의 목록은 10개의 페이지 단위이다.
	
	// 페이지 번호 목록
	// 시작 페이지 번호와 마지막 페이지 번호
	// 만약 게시물이 57개 있으면 시작 페이지 = 1, 마지막 페이지 = 6
	// 만약 게시물이 123개 있으면 다음페이지 클릭시 시작 페이지 = 11, 마지막 페이지 = 13
	private int startPage, endPage;
	private boolean prev, next;
	
	// total은 전체 게시물 개수
	private int total;
	private Criteria cri;
	
	
	// 생성자 시작
	public PageDTO(Criteria cri, int total) { 
		this.cri = cri;
		this.total = total;
		
		// pageNum 현재 위치한 페이지 번호, amount는 페이지별 나눌 게시물 단위(1페이지 = 10개 게시물)
		// endPage 마지막 페이지 번호, startPage(endPage-9) 시작 페이지 번호
		// ex) pageNum이 3일때 --> 0.3 --> 1 --> 1 * 10 => 10 (마지막 페이지 번호)
		// ex) pageNum이 10일때 --> 10 - 9 => 1(시작 페이지 번호)
		this.endPage = (int)(Math.ceil(cri.getPageNum() / 10.0)) * 10;
		this.startPage = endPage - 9;
		
		// > 1은 게시물이 101개 이상부터 성립된다.
		// 다음/이전의 단위는 10개의 페이지 단위(= 게시물 100개)이기 때문 
		this.prev = this.startPage > 1;
		
		// total은 전체 게시물의 개수
		// ex) (total * 1.0) / cri.getAmount()) --> 71 / 10 -> 7.1 => 8 
		// realEnd에 8이 들어가게 된다. 71개의 게시물이 있다고 했을 때 8페이지까지 존재해야 하기 때문에
		int realEnd = (int)(Math.ceil((total * 1.0) / cri.getAmount()));
		
		// 현재 페이지(pageNum)가 3일 때 endPage가 10이 되고 실제 필요한 페이지 번호(realEnd)는 8이 된다. 
		// 만약 실제 필요 페이지 번호(realEnd)가 마지막 페이지 번호(endPage)보다 작으면 endPage를 realEnd(8)로 설정한다.
		if(realEnd < this.endPage) this.endPage = realEnd;
		
		// 만약 total값이 300(page가 30까지 필요)이라고 가정했을 때,
		// 현재 페이지(pageNum)가 3일 때 다음 페이지가 존재해야 하기 때문에 next값을 true로 설정한다
		this.next = this.endPage < realEnd;
	} // 생성자 끝
}
