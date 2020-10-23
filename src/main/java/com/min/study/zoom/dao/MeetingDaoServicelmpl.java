package com.min.study.zoom.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import org.springframework.transaction.annotation.Transactional;

import com.min.study.zoom.dto.meetingDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("MeetingDaoSerivce")
public class MeetingDaoServicelmpl extends SqlSessionDaoSupport implements MeetingDaoService{
	
		meetingDTO dto = new meetingDTO();
		HashMap<String, Object> map = new HashMap<String, Object>();
		//회의 등록
		@Transactional
		@Override
		public int insertMeeting(Map<String, String> paramMap) {
			// TODO Auto-generated method stub
			return getSqlSession().insert("meetings.insertMeeting",paramMap);
		}
		//초대 등록
		public int insertInvitation(Map<String, String> paramMap) {
			// TODO Auto-generated method stub
			return getSqlSession().insert("meetings.insertInvitation",paramMap);
		}
		
		
		//회의 조회
		@Override
		public List<HashMap<String, Object>> selectMeeting(String userid) {
			// TODO Auto-generated method stub
			System.out.println("-------select--------");
			List<HashMap<String, Object>> result = getSqlSession().selectList("meetings.selectMeeting",userid);
			
			return result;
		}
		
		//유저 회의 초대장 조회
		public List<HashMap<String, Object>> selectUserInvit(String userid) {
			// TODO Auto-generated method stub
			List<HashMap<String, Object>> result = getSqlSession().selectList("meetings.selectUserInvit",userid);
			
			return result;
		}
		
		
		//회의삭제
		public int deleteMeeting(String uuid) {
			// TODO Auto-generated method stub
			return getSqlSession().delete("deleteMeeting",uuid);
		}
		
	
}
