package com.min.study.zoom.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.min.study.user.vo.UserDetailsVO;
import com.min.study.zoom.dao.MeetingDaoService;

@Controller
public class InvitationUserController {

	private String userid;
	
	@Resource(name="MeetingDaoSerivce")
	private MeetingDaoService mdao;
	
	@RequestMapping(value = "/user/Invitation", method = { RequestMethod.GET, RequestMethod.POST })
    public String Invitation(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response, Authentication auth) {
    	System.out.println("-----------zoom 회의 초대-----------");
	    	
	    	UserDetailsVO vo = (UserDetailsVO) auth.getPrincipal();
	    	userid = vo.getId();
	    	System.out.println("----------userid----------" + userid);
	    	List<HashMap<String, Object>> result = mdao.selectUserInvit(userid);
	    	System.out.println(result);
	    	
	    	
	    	
	    	model.addAttribute("result",result);
	    	
	    
    	return "ZOOM/user_Invitation";
    }
	
}
