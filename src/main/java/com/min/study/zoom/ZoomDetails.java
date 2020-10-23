package com.min.study.zoom;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.min.study.zoom.dto.meetingDTO;


public class ZoomDetails extends DefaultApi20{

	
	private final static String CLIENT_ID = "cXdlrE_PQaNS5QdUeGdlg";
    private final static String CLIENT_SECRET = "wRnJbOQsdOnR5KTUR6Uz4e41Ixr5Im1j";         
    /*REDIRECT URL*/
    private final static String REDIRECT_URI = "https://f2cde7541078.ngrok.io/redirect";
    private final static String REDIRECT_URI_CREATE = "https://f2cde7541078.ngrok.io/create/meetings/redirect";
    private final static String REDIRECT_URI_PARTICIPANTS = "https://f2cde7541078.ngrok.io/participants/meetings/redirect";
    private final static String REDIRECT_URI_DELETE = "https://f2cde7541078.ngrok.io/delete/meetings/redirect";
    
    private final static String SESSION_STATE = "oauth_state";
    private static String ZOOM_STATE = null;
    /*API 요청 URL*/
    private final static String PROFILE_API_URL = "https://api.zoom.us/v2/users/me/meetings";
    private static String DELETE_MEETING_API_URL = "https://api.zoom.us/v2/meetings/";//+meetingId
    private final static String PARTICIPANTS_API_URL = "https://f2cde7541078.ngrok.io/past_meetings/{meetingUUID}/participants";
	private final static String Access_Token = "https://zoom.us/oauth/token?grant_type=authorization_code";
	private final static String Authorization_Url = "https://zoom.us/oauth/authorize";
	
	
    public ZoomDetails(){
    	
    }
    
    private static class InstanceHolder{
        private static final ZoomDetails INSTANCE = new ZoomDetails();
    }

    public static ZoomDetails instance(){
        return InstanceHolder.INSTANCE;
    }
    
    @Override
    public String getAccessTokenEndpoint() {
        return Access_Token;
    }
    
    @Override
    protected String getAuthorizationBaseUrl() {
        // TODO Auto-generated method stub
        return Authorization_Url;
    }
    

    public static String getClientId() {
		return CLIENT_ID;
	}


	public static String getClientSecret() {
		return CLIENT_SECRET;
	}



	public static String getSessionState() {
		return SESSION_STATE;
	}
	
	 /*API 요청 URL*/
	public static String getProfileApiUrl() {
		return PROFILE_API_URL;
	}
	
	public static String getDELETE_MEETING_API_URL() {
		return DELETE_MEETING_API_URL;
	}

	public static void setDELETE_MEETING_API_URL(String dELETE_MEETING_API_URL) {
		DELETE_MEETING_API_URL = dELETE_MEETING_API_URL;
	}

	
	public static String getParticipantsApiUrl() {
		return PARTICIPANTS_API_URL;
	}

	
	/*REDIRECT URL*/

	public static String getRedirectUri() {
		return REDIRECT_URI;
	}

	public static String getRedirectUriCreate() {
		return REDIRECT_URI_CREATE;
	}

	
	public static String getRedirectUriDelete() {
		return REDIRECT_URI_DELETE;
	}
	
	
	public static String getRedirectUriParticipants() {
		return REDIRECT_URI_PARTICIPANTS;
	}
	
	
	public static String getZOOM_STATE() {
		return ZOOM_STATE;
	}

	public static void setZOOM_STATE(String zOOM_STATE) {
		ZOOM_STATE = zOOM_STATE;
	}

	
	
	

}