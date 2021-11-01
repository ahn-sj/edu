package org.zerock.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	@RequestMapping("")
	public void basic() {
		log.info("basic method.......");
	}

	@GetMapping("/basicGET")
	public void basicGET() {
		log.info("basicGET method.......");
	}
	
	@GetMapping("/ex01")
	public void ex01(SampleDTO dto) {
		log.info(dto);
	}
	
	@GetMapping("/ex02")
	public void ex02(@RequestParam("name") String name, int age) {
		log.info(name);
		log.info(age);
	}
	
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		log.info("ids : " + ids);
		
		return "ex02List";
	}
	// 특수문자라 브라우저가 이해할 수 있도록 변경시킨다
	//http://localhost:8080/sample/ex02Bean?list[0].name=AA&list[0].age=16
	//http://localhost:8080/sample/ex02Bean?list%5B0%5D.name=AA&list%5B0%5D.age=16
	// 이 메서드는 여러개의 개체를 한꺼번에 추출할 수 있는 방법
//	@GetMapping("/ex02Bean")
//	public String ex02Bean(@ModelAttribute("sample") SampleDTOList list, Model model) {
//		log.info(list);
//		
//		model.addAttribute("result", "success");
//		
//		return "sample/ex02Bean";
//	}
	
	
//	@GetMapping("/ex02Bean")
//	public String ex02Bean(SampleDTOList list) {
//		log.info(list);
//		
//		return "sample/ex02Bean";
//	}
	
//	@GetMapping("/ex02Bean")
//	public String ex02Bean(@ModelAttribute("slist") SampleDTOList list) {
//		log.info(list);
//		
//		return "sample/ex02Bean";
//	}
	
//	@GetMapping("/ex02Bean")
//	public void ex02Bean(@ModelAttribute("slist") SampleDTOList list) {
//		log.info(list);
//	}

	@GetMapping({"/ex02Bean", "/ex022"})
	public void ex02Bean(@ModelAttribute("slist") SampleDTOList list) {
		log.info(list);
	}
	
	@GetMapping("/re1")
	public String re1() {
		log.info("re1..........");
		
		return "redirect:/sample/re2";
	}

	@GetMapping("/re2")
	public void re2() {
		log.info("re1..........");
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("exUpload.......");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {

		files.forEach(file -> {
			log.info(file.getOriginalFilename());
			log.info(file.getSize());
			log.info(file.getContentType());
		});
	}
	
	
}
