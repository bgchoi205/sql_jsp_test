package com.jhs.exam.exam2.http.controller;

import java.util.ArrayList;
import java.util.List;

import com.jhs.exam.exam2.container.Container;
import com.jhs.exam.exam2.dto.Article;
import com.jhs.exam.exam2.dto.Board;
import com.jhs.exam.exam2.dto.ResultData;
import com.jhs.exam.exam2.http.Rq;
import com.jhs.exam.exam2.service.ArticleService;
import com.jhs.exam.exam2.service.BoardService;
import com.jhs.exam.exam2.util.Ut;

public class UsrArticleController extends Controller {
	private ArticleService articleService;
	private BoardService boardService;

	public void init() {
		articleService = Container.articleService;
		boardService = Container.boardService;
	}

	@Override
	public void performAction(Rq rq) {
		switch (rq.getActionMethodName()) {
		case "list":
			actionShowList(rq);
			break;
		case "detail":
			actionShowDetail(rq);
			break;
		case "write":
			actionShowWrite(rq);
			break;
		case "doWrite":
			actionDoWrite(rq);
			break;
		case "modify":
			actionShowModify(rq);
			break;
		case "doModify":
			actionDoModify(rq);
			break;
		case "doDelete":
			actionDoDelete(rq);
			break;
		default:
			rq.println("존재하지 않는 페이지 입니다.");
			break;
		}
	}

	private void actionDoDelete(Rq rq) {
		int id = rq.getIntParam("id", 0);
		String redirectUri = rq.getParam("redirectUri", "../article/list");

		if (id == 0) {
			rq.historyBack("id를 입력해주세요.");
			return;
		}

		Article article = articleService.getForPrintArticleById(rq.getLoginedMember(), id);
		
		if ( article == null ) {
			rq.historyBack(Ut.f("%d번 게시물이 존재하지 않습니다.", id));
			return;
		}
		
		ResultData actorCanDeleteRd = articleService.actorCanDelete(rq.getLoginedMember(), article);
		
		if(actorCanDeleteRd.isFail()) {
			rq.historyBack(actorCanDeleteRd.getMsg());
			return;
		}
		
		articleService.delete(id);

		rq.replace(Ut.f("%d번 게시물을 삭제하였습니다.", id), redirectUri);
	}

	private void actionShowDetail(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			rq.historyBack("id를 입력해주세요.");
			return;
		}

		Article article = articleService.getForPrintArticleById(rq.getLoginedMember(), id);
		
		if ( article == null ) {
			rq.historyBack(Ut.f("%d번 게시물이 존재하지 않습니다.", id));
			return;
		}

		rq.setAttr("article", article);
		rq.jsp("usr/article/detail");
	}

	private void actionShowList(Rq rq) {
		int page = rq.getIntParam("page", 1);
		int boardId = rq.getIntParam("boardId", 0);
		String searchKeywordTypeCode = rq.getParam("searchKeywordTypeCode", "title,body");
		String searchKeyword = rq.getParam("searchKeyword", "");
		String baseUri = "?";
		String searchUri = "searchKeywordTypeCode=" + searchKeywordTypeCode + "&searchKeyword=" + searchKeyword + "&";
		String boardUri = "boardId=" + boardId + "&";
		
		String boardName = null;
		
		if(boardId != 0) {
			Board board = boardService.getBoardById(boardId);
			
			if(board == null) {
				rq.historyBack(Ut.f("%d번 게시판은 존재하지 않습니다.", boardId));
				return;
			}
			
			boardName= board.getName();
			
		}
		
		

		if(boardId != 0) {
			baseUri = baseUri + boardUri;
		}
		
		if(searchKeyword != null && searchKeyword.trim().length() > 0) {
			baseUri = baseUri + searchUri;
		}
		
		int articleCountForPage = 5;
		
		List<Article> articles = articleService.getForPrintArticles(rq, rq.getLoginedMember(), page, boardId, articleCountForPage, searchKeywordTypeCode, searchKeyword);
		
		int totalArticlesCount = articleService.getTotalArticlesCount(searchKeywordTypeCode, searchKeyword, boardId);
		
		int totalPage = (int)Math.ceil((double)totalArticlesCount / articleCountForPage);
		int pageBlockCount = 5;
		int curBlock = (int)Math.ceil((double)page / pageBlockCount);
		int startPage = (curBlock - 1) * pageBlockCount + 1;
		int endPage = startPage + pageBlockCount - 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		int mobilePageBlockCount = 4;
		int mobileCurBlock = (int)Math.ceil((double)page / mobilePageBlockCount);
		int mobileStartPage = (mobileCurBlock - 1) * mobilePageBlockCount + 1;
		int mobileEndPage = mobileStartPage + mobilePageBlockCount - 1;
		if(mobileEndPage > totalPage) {
			mobileEndPage = totalPage;
		}
		
//		int pageArm = 5;
//		int startPage = page - pageArm;
//		int endPage = page + pageArm;
//		if(startPage < 1) {
//			startPage = 1;
//		}
//		if(endPage > totalPage) {
//			endPage = totalPage;
//		}
		
		
		rq.setAttr("page", page);
		rq.setAttr("pageBlockCount", pageBlockCount);
		rq.setAttr("mobilePageBlockCount", mobilePageBlockCount);
		rq.setAttr("mobileStartPage", mobileStartPage);
		rq.setAttr("mobileEndPage", mobileEndPage);
		rq.setAttr("boardId", boardId);
		rq.setAttr("boardName", boardName);
		rq.setAttr("searchKeyword", searchKeyword);
		rq.setAttr("searchKeywordTypeCode", searchKeywordTypeCode);
		rq.setAttr("baseUri", baseUri);
		rq.setAttr("totalArticlesCount", totalArticlesCount);
		rq.setAttr("articles", articles);
		rq.setAttr("totalPage", totalPage);
		rq.setAttr("startPage", startPage);
		rq.setAttr("endPage", endPage);
		rq.jsp("usr/article/list");
	}

	private void actionDoWrite(Rq rq) {
		int boardId = rq.getIntParam("boardId", 0);
		int memberId = rq.getLoginedMemberId();
		String title = rq.getParam("title", "");
		String body = rq.getParam("body", "");
		String redirectUri = rq.getParam("redirectUri", "../article/list");
		
		if (boardId == 0) {
			rq.historyBack("boardId를 입력해주세요.");
			return;
		}

		if (title.length() == 0) {
			rq.historyBack("title을 입력해주세요.");
			return;
		}

		if (body.length() == 0) {
			rq.historyBack("body를 입력해주세요.");
			return;
		}
		
		Board board = boardService.getBoardById(boardId);
		
		if(board == null) {
			rq.historyBack("존재하지 않는 게시판입니다.");
			return;
		}

		ResultData writeRd = articleService.write(boardId, memberId, title, body);
		int id = (int) writeRd.getBody().get("id");

		redirectUri = redirectUri.replace("[NEW_ID]", id + "");

		rq.replace(writeRd.getMsg(), redirectUri);
	}

	private void actionShowWrite(Rq rq) {
		List<Board> boards = boardService.getBoards();
		
		rq.setAttr("boards", boards);
		rq.jsp("usr/article/write");
	}
	
	private void actionDoModify(Rq rq) {
		int id = rq.getIntParam("id", 0);
		String title = rq.getParam("title", "");
		String body = rq.getParam("body", "");
		String redirectUri = rq.getParam("redirectUri", Ut.f("../article/detail?id=%d", id));
		
		if (id == 0) {
			rq.historyBack("id를 입력해주세요.");
			return;
		}

		if (title.length() == 0) {
			rq.historyBack("title을 입력해주세요.");
			return;
		}

		if (body.length() == 0) {
			rq.historyBack("body를 입력해주세요.");
			return;
		}
		
		Article article = articleService.getForPrintArticleById(rq.getLoginedMember(), id);
		
		if(article == null) {
			rq.historyBack("존재하지 않는 게시물입니다.");
			return;
		}
		
		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMember(), article);
		
		if(actorCanModifyRd.isFail()) {
			rq.historyBack(actorCanModifyRd.getMsg());
			return;
		}

		ResultData modifyRd = articleService.modify(id, title, body);

		rq.replace(modifyRd.getMsg(), redirectUri);
	}

	private void actionShowModify(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			rq.historyBack("id를 입력해주세요.");
			return;
		}

		Article article = articleService.getForPrintArticleById(rq.getLoginedMember(), id);
		
		if ( article == null ) {
			rq.historyBack(Ut.f("%d번 게시물이 존재하지 않습니다.", id));
			return;
		}
		
		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMember(), article);
		
		if(actorCanModifyRd.isFail()) {
			rq.historyBack(actorCanModifyRd.getMsg());
			return;
		}

		rq.setAttr("article", article);
		rq.jsp("usr/article/modify");
	}
}
