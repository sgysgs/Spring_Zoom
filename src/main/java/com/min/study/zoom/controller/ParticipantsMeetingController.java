package com.min.study.zoom.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.min.study.zoom.ZoomApiIntegration;
import com.min.study.zoom.ZoomDetails;
import com.min.study.zoom.dto.meetingDTO;

@Controller
public class ParticipantsMeetingController {

	private ZoomApiIntegration ZoomApiIntegration;
	private String apiResult = null;
    String topic = null;
    String start_time = null;
    String join_url = null;
    String meetingid = null;
    String duration = null;
    String uuid = null;
	
	
	@Autowired
    private void setZoomApiIntegration(ZoomApiIntegration ZoomApiIntegration) {
        this.ZoomApiIntegration = ZoomApiIntegration;
    }
	
	
    @RequestMapping(value = "/participants", method = { RequestMethod.GET, RequestMethod.POST })
    public void participants(Model model, HttpSession session,HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("-----------회의 참여자 정보 zoom api 연결 요청-----------");
    	
    	uuid = request.getParameter("uuid");
    	
    	
    	//미팅 정보
    	ZoomDetails.setZOOM_STATE("zoom_participants");
        String zoomAuthUrl = ZoomApiIntegration.getAuthorizationUrl(session);
        System.out.println("-------ZoomAuthUrl: " + zoomAuthUrl);
        try {
        	response.sendRedirect(zoomAuthUrl);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    
    
    //회
    @RequestMapping(value = "/participants/meetings/redirect", method = { RequestMethod.GET, RequestMethod.POST })
    public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session, HttpServletRequest request)
            throws IOException {
    	
    	System.out.println("---------Zoom redirect -----------");
        OAuth2AccessToken oauthToken;
        oauthToken = ZoomApiIntegration.getAccessToken(session, code, state);
        apiResult = ZoomApiIntegration.PartMeeting(oauthToken,uuid);
        
        return "ZOOM/participants";
    }
    
}
