CREATE TABLE member_tbl (
    id	        varchar2(20),
	pw	        varchar2(20) not null,
	name     varchar2(50) not null,
	gender   char(1) 	  not null,
	email	    varchar2(50) not null,
	mobile    varchar2(13) not null,
	phone	    varchar2(13),
	zip1	    char(5),
	address1 varchar2(300),
	zip2	    char(6),
	address2 varchar2(300),
	birthday date NOT NULL,
	joindate date NOT NULL,
	CONSTRAINT MEMBER_ID_PK PRIMARY KEY(id),
	CONSTRAINT MEMBER_EMAIL_UK UNIQUE(email)
);
    
DROP TABLE member_tbl;