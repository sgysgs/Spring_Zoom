package com.min.study.zoom.dto;

public class meetingDTO {
	String meetingid=null;
	String topic;
	String start_time;
	String duration;
	String password;
	String join_url;
	String start_url;
	String uuid;
	String userid;
	String type;

	public meetingDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public meetingDTO(String meetingid, String topic, String start_time, String duration, String password, String join_url,
			String start_url,String uuid,String userid,String type) {
		super();
		this.meetingid = meetingid;
		this.topic = topic;
		this.start_time = start_time;
		this.duration = duration;
		this.password = password;
		this.join_url = join_url;
		this.start_url = start_url;
		this.uuid = uuid;
		this.userid = userid;
		this.type = type;
	}
	
	
	public String getMeetingid() {
		return meetingid;
	}

	public void setMeetingid(String meetingid) {
		this.meetingid = meetingid;
	}

	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getJoin_url() {
		return join_url;
	}
	public void setJoin_url(String join_url) {
		this.join_url = join_url;
	}
	public String getStart_url() {
		return start_url;
	}
	public void setStart_url(String start_url) {
		this.start_url = start_url;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	

	
	
	
}
