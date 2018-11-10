INSERT INTO member_tbl
 VALUES
 ('spring1234', 
  '#Abcd1234',
  '홍길동',
  'm',
  'abcd@abcd.com',
  '010-1234-5678',
  '02-6925-4760 ',
  '08390',
  '서울특별시 구로구 디지털로30길 31(구로동)*오라클자바 교육센터',
  '152775',
  '서울특별시 구로구 구로동 222-8 코오롱디지털타워빌란트Ⅱ*오라클자바 교육센터',  
  '1990-1-1',
   SYSDATE);
   
SELECT * FROM member_tbl WHERE id='spring1234';   

DELETE member_tbl WHERE id='spring1234';

UPDATE member_tbl SET
    pw='#Java1234',
    name='홍수환',
    email='oj@naver.com',
    mobile='010-2323-5454',
    phone='02-6941-4761',
    zip1='08381',
    address1= '서울특별시 구로구 디지털로 271(구로동)*아이티런 교육센터',
    zip2='152775',
    address2= '서울특별시 구로구 구로동 212-13 벽산디지털밸리Ⅲ*아이티런 교육센터'
WHERE id='spring1234';

-------------------------------------------------------

UPDATE member_tbl SET
    pw='#Java1234',
    name='홍수환',
    email='oj@naver.com',
    mobile='010-2323-5454',
    phone='',
    zip1='',
    address1= '',
    zip2='',
    address2= ''
WHERE id='abcd1234';

------------------------------------------------------------

UPDATE member_tbl SET
    pw='#Java1234',
    name='홍수환',
    email='oj@naver.com',
    mobile='010-2323-5454',
    phone='02-6941-4761',
    zip1='08381',
    address1= '서울특별시 구로구 디지털로 271(구로동)*아이티런 교육센터',
    zip2='152775',
    address2= '서울특별시 구로구 구로동 212-13 벽산디지털밸리Ⅲ*아이티런 교육센터'
WHERE id='abcd1234';

commit;

---------------------------------------------------------------------

INSERT INTO member_tbl
 VALUES
 ('java1234', 
  '#Abcd1234',
  '홍길동',
  'm',
  'java@javajava.com',
  '010-1234-5678',
  '02-6925-4760 ',
  '08390',
  '서울특별시 구로구 디지털로30길 31(구로동)*오라클자바 교육센터',
  '152775',
  '서울특별시 구로구 구로동 222-8 코오롱디지털타워빌란트Ⅱ*오라클자바 교육센터',  
  '1990-1-1',
   SYSDATE);
   
INSERT INTO member_tbl
 VALUES
 ('spring1234', 
  '#Abcd1234',
  '홍길동',
  'm',
  'spring@spring.com',
  '010-1234-5678',
  '02-6925-4760 ',
  '08390',
  '서울특별시 구로구 디지털로30길 31(구로동)*오라클자바 교육센터',
  '152775',
  '서울특별시 구로구 구로동 222-8 코오롱디지털타워빌란트Ⅱ*오라클자바 교육센터',  
  '1990-1-1',
   SYSDATE);   
   
---------------------------------------------------

SELECT * 
   FROM (SELECT ROWNUM,  
	       		  m.*, 
   			 FLOOR((ROWNUM - 1)/5 + 1) page 
   			 FROM ( 
   			 		SELECT * FROM member_tbl 
                    WHERE name='홍길동'
   			        ORDER BY id ASC 
   			       ) m 
   	     ) 
WHERE page = 2; 

----------------------------------------------------

SELECT id FROM member_tbl
WHERE email='servlet1@abcd.com' 
  AND mobile='010-1234-1113';