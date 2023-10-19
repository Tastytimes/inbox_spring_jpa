package com.agni.hotel.hotelmanagement.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.agni.hotel.hotelmanagement.JWT.CustomerUsersDetailsService;
import com.agni.hotel.hotelmanagement.JWT.JWTUtill;
import com.agni.hotel.hotelmanagement.JWT.JwtFilter;
import com.agni.hotel.hotelmanagement.POJO.Users;
import com.agni.hotel.hotelmanagement.dao.UserDao;
import com.agni.hotel.hotelmanagement.service.UserService;
import com.agni.hotel.hotelmanagement.utills.HotelUtills;
import com.agni.hotel.hotelmanagement.wrapper.UserWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	CustomerUsersDetailsService customerUsersDetailsService;
	
	@Autowired
	JWTUtill jwtUtill;
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		log.info("inside sign up {}", requestMap);
		try {
			if(validateSignupMap(requestMap)) {
				Users users = userDao.findByEmail(requestMap.get("email"));
				if(Objects.isNull(users)) {
					Users u = getUsersFromMap(requestMap);
					userDao.save(u);
					return HotelUtills.getResponseEntity("Successfully registered", HttpStatus.CREATED);
					
				}else {
					HotelUtills.getResponseEntity("Email Already registered with us", HttpStatus.BAD_REQUEST);
				}
			}else {
				return HotelUtills.getResponseEntity("somefileds are missing", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return HotelUtills.getResponseEntity("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private boolean validateSignupMap(Map<String, String> requestMap) {
		if(requestMap.containsKey("name") && requestMap.containsKey("email") && requestMap.containsKey("contactNumber") && requestMap.containsKey("password")){
			return true;
		}
		return false;
	}
	
	private Users getUsersFromMap(Map<String, String> requestMap) {
		Users users = new Users();
		users.setName(requestMap.get("name"));
		users.setEmail(requestMap.get("email"));
		users.setContactNumber(requestMap.get("contactNumber"));
		users.setPassword(new BCryptPasswordEncoder().encode(requestMap.get("password")));
//		users.setPassword(requestMap.get("password"));
		users.setStatus("false");
		users.setRole("user");
		log.info("users sign up {}", users);
		return users;
	}

	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		log.info("inside login {}", requestMap);
		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password")));
			Authentication auth =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password")));
			log.info("auth---- {}", authenticationManager);
			System.out.println(auth.isAuthenticated());
			if(auth.isAuthenticated()) {
				log.info("customer details {}",customerUsersDetailsService.getuserDetail().getStatus().equalsIgnoreCase("true"));
				if(customerUsersDetailsService.getuserDetail().getStatus().equalsIgnoreCase("true")) {
					String generatedtoken = jwtUtill.generateToken(customerUsersDetailsService.getuserDetail().getEmail(), customerUsersDetailsService.getuserDetail().getRole());
					log.info("token {}", generatedtoken);
					return new ResponseEntity<String>(generatedtoken, HttpStatus.OK);
				}else {
					return new ResponseEntity<String>("Wait for admin approval", HttpStatus.BAD_REQUEST);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("{}", e);
		}
		return HotelUtills.getResponseEntity("Bad Request!", HttpStatus.BAD_REQUEST);
	}
	
//	public ResponseEntity<List<UserWrapper>> getAllUsers(){
//		
//	}

	@Override
	public ResponseEntity<List<UserWrapper>> getAllusers() {
		try {
			if(jwtFilter.isAdmin()) {
				List<Users> resp = userDao.findAll();
				List<UserWrapper> aa = userDao.findUsersByRole();
				log.info("named query {}", aa);
				ArrayList<UserWrapper> results = new ArrayList<UserWrapper>();
//			UserWrapper users = new UserWrapper();
				for (Users user : resp) {
					UserWrapper userWrapper = new UserWrapper(
							user.getId(),
							user.getEmail(),
							user.getName(),
							user.getContactNumber(),
							user.getStatus()
							);
					results.add(userWrapper);
				}
				return new ResponseEntity<List<UserWrapper>>(aa, HttpStatus.OK);
			}else {
				return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateUser(Map<String, String> requestMap) {
		try {
			if(jwtFilter.isAdmin()) {
				Optional<Users> users = userDao.findById(Integer.parseInt(requestMap.get("id")));
				if(!users.isEmpty()) {
					userDao.updateStatus(requestMap.get("status"),Integer.parseInt(requestMap.get("id")));
					sendMailToAllAdmin(requestMap.get("status"), users.get().getEmail(),userDao.getAllAdmin());
					return new ResponseEntity<String>("User Status has updated successfully", HttpStatus.OK);
				}else {
					return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
				}
			}else {
				return new ResponseEntity<String>("Dont have aceess for update", HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return HotelUtills.getResponseEntity("Bad Request!", HttpStatus.BAD_REQUEST);
	}

	private void sendMailToAllAdmin(String string, String email, List<String> allAdmin) {
		// TODO Auto-generated method stub
		log.info("email list {}", allAdmin);
		for (String user : allAdmin) {
			log.info("inside sendMailToAllAdmin {}", user);
		}
		allAdmin.remove(jwtFilter.getCurrentUser());
		log.info("removing {}", allAdmin);
	}

	@Override
	public ResponseEntity<String> checkToken() {
		try {
			return HotelUtills.getResponseEntity("true", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return HotelUtills.getResponseEntity("Bad Request!", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
		try {
			log.info("currentUser {}", jwtFilter.getCurrentUser());
			Users user = userDao.findByEmail(jwtFilter.getCurrentUser());
			if(!user.equals(null)) {
				if(new BCryptPasswordEncoder().matches(requestMap.get("oldPassword"), user.getPassword())){
					user.setPassword(new BCryptPasswordEncoder().encode(requestMap.get("newPassword")));
					userDao.save(user);
					return new ResponseEntity<String>("Password has changed successfully", HttpStatus.OK);
				}else {
					return new ResponseEntity<String>("Incorrect password", HttpStatus.BAD_REQUEST);
				}
			}else {
				return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return HotelUtills.getResponseEntity("Bad Request!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
