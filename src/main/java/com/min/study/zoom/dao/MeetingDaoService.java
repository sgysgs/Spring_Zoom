package com.min.study.zoom.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.min.study.zoom.dto.meetingDTO;

public interface MeetingDaoService {

	public int insertMeeting(Map<String, String> paramMap);
	public List<HashMap<String, Object>> selectMeeting(String userid);
	public int deleteMeeting(String meetingid);
	public int insertInvitation(Map<String, String> paramMap);
	public List<HashMap<String, Object>> selectUserInvit(String userid);
	
}
