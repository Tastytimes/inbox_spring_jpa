package com.agni.hotel.hotelmanagement.JWT;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agni.hotel.hotelmanagement.dao.UserDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerUsersDetailsService implements UserDetailsService {

	@Autowired
	UserDao userDao;
	private com.agni.hotel.hotelmanagement.POJO.Users userDetail;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		userDetail = userDao.findByEmail(username);
		if(!Objects.isNull(userDetail))
			return new User(userDetail.getEmail(),userDetail.getPassword(), new ArrayList<>());
			else
				throw new UsernameNotFoundException("user not found");
		
	}
	
	public com.agni.hotel.hotelmanagement.POJO.Users getuserDetail() {
		return userDetail;
	}

	
}
