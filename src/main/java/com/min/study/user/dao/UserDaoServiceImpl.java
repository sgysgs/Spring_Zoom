package com.min.study.user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDaoService")
public class UserDaoServiceImpl extends SqlSessionDaoSupport implements UserDaoService {

	//회원가입
	@Override
	@Transactional
	public int insertUser(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return getSqlSession().insert("user.insertUser",paramMap);
	}
	
	//로그인
	@Override
	public Map<String, Object> selectUser(String username) {
		// TODO Auto-generated method stub
		System.out.println("-------select--------");
		return getSqlSession().selectOne("user.selectUser",username);
	}
	
	//관리 중인 유저 조회 
		@Override
	public List<HashMap<String, Object>> selectUseradmin(String userid) {
		// TODO Auto-generated method stub
		System.out.println("-------selectUseradmin--------");
		List<HashMap<String, Object>> result = getSqlSession().selectList("user.selectUseradmin",userid);
				
		return result;
	}
	
}
