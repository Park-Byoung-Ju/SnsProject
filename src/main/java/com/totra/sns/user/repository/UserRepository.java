package com.totra.sns.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.totra.sns.user.domain.User;

@Mapper
public interface UserRepository {

	public int insertUser(@Param("loginId") String loginId
						, @Param("password") String password
						, @Param("name") String name
						, @Param("email") String email
						, @Param("nickname") String nickname);
	
	public int idCheck(@Param("loginId") String loginId);
	
	public User login(@Param("loginId") String loginId
					,@Param("password") String password);
	
	public User readUser(@Param("id") int id);
}
