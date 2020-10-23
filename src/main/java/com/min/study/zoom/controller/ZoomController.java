package com.min.study.zoom.controller;

import java.io.IOException;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.min.study.zoom.*;
import com.min.study.zoom.dto.meetingDTO;

@Controller
public class ZoomController {
	
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
	
	
    @RequestMapping(value = "/zoom", method = { RequestMethod.GET, RequestMethod.POST })
    public String redirect(Model model, HttpSession session) {
    	System.out.println("-----------zoom api 연결 요청-----------");
    	//미팅 정보
    	ZoomDetails.setZOOM_STATE("zoom");
        String zoomAuthUrl = ZoomApiIntegration.getAuthorizationUrl(session);
        System.out.println("-------ZoomAuthUrl: " + zoomAuthUrl);
        model.addAttribute("url", zoomAuthUrl);

        return "ZOOM/zoom";
    }
    
    
    //회의 리스트
    @RequestMapping(value = "/redirect", method = { RequestMethod.GET, RequestMethod.POST })
    public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session, HttpServletRequest request)
            throws IOException {
    	
    	System.out.println("---------Zoom redirect -----------");
        OAuth2AccessToken oauthToken;
        oauthToken = ZoomApiIntegration.getAccessToken(session, code, state);
        apiResult = ZoomApiIntegration.getUserProfile(oauthToken);
        
        System.out.println("----------------create meetings api 요청------------------");
		  //회의 추가 상태로 변환
        
		  ZoomDetails.setZOOM_STATE("zoom_create");
		  String zoomAuthUrl = ZoomApiIntegration.getAuthorizationUrl(session);
	        System.out.println("-------ZoomAuthUrl: " + zoomAuthUrl);
	        model.addAttribute("url_create", zoomAuthUrl);
	        
        try {
        	
            JSONParser jsonParse = new JSONParser();
            JSONObject obj =  (JSONObject)jsonParse.parse(apiResult);
            System.out.println("JsonObject 결과 값 :: " + obj);
            
            //Json Array값만 빼기
            JSONArray meeting = (JSONArray)obj.get("meetings");
            JSONObject object;
            //Json 배열 Json Object로 변환
            
        

            ArrayList<meetingDTO> dtos = new ArrayList<meetingDTO>();
            
            for (int i = 0; i < meeting.size(); i++) {          
            	object = (JSONObject) meeting.get(i);  
            	topic = object.get("topic").toString();
            	start_time = object.get("start_time").toString();
            	join_url = object.get("join_url").toString();
            	meetingid = object.get("id").toString();
            	duration = object.get("duration").toString();
            	uuid = object.get("uuid").toString();
            	  meetingDTO dto = new meetingDTO(meetingid, topic, start_time, duration, null, join_url, null,uuid,null,null);
            	  dtos.add(dto);
            }
            
          
            System.out.println("dtos :: " + dtos);
            model.addAttribute("dtos",dtos);
            //topic,join_url,start_time
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        
        
        return "ZOOM/ZoomSuccess";
    }
    
    
}
