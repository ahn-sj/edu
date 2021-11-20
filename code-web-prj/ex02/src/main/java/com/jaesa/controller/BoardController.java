package com.jaesa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jaesa.domain.BoardVO;
import com.jaesa.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
@Log4j
public class BoardController {
	
	private final BoardService service;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("-----------------");
		log.info("list..........");
		log.info("-----------------");
		
		model.addAttribute("list", service.getList());
	}
	
//	@PostMapping("/register")
//	public void register(BoardVO board) {
//		log.info("board" + board);
//		
//		Long bno = service.register(board);
//		log.info("bno" + bno);
//	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("board" + board);
		
		Long bno = service.register(board);
		log.info("bno" + bno);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/get")
	public void get(@RequestParam("bno")Long bno, Model model) {
		log.info("/get");
		
		model.addAttribute("board", service.get(bno)) ;
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		int cnt = service.modify(board);
		
		if(cnt == 1) rttr.addFlashAttribute("result", "success"); 
		return "redirct:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno")Long bno, RedirectAttributes rttr) {
		int cnt = service.remove(bno);
		
		if(cnt == 1) rttr.addFlashAttribute("result", "success"); 
		return "redirct:/board/list";
	}
	
}
