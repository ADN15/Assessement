package com.example.movie.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.GenericGenerator;


/**
 * @author 10991981
 *
 */
@Entity
@Table(name="photo_detail")
@Data
@EqualsAndHashCode(callSuper=false)
public class PhotoDetail implements Serializable {
	
	private static final long serialVersionUID = -4853555399538625007L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid", strategy="uuid2")
	@Column(name="photo_id", nullable=false)
	private String photoId;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "server_id")
	private String serverId;
	
	@Column(name = "title")
	private String title;
	
}