package com.jaesa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jaesa.domain.BoardVO;
import com.jaesa.domain.Criteria;
import com.jaesa.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
@Log4j
public class BoardController {
	
	private final BoardService service;
	
	// not used paging - getList()
//	@GetMapping("/list")
//	public void list(Model model) {
//		log.info("-----------------");
//		log.info("list..........");
//		log.info("-----------------");
//		
//		model.addAttribute("list", service.getList());
//	}
	
	
	// used paging - getList(cri)
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		log.info("-----------------");
		log.info("getList(cri)..........");
		log.info("-----------------");
		
		model.addAttribute("list", service.getList(cri));
	}
	
//	@PostMapping("/register")
//	public void register(BoardVO board) {
//		log.info("board" + board);
//		
//		Long bno = service.register(board);
//		log.info("bno" + bno);
//	}
	
	
	@GetMapping("/register")
	public void registerGET() {
		log.info("register GET..");
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		Long bno = service.register(board);
		
		log.info("board" + board);
		log.info("bno" + bno);
		
		rttr.addFlashAttribute("result", bno);
		
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno")Long bno, Model model) {
		log.info("/get and /modify");
		
		model.addAttribute("board", service.get(bno)) ;
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		int cnt = service.modify(board);
		
		if(cnt == 1) rttr.addFlashAttribute("result", "success"); 
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno")Long bno, RedirectAttributes rttr) {
		int cnt = service.remove(bno);
		
		if(cnt == 1) rttr.addFlashAttribute("result", "success"); 
		return "redirect:/board/list";
	}
	
}
