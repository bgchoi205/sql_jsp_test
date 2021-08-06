package com.jhs.exam.exam2.repository;

import java.util.List;

import com.jhs.exam.exam2.container.ContainerComponent;
import com.jhs.exam.exam2.dto.Article;
import com.jhs.exam.exam2.dto.Board;
import com.jhs.mysqliutil.MysqlUtil;
import com.jhs.mysqliutil.SecSql;

public class BoardRepository implements ContainerComponent {
	public void init() {
		
	}

	public Board getBoardById(int boardId) {
		SecSql sql = new SecSql();
		sql.append("SELECT B.*");
		sql.append("FROM board AS B");
		sql.append("WHERE B.id = ?", boardId);
		
		return MysqlUtil.selectRow(sql, Board.class);
	}

	public List<Board> getBoards() {
		SecSql sql = new SecSql();
		sql.append("SELECT B.*");
		sql.append("FROM board AS B");
		
		return MysqlUtil.selectRows(sql, Board.class);
	}

	

}
