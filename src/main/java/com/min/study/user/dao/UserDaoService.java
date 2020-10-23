package com.min.study.user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserDaoService {
	
	public int insertUser(Map<String, String> paramMap);
	public Map<String, Object> selectUser(String username);
	public List<HashMap<String, Object>> selectUseradmin(String userid);
}
