package com.min.study.zoom.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.min.study.zoom.ZoomDetails;
import com.min.study.zoom.dao.MeetingDaoService;
import com.min.study.zoom.dto.meetingDTO;

@Controller
public class ListMeetingController {

			private String topic;
			private String start_time;
			private String join_url;
			private String meetingid;
			private String duration;
			meetingDTO dto = new meetingDTO();
			@Resource(name="MeetingDaoSerivce")
			private MeetingDaoService dao;
			
			@RequestMapping(value ="/list/meetings", method = { RequestMethod.GET, RequestMethod.POST })
				public String createRedirct(Model model, HttpSession session, HttpServletRequest request){
				  
				  System.out.println("----------------list meetings------------------");
				  Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		            User user = (User) authentication.getPrincipal();
		            String userid = user.getUsername();
		            
		            List<HashMap<String, Object>> meetings = dao.selectMeeting(userid);
		            System.out.println(userid+"의 회의 리스트: "+ meetings);
		          
		            if(meetings ==null) {
		    			System.out.println("회의 정보  존재 하지 않음 ");
		    			}
		            
		            model.addAttribute("dtos",meetings);
		            
					return "/ZOOM/ZoomList";
				}
	
}
