package com.min.study.zoom.service;

import java.io.IOException;


import org.json.simple.JSONObject;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.min.study.zoom.ZoomDetails;

public class DeleteMeetingService {
	
	ZoomDetails zoomDetails = new ZoomDetails();
	String CLIENT_ID = ZoomDetails.getClientId();
	String CLIENT_SECRET = ZoomDetails.getClientSecret();
	String SESSION_STATE = ZoomDetails.getSessionState();
	String REDIRECT_URI_DELETE = ZoomDetails.getRedirectUriDelete();
	String REDIRECT_URI = ZoomDetails.getRedirectUri();
	String DELETE_MEETING_API_URL = ZoomDetails.getDELETE_MEETING_API_URL();
	
	 //회의 추가
    public String DelMeeting(OAuth2AccessToken oauthToken) throws IOException{
    	 System.out.println("---- 회의 추가 서비스 ----------");
    	 
        OAuth20Service oauthService =new ServiceBuilder()
                .apiKey(CLIENT_ID)
                .apiSecret(CLIENT_SECRET)
                .callback(REDIRECT_URI).build(ZoomDetails.instance());
        
        OAuthRequest request = new OAuthRequest(Verb.POST, DELETE_MEETING_API_URL, oauthService);
        
        request.addHeader("Content-Type", "application/json;charset=UTF-8");
        JSONObject jsonOb = new JSONObject();
        System.out.println("-----json 출력 --"+jsonOb.toString());		
        
        request.addPayload(jsonOb.toString());
        oauthService.signRequest(oauthToken, request);
        Response response = request.send();
        return response.getBody();
    }
    
}
