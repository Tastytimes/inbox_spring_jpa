package com.agni.hotel.hotelmanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agni.hotel.hotelmanagement.POJO.Users;
import com.agni.hotel.hotelmanagement.wrapper.UserWrapper;

import jakarta.transaction.Transactional;
@Repository
public interface UserDao extends JpaRepository<Users, Integer> {
	
	Users findByEmail(String email);
	@Transactional
	@Modifying
	Integer updateStatus(@Param("status") String status, @Param("id") Integer id );

	 List<String> getAllAdmin();
	
//	 @Query(value = "SELECT u.id AS id, u.email AS email, u.name AS name, u.contact_number AS contactNumber, u.status AS status FROM Users u WHERE u.role = 'user'", nativeQuery = true)
//	    List<UserWrapper> findUsersByRole();
	 @Query(name = "User.findUsersByRole")
	    List<UserWrapper> findUsersByRole();
	
//	@Query(value="SELECT id,email,name,contact_number,status FROM Users q WHERE q.role = 'user'", nativeQuery= true)
//	List<UserWrapper> findUsersByRole();
	
//	@Query(value = "SELECT new com.agni.hotel.hotelmanagement.wrapper.UserWrapper(q.id, q.email, q.name, q.contact_number, q.status) FROM Users q WHERE q.role = 'user'", nativeQuery = true)
//	List<UserWrapper> findUsersByRole();
	
//	@Query(value="SELECT * FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery= true)
//	List<Question> findRandomQuestionsByCategory(
//			String category, int numQ);
}
