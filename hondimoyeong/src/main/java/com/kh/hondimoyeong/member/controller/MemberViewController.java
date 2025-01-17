package com.kh.hondimoyeong.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.hondimoyeong.companion.model.service.CompanionService;
import com.kh.hondimoyeong.course.model.service.CourseService;
import com.kh.hondimoyeong.member.model.service.MemberService;

@Controller
public class MemberViewController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CompanionService companionService;
	
	// 화면
	@RequestMapping("login")
	public String login() {
		return "member/login";
	}

	@RequestMapping("insertForm")
	public String insertForm() {
		return "member/insertForm";
	}	
	
	@RequestMapping("searchIdForm")
	public String searchIdForm() {
		return "member/searchIdForm";
	}
	
	@RequestMapping("searchPwdForm")
	public String searchPwdForm() {
		return "member/searchPwdForm";
	}
	
	@RequestMapping("myPage")
	public String myPage() {
		return "member/myPage";
	}

	@RequestMapping("myPageUpdate")
	public String myPageUpdate() {
		return "member/myPageUpdate";
	}
	
	@RequestMapping("reservationList")
	public String reservationList() {
		return "member/reservationList";
	}
	
	@RequestMapping("list.customerView")
	public String selectAll() {
		return "member/customerList";
	}
	
	@RequestMapping("completeCourse")
	public String completeCourse(Model model) {
		model.addAttribute("list", courseService.stampList());
		return "member/completeCourse";
	}
	
	@RequestMapping("customerEnrollForm")
	public String customerEnrollForm() {
		return "member/customerEnrollForm";
	}
	
	
	@RequestMapping("list.reservationListView")
	public String reservationSelectAll() {
		return "member/reservationList";
	}
	
	
	@GetMapping("list.customerUpdateForm")
	public ModelAndView customerUpdateForm(@RequestParam("customerNo") int customerNo, ModelAndView mv){
		mv.addObject("customer", memberService.selectCustomerByNo(customerNo)).setViewName("member/customerUpdate");
		return mv;
	}
	
	
	
	
}
