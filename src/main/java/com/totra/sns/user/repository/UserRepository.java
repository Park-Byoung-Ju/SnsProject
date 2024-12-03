package com.totra.sns.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {

	public int insertUser(@Param("loginId") String loginId
						, @Param("password") String password
						, @Param("name") String name
						, @Param("email") String email
						, @Param("nickname") String nickname);
	
	public int idCheck(@Param("loginId") String loginId);
}
