package com.min.study.zoom.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.min.study.user.dao.UserDaoService;
import com.min.study.user.vo.UserDetailsVO;
import com.min.study.zoom.ZoomDetails;
import com.min.study.zoom.dao.MeetingDaoService;

@Controller
public class InvitationMeetingController {

	
	private String uuid;
	private String topic;
	private String userid;
	
	@Resource(name="userDaoService")
	private UserDaoService dao;
	
	@Resource(name="MeetingDaoSerivce")
	private MeetingDaoService mdao;
	
	@RequestMapping(value = "/Invitation", method = { RequestMethod.GET, RequestMethod.POST })
    public String Invitation(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response, Authentication auth) {
    	System.out.println("-----------zoom 회의 초대-----------");
    	
    	uuid = request.getParameter("uuid");
    	topic = request.getParameter("topic");
    	UserDetailsVO vo = (UserDetailsVO) auth.getPrincipal();
    	userid = vo.getId();
    	List<HashMap<String, Object>> user = dao.selectUseradmin(userid);
    	System.out.println(user);
        model.addAttribute("dtos",user);
        model.addAttribute("topic",topic);
    	return "ZOOM/Invitation";
    }
	
	
	@RequestMapping(value = "/Invitation/Meeting", method = { RequestMethod.GET, RequestMethod.POST })
    public String InvitationMeeting(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response, Authentication auth) {
    	System.out.println("-----------초대하기-----------");
    	
    	HashMap<String, String> paramMap = new HashMap<String, String>();
    	userid = request.getParameter("userid");
    	paramMap.put("userid", userid);
    	paramMap.put("uuid", uuid);
    	System.out.println("----------------------"+userid+","+uuid);
    	int result = mdao.insertInvitation(paramMap);
    	model.addAttribute("msg", "초대장을 보냈습니다.");
    	model.addAttribute("url", "https://f2cde7541078.ngrok.io/list/meetings");
    	return "/ZOOM/alert";
    }
	
	
	
	@RequestMapping(value = "/Invitation/All", method = { RequestMethod.GET, RequestMethod.POST })
    public String InvitationAll(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response, Authentication auth) {
    	System.out.println("-----------초대하기-----------");
    	
    	return "ZOOM/Invitation";
    }
}
