package com.jhs.exam.exam2.service;

import java.util.List;

import com.jhs.exam.exam2.container.Container;
import com.jhs.exam.exam2.container.ContainerComponent;
import com.jhs.exam.exam2.dto.Article;
import com.jhs.exam.exam2.dto.Board;
import com.jhs.exam.exam2.dto.Member;
import com.jhs.exam.exam2.dto.ResultData;
import com.jhs.exam.exam2.http.Rq;
import com.jhs.exam.exam2.repository.BoardRepository;

public class BoardService implements ContainerComponent {
	private BoardRepository boardRepository;
	
	public void init() {
		boardRepository = Container.boardRepository;
	}

	public Board getBoardById(int boardId) {
		Board board = boardRepository.getBoardById(boardId);
		
		return board;
	}
	
	public List<Board> getBoards() {
		List<Board> boards =  boardRepository.getBoards();
		
		return boards;
	}

}
