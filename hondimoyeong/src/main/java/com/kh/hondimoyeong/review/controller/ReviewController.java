package com.kh.hondimoyeong.review.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.hondimoyeong.common.model.vo.PageInfo;
import com.kh.hondimoyeong.common.template.Pagination;
import com.kh.hondimoyeong.course.model.vo.Course;
import com.kh.hondimoyeong.review.model.service.ReviewService;
import com.kh.hondimoyeong.review.model.vo.Review;
import com.kh.hondimoyeong.review.model.vo.ReviewComment;
import com.kh.hondimoyeong.review.model.vo.ReviewImg;

@Controller
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	/**
	 * 
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("review")
	public String selectList(@RequestParam(value="page", defaultValue="1") int page, Model model) {
		PageInfo pi = Pagination.getPageInfo(reviewService.selectListCount(), page, 10, 5);
		
		List<ReviewComment> comment = reviewService.selectCommentCount();
		
		model.addAttribute("comment", comment);
		model.addAttribute("review", reviewService.selectList(pi));
		model.addAttribute("pageInfo", pi);
		return "review/reviewList";
	}
	
	@RequestMapping("detail.rvw")
	public ModelAndView detail(int reviewNo, ModelAndView mv) {
		
		Review review = reviewService.selectReview(reviewNo);
		if(review != null) {
			List<ReviewImg> reviewImgs = reviewService.selectReviewImgs(reviewNo);
			review.setReviewImgs(reviewImgs);
			
			if(reviewService.increaseCount(reviewNo) > 0) {
				mv.addObject("review", review).setViewName("review/reviewDetail");
			} else {
				mv.addObject("errorMsg", "조회 실패").setViewName("common/errorPage");
			}
		} else {
			mv.addObject("errorMsg", "리뷰 찾을 수 없음").setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping("search.rvw")
	public String search(@RequestParam(value="keyword") String keyword,
						 @RequestParam(value="condition") String condition,
						 @RequestParam Map<String, String> map,
						 @RequestParam(value="page", defaultValue="1") int page, Model model) {
		
		Map<String, String> searchMap = new HashMap<String, String>();
		searchMap.put("condition", condition);
		searchMap.put("keyword", keyword);
		
		int totalCount = reviewService.searchCount(searchMap);
		PageInfo pi = Pagination.getPageInfo(totalCount, page, 10, 5);
		List<Review> review = reviewService.search(searchMap, pi);
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("condition", condition);
		model.addAttribute("review", review);
		model.addAttribute("searchPage", pi);
		
		return "review/reviewList";
	}
	
	@RequestMapping("insertForm.rvw")
	public String insertForm(Model model, Course course) {
		List<Course> courseList = reviewService.selectCourse(course);
		model.addAttribute("courseList", courseList);
		return "review/reviewInsertForm";
	}
	
	@RequestMapping("insert.rvw")
	public String insert(Review review,
	                     @RequestParam("upfiles") MultipartFile[] upfiles,
	                     HttpSession session,
	                     Model model) {
	    
	    // 리뷰를 삽입하고 리뷰 번호를 얻습니다.
	    int reviewNo = reviewService.insertAndGetReviewNo(review);
	    
	    // 리뷰 번호를 확인하고 파일을 첨부합니다.
	    if (reviewNo > 0) { // 리뷰가 삽입되었을 경우
	        for (MultipartFile upfile : upfiles) {
	            if (!upfile.getOriginalFilename().equals("")) {
	                // 파일 첨부시
	                String originalFilename = upfile.getOriginalFilename();
	                String changeName = saveFile(upfile, session);

	                // REVIEW_IMG 테이블에 파일 정보 삽입
	                ReviewImg reviewImg = new ReviewImg();
	                reviewImg.setReviewNo(reviewNo); // 리뷰 번호 설정
	                reviewImg.setOriginName(originalFilename); // 원본 파일명 설정
	                reviewImg.setChangeName(changeName); // 변경된 파일명 설정
	                reviewService.insertImg(reviewImg); // 리뷰 이미지 삽입
	            }
	        }
	        session.setAttribute("alertMsg", "게시글 작성 성공!");
	        return "redirect:review";
	    } else { // 리뷰 삽입 실패
	        model.addAttribute("errorMsg", "작성 실패");
	        return "common/errorPage";
	    }
	}



	// 파일 따로 빼두기
	public String saveFile(MultipartFile upfile, HttpSession session) {
		String originName = upfile.getOriginalFilename();
		String ext = originName.substring(originName.lastIndexOf("."));
		String currentTime = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
		int ranNum = (int)Math.random() * 90000 + 10000;
		String changeName = currentTime + ranNum + ext;
		String savePath = session.getServletContext().getRealPath("/resources/reviewFile/");
		
			try {
				upfile.transferTo(new File(savePath + changeName));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return "resources/reviewFile/" + changeName;
	}
	
	
	/**
	 * 댓글 ajax
	 */
	@ResponseBody
	@GetMapping(value="comment", produces="application/json; charset=UTF-8")
	public String selectComment(int reviewNo) {
		return new Gson().toJson(reviewService.selectComment(reviewNo));
	}
	
	@ResponseBody
	@PostMapping("comment")
	public String ajaxInsertComment(ReviewComment reviewComment) {
		return reviewService.insertComment(reviewComment) > 0 ? "success" : "fail";
	}

}
