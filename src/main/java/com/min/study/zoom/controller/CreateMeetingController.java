package com.min.study.zoom.controller;

import java.awt.Frame;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.min.study.user.dao.UserDaoService;
import com.min.study.user.vo.UserDetailsVO;
import com.min.study.zoom.ZoomApiIntegration;
import com.min.study.zoom.ZoomDetails;
import com.min.study.zoom.dao.MeetingDaoService;
import com.min.study.zoom.dao.MeetingDaoServicelmpl;
import com.min.study.zoom.dto.meetingDTO;

@Controller
public class CreateMeetingController {
		
		String topic;
		String start_time;
		String duration;
		String password;
		String type;
		meetingDTO dto = new meetingDTO();
		
		
		private ZoomApiIntegration ZoomApiIntegration;
		private String apiResult = null;
	
		@Autowired
	    private void setZoomApiIntegration(ZoomApiIntegration ZoomApiIntegration) {
	        this.ZoomApiIntegration = ZoomApiIntegration;
	    }
		
		@Resource(name="MeetingDaoSerivce")
		private MeetingDaoService dao;
		
		@RequestMapping("/create/meetings")
		public String createMeet(){
			return "/ZOOM/ZoomCreate";
		}
		
		
		
		//form
		@RequestMapping(value ="/create/redirect", method = { RequestMethod.GET, RequestMethod.POST })
			public void createRedirct(Model model, HttpSession session, HttpServletRequest request,HttpServletResponse response){
			  
			  System.out.println("----------------create meetings api 요청------------------");
			  System.out.println("---------Zoom 회의 개설 -----------");
			  
		    	topic = request.getParameter("topic");
		    	start_time= request.getParameter("start_time");
		    	duration = request.getParameter("duration");
		    	password = request.getParameter("password");
		    	type = request.getParameter("type");
		      dto = new meetingDTO(null, topic, start_time, duration, password, null, null, null,null,type);
		      System.out.println("---------dto-----------"+dto.getMeetingid());
			  //회의 추가 상태로 변환
			  ZoomDetails.setZOOM_STATE("zoom_create");
			  String zoomAuthUrl = ZoomApiIntegration.getAuthorizationUrl(session);
		      System.out.println("-------ZoomAuthUrl: " + zoomAuthUrl);
		      model.addAttribute("url_create", zoomAuthUrl);
		      try {
		        	response.sendRedirect(zoomAuthUrl);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
	
		
		//redirect url
	  @RequestMapping(value = "/create/meetings/redirect", method = { RequestMethod.GET, RequestMethod.POST })
	  public String CreateMeeting(Model model, @RequestParam String code, @RequestParam String state, HttpSession session, HttpServletRequest request)
	            throws IOException {
	        try {
	        	 System.out.println("---------회의 개설성공 -----------");
	 	        OAuth2AccessToken oauthToken;
	 	        oauthToken = ZoomApiIntegration.getAccessToken(session, code, state);
	 	        apiResult = ZoomApiIntegration.setMeeting(oauthToken,dto);
	 	        System.out.println("apiResult-----" + apiResult);
	 	        
	        	JSONParser jsonParse = new JSONParser();
	            JSONObject obj =  (JSONObject)jsonParse.parse(apiResult);
	            System.out.println("JsonObject 결과 값 :: " + obj);
	            //Json Array값만 빼기
	            //Json 배열 Json Object로 변환
	            
	            /* 데이터 베이스에 저장 */
	            String start_time = obj.get("start_time").toString();
	            String join_url = obj.get("join_url").toString();
	            String meetingid = "null";
	            String uuid = obj.get("uuid").toString();
	            String joinId = obj.get("id").toString();
	            String joinPw = obj.get("password").toString();
	            String duration = obj.get("duration").toString();
	            String host_Id = obj.get("host_id").toString();
	            String start_url = obj.get("start_url").toString();
	            String type = obj.get("type").toString();
	            
	           
	            Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
	            User user = (User) authentication.getPrincipal();
	            String userid = user.getUsername();

	            System.out.println("회의 개설 유저 아이디: " +userid);
	            
	            Map<String, String> paramMap = new HashMap<String, String>();
	    		paramMap.put("topic", topic);
	    		paramMap.put("start_time", start_time);
	    		paramMap.put("join_url", join_url);
	    		paramMap.put("meetingid", meetingid);
	    		paramMap.put("uuid", uuid);
	    		paramMap.put("joinId", joinId);
	    		paramMap.put("joinPw", joinPw);
	    		paramMap.put("duration", duration);
	    		paramMap.put("host_Id", host_Id);
	    		paramMap.put("start_url", start_url);
	    		paramMap.put("userid", userid);
	    		paramMap.put("type", type);
	    		int result = dao.insertMeeting(paramMap);
	    		
	    		System.out.println("param=================="+paramMap);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	        
	        

	        
	        return "redirect:/zoom";
	    }
	  
	  
}
