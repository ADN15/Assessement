CREATE TABLE "flickr".photo_detail (
	photo_id varchar(255) NOT NULL,
	user_id varchar(50) NULL,
	server_id varchar(225) NULL,
	title varchar(225) NULL,
	CONSTRAINT poto_id_pk PRIMARY KEY (photo_id)
);