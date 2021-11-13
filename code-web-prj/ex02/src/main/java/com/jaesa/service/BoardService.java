package com.jaesa.service;

import java.util.List;

import com.jaesa.domain.BoardVO;

public interface BoardService {
	// 등록
//	void register(BoardVO board);
	// n번의 게시물이 등록되었습니다 를 표현할 때는 반환타입을 Long으로 준다
	Long register(BoardVO board);
	
	// 조회
	BoardVO get(Long bno);
	
	// 수정
	int modify(BoardVO board);
	
	// 삭제
	int remove(Long bno);
	
	// 전체 목록 조회
	List<BoardVO> getList();
}
