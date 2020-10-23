package com.min.study.zoom.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.min.study.zoom.*;
import com.min.study.zoom.dao.MeetingDaoService;
import com.min.study.zoom.dto.meetingDTO;

@Controller
public class DeleteMeetingController {
	
	private ZoomApiIntegration ZoomApiIntegration;
	private String apiResult = null;
	private String meetingid = null;
	private String uuid = null;
	ZoomDetails zoomDetails = new ZoomDetails();
	meetingDTO dto = new meetingDTO();
	
	@Autowired
    private void setZoomApiIntegration(ZoomApiIntegration ZoomApiIntegration) {
        this.ZoomApiIntegration = ZoomApiIntegration;
    }
	
	@Resource(name="MeetingDaoSerivce")
	private MeetingDaoService dao;
	
	
	@RequestMapping(value = "/DeleteMeeting", method = { RequestMethod.GET, RequestMethod.POST })
    public void DeleteMeeting(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("-----------zoom api 연결 요청-----------");
    	
    	meetingid = request.getParameter("meetingid");
    	uuid = request.getParameter("uuid");
    	 
    	
    	//미팅 정보
    	ZoomDetails.setZOOM_STATE("zoom_delete");
        String zoomAuthUrl = ZoomApiIntegration.getAuthorizationUrl(session);
        System.out.println("-------ZoomAuthUrl: " + zoomAuthUrl);
        //model.addAttribute("url", zoomAuthUrl);
        try {
        	response.sendRedirect(zoomAuthUrl);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        
    }
	
	
	//redirect url
	  @RequestMapping(value = "/delete/meetings/redirect", method = { RequestMethod.GET, RequestMethod.POST })
	  public String DeleteMeetingRedirect(Model model, @RequestParam String code, @RequestParam String state, HttpSession session, HttpServletRequest request)
	            throws IOException {
		  System.out.println("---------회의 삭제 -----------");
		  System.out.println("UUID-----" + uuid);
	        OAuth2AccessToken oauthToken;
	        oauthToken = ZoomApiIntegration.getAccessToken(session, code, state);
	        apiResult = ZoomApiIntegration.DelMeeting(oauthToken,meetingid);
	        
	        dao.deleteMeeting(uuid);
	        System.out.println("apiResult-----" + apiResult);
	        return "redirect:/zoom";
	    }
	
	
}
