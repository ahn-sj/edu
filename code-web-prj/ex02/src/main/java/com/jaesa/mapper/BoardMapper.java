package com.jaesa.mapper;

import java.util.List;

import com.jaesa.domain.BoardVO;

public interface BoardMapper {
	List<BoardVO> getList();
	
	void insert(BoardVO board);
	
	void insertSelectKey(BoardVO board);
}
