package com.dajeong.board;

import java.util.List;

import com.dajeong.page.RowNumber;

public interface BoardDAO {
	
	//selectList
	public List<BoardDTO> selectList(RowNumber rowNumber) throws Exception;
		
	//selectOne
	public BoardDTO selectOne(int num) throws Exception;
	
	//insert
	public int insert(BoardDTO boardDTO) throws Exception;
	
	//update
	public int update(BoardDTO boardDTO) throws Exception;
	
	//delete 
	public int delete(int num) throws Exception;
	
	//getCount
	public int getCount(String kind, String search) throws Exception;

}
