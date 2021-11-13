package com.jaesa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jaesa.domain.BoardVO;
import com.jaesa.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
@ToString
public class BoardServiceImpl implements BoardService {
	private final BoardMapper mapper;

	
	// 삽입
	// n번의 게시물이 등록되었습니다 를 표현할 때는 반환타입을 Long으로 준다
//	@Override
//	public void register(BoardVO board) {
//		mapper.insert(board);
//	}
	@Override
	public Long register(BoardVO board) {
		mapper.insertSelectKey(board);
		
		return board.getBno();
	}

	// 조회
	@Override
	public BoardVO get(Long bno) {
		return mapper.read(bno);
	}

	// 수정
	@Override
	public int modify(BoardVO board) {
		return mapper.update(board);
	}

	// 삭제
	@Override
	public int remove(Long bno) {
		return mapper.delete(bno);
	}

	// 전체 목록 조회
	@Override
	public List<BoardVO> getList() {
		return mapper.getList();
	}
	
}
