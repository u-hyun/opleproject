package com.ople.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ople.domain.Member;
import com.ople.domain.Board;
import com.ople.service.BoardService;

@SessionAttributes("member")
@Controller
public class BoardController {
	//session에 member가 없으면 실행, 있으면 실행되지 않는다.
	@ModelAttribute("member")
	public Member getMember() {
		return new Member();
	}
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/getBoardList")
	public String getBoardList(Model m, @RequestParam(name = "p", defaultValue = "1") int pNum, @ModelAttribute("member")Member member) {
		System.out.println(member.getMemberId());
		if(member.getMemberId() == null) {
			return "redirect:loginform";
		}
		Page<Board> pageList = boardService.getBoardList(pNum);
		List<Board> bList = pageList.getContent();// 보여질 글
		int totalPageCount = pageList.getTotalPages();// 전체 페이지 수
		m.addAttribute("blist", bList);
		m.addAttribute("totalPage", totalPageCount);

		int pageNum = 2;
		int begin = (pNum - 1) / pageNum * pageNum + 1;
		int end = begin + pageNum - 1;
		if (end > totalPageCount) {
			end = totalPageCount;       
		}

		m.addAttribute("begin", begin);
		m.addAttribute("end", end);

		return "/board/getBoardList";
	}
	
	@GetMapping("/insertBoard")
	public String insertBoardView() {
		return "/board/insertBoard";
	}
	
	@PostMapping("insertBoard")
	public String insertBoard(Board board, @ModelAttribute("member")Member member) {
		board.setMemberId(member.getMemberId());
		boardService.saveBoard(board);
		return "redirect:getBoardList";
	}

	@RequestMapping("/content/{commentId}")
	public String getBoard(@PathVariable Long commentId, Model m) {
		Board board = boardService.getBoard(commentId);
		m.addAttribute("board", board);
		return "/board/getBoard";
	}
	
	@GetMapping("/updateform/{commentId}")
	public String updateform(@PathVariable Long commentId, Model m) {
		Board board = boardService.onlyBoard(commentId);
		m.addAttribute("board", board);
		return "/board/updateform";
	}

	@PostMapping("/update")
	public String update(Board board) {
		boardService.saveBoard(board);
		return "redirect:/getBoardList";
	}

	@GetMapping("/delete/{commentId}")
	public String delete(@PathVariable Long commentId) {
		boardService.deleteBoard(commentId);
		return "redirect:/getBoardList";
	}
		
}
