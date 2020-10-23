package com.min.study.zoom;

import java.io.IOException;

import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.util.StringUtils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.min.study.zoom.dto.meetingDTO;

public class ZoomApiIntegration {

	ZoomDetails zoomDetails = new ZoomDetails();
	String CLIENT_ID = ZoomDetails.getClientId();
	String CLIENT_SECRET = ZoomDetails.getClientSecret();
	String SESSION_STATE = ZoomDetails.getSessionState();
	/*REDIRECT_URI*/
	String REDIRECT_URI = ZoomDetails.getRedirectUri();
	String REDIRECT_URI_CREATE = ZoomDetails.getRedirectUriCreate();
	String REDIRECT_URI_DELETE = ZoomDetails.getRedirectUriDelete();
	String REDIRECT_URI_PARTICIPANTS = ZoomDetails.getRedirectUriParticipants();
	/*API_URL*/
	String PROFILE_API_URL = ZoomDetails.getProfileApiUrl();
	String DELETE_MEETING_API_URL = ZoomDetails.getDELETE_MEETING_API_URL();
	String PARTICIPANTS_API_URL = ZoomDetails.getParticipantsApiUrl();
	//
    public String getAuthorizationUrl(HttpSession session) {
        /* 세션 유효성 검증을 위하여 난수를 생성 */
        String state = generateRandomString();
        /* 생성한 난수 값을 session에 저장 */
        setSession(session,state);        
        
        //무슨 api인지 판단하기 위함
        String zoom = zoomDetails.getZOOM_STATE();
        String url = null;
        
        /* Scribe에서 제공하는 인증 URL 생성 기능을 이용하여 인증 URL 생성 */
        
        if(zoom.equals("zoom")){
        	url = REDIRECT_URI;
        }else if (zoom.equals("zoom_create")) {
        	url = REDIRECT_URI_CREATE;
		}else if (zoom.equals("zoom_delete")) {
			url = REDIRECT_URI_DELETE;
		}else if (zoom.equals("zoom_participants")) {
			url = REDIRECT_URI_PARTICIPANTS;
		}

        System.out.println("url---------------------- :" +url );
        OAuth20Service oauthService = new ServiceBuilder()                                                   
                .apiKey(CLIENT_ID)
                .apiSecret(CLIENT_SECRET)
                .callback(url)
                .state(state) //앞서 생성한 난수값을 인증 URL생성시 사용함
                .build(ZoomDetails.instance());
        
        return oauthService.getAuthorizationUrl();
    }
    
    public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException{
    	System.out.println("------Zoom AccessToken 획득 Method");
        /* Callback으로 전달받은 세선검증용 난수값과 세션에 저장되어있는 값이 일치하는지 확인 */
    	 String zoom = zoomDetails.getZOOM_STATE();
         String url = null;
    	
         if(zoom.equals("zoom")){
         	url = REDIRECT_URI;
         }else if (zoom.equals("zoom_create")) {
         	url = REDIRECT_URI_CREATE;
 		}else if (zoom.equals("zoom_delete")) {
			url = REDIRECT_URI_DELETE;
		}else if (zoom.equals("zoom_participants")) {
			url = REDIRECT_URI_PARTICIPANTS;
		}
        System.out.println("url---------------------- :" +url);
        String sessionState = getSession(session);
        if(StringUtils.pathEquals(sessionState, state)){
        	
            OAuth20Service oauthService = new ServiceBuilder()
                    .apiKey(CLIENT_ID)
                    .apiSecret(CLIENT_SECRET)
                    .callback(url)
                    .state(state)
                    .build(ZoomDetails.instance());
            /* Scribe에서 제공하는 AccessToken 획득 기능으로 Access Token을 획득 */
            OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
            return accessToken;
        }
        return null;
    }
    
    
    /* 세션 유효성 검증을 위한 난수 생성기 */
    private String generateRandomString() {
        return UUID.randomUUID().toString();
    }
    
    /* http session에 데이터 저장 */
    private void setSession(HttpSession session,String state){
        session.setAttribute(SESSION_STATE, state);     
    }
    /* http session에서 데이터 가져오기 */ 
    private String getSession(HttpSession session){
        return (String) session.getAttribute(SESSION_STATE);
    }
   
    
    
		    /* * * * * * * * * * * * * * * * * * * * *
		     * * * * * * * * API를 호출  * * * * * * * * 
		     * * * * * * * * * * * * * * * * * * * * */
    
    /*회의 리스트 가져오기*/
    public String getUserProfile(OAuth2AccessToken oauthToken) throws IOException{

        OAuth20Service oauthService =new ServiceBuilder()
                .apiKey(CLIENT_ID)
                .apiSecret(CLIENT_SECRET)
                .callback(REDIRECT_URI).build(ZoomDetails.instance());

        OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);

        oauthService.signRequest(oauthToken, request);
        Response response = request.send();
        return response.getBody();
    }
    	
    /*회의 추가*/
    public String setMeeting(OAuth2AccessToken oauthToken,meetingDTO dto) throws IOException{
    	 System.out.println("---- 회의 추가 서비스 ----------");
    	 
        OAuth20Service oauthService =new ServiceBuilder()
                .apiKey(CLIENT_ID)
                .apiSecret(CLIENT_SECRET)
                .callback(REDIRECT_URI).build(ZoomDetails.instance());
        
        OAuthRequest request = new OAuthRequest(Verb.POST, PROFILE_API_URL, oauthService);
        JSONObject jsonOb = new JSONObject();
        
        jsonOb.put("topic", dto.getTopic());
        jsonOb.put("type", 2);
        jsonOb.put("start_time", dto.getStart_time());
        jsonOb.put("duration", dto.getDuration());
        jsonOb.put("password", dto.getPassword());
        
        request.addHeader("Content-Type", "application/json;charset=UTF-8");

        System.out.println("-----json 출력 --"+jsonOb.toString());		
        
        request.addPayload(jsonOb.toString());
        oauthService.signRequest(oauthToken, request);
        Response response = request.send();
        return response.getBody();
    }
    
    /*회의 삭제*/
    public String DelMeeting(OAuth2AccessToken oauthToken,String meetingid) throws IOException{
    	 System.out.println("---- 회의 추가 서비스 ----------");
    	 System.out.println("---- API_URL----------"+DELETE_MEETING_API_URL+meetingid);
        OAuth20Service oauthService =new ServiceBuilder()
                .apiKey(CLIENT_ID)
                .apiSecret(CLIENT_SECRET)
                .callback(REDIRECT_URI).build(ZoomDetails.instance());
        OAuthRequest request = new OAuthRequest(Verb.DELETE, DELETE_MEETING_API_URL+ meetingid, oauthService);
        
        request.addHeader("Content-Type", "application/json;charset=UTF-8");
        JSONObject jsonOb = new JSONObject();
        System.out.println("-----json 출력 --"+jsonOb.toString());		
        
        request.addPayload(jsonOb.toString());
        oauthService.signRequest(oauthToken, request);
        Response response = request.send();
        return response.getBody();
    }
    
    public String PartMeeting(OAuth2AccessToken oauthToken,String meetinguuid) throws IOException{
   	 System.out.println("---- 회의 추가 서비스 ----------");
   	 System.out.println("------ 회의 meetinguuid----------"+meetinguuid);
   	 String url = PARTICIPANTS_API_URL.replace("{meetingUUID}", meetinguuid);
   	 
   	 System.out.println("------ 회의 참여자 정보 ----------"+url);
   	
       OAuth20Service oauthService =new ServiceBuilder()
               .apiKey(CLIENT_ID)
               .apiSecret(CLIENT_SECRET)
               .callback(REDIRECT_URI).build(ZoomDetails.instance());
       OAuthRequest request = new OAuthRequest(Verb.GET, url, oauthService);
       
       request.addHeader("Content-Type", "application/json;charset=UTF-8");
       JSONObject jsonOb = new JSONObject();
       System.out.println("-----json 출력 --"+jsonOb.toString());		
       
       request.addPayload(jsonOb.toString());
       oauthService.signRequest(oauthToken, request);
       Response response = request.send();
       return response.getBody();
   }
   // 
    
}
