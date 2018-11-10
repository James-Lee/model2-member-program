-- dummy member generator

DELETE member_tbl; 

CREATE OR REPLACE PROCEDURE dummy_gen_proc
IS
BEGIN

    FOR i IN 1..100 LOOP
        INSERT INTO member_tbl VALUES 
        ('servlet' || (1112+i),
         '#Servlet1234', 
         '서블릿' || (1112+i), 
         'm', 
         'servlet' || i || '@abcd.com',
          '010-1234-' || (1112+i), 
          '02-6925-' || (1112+i),  
          '12345',  '부천*학교',  
          '123456',  '서울 구로*학원',  
          '1990-1-1',  SYSDATE);
     END LOOP;

    COMMIT;    
END;
/

EXECUTE dummy_gen_proc;


CREATE OR REPLACE PROCEDURE dummy_update_proc
IS
BEGIN

    UPDATE member_tbl SET
         address1='경기도 부천시 부천대학교*정보통신과',
         address2='서울시 구로구 구로동 코오롱디지털빌란트 2* 오라클자바교육학원';          

    COMMIT;    
END;
/

EXECUTE dummy_update_proc;

------------------------------------------------------

CREATE SEQUENCE member_role_id_seq
START WITH 1
MAXVALUE 999
NOCYCLE;

CREATE TABLE  member_role (   
    role_id number(4) default 0, 
    id varchar2(20),
    degree number(1) default 0,
    constraint member_role_pk primary key(role_id),
    constraint member_role_fk foreign key(id) references  member_tbl(id)
);

CREATE OR REPLACE PROCEDURE dummy_role_gen_proc
IS
BEGIN

    FOR i IN 1..100 LOOP
        INSERT INTO member_role VALUES 
        ( member_role_id_seq.nextval,
         'servlet' || (1112+i),
         0);
     END LOOP;

    COMMIT;    
END;
/

EXECUTE dummy_role_gen_proc;