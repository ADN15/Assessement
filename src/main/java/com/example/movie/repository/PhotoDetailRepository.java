package com.example.movie.repository;

import java.sql.Timestamp;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import com.example.movie.model.PhotoDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface PhotoDetailRepository extends JpaRepository<PhotoDetail,String> {
	
	List<PhotoDetail> findAllByUserId(String userId);
	
	PhotoDetail findOneByTitle (String title);
}