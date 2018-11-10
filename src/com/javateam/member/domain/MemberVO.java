package com.javateam.member.domain;

/**
 * 
 */
import java.sql.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.lang.reflect.Field;

/**
 * @author javateam
 *
 */
public class MemberVO {
	
	private String id;
	private String pw;
	private String name;
	private char gender;
	private String email;
	private String mobile;
	private String phone;
	private String zip1;
	private String address1;
	private String zip2;
	private String address2;
	private Date birthday;
	private Date joindate;

	public MemberVO() {}
	/*
	public MemberVO(Map<String, String[]> map) {
		
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		
		// 전체 인자 인쇄
		map.forEach((k,v)->System.out.println(k+"="+v[0]));
		
		while (it.hasNext()) {
			
			String fldName = it.next();
			
			if (!fldName.equals("submit_btn"))  { // 버튼 필드 제외
				
				// 아이디, 패쓰워드, 이름
				this.id = map.get("id_fld")[0];
				this.pw = map.get("pw_fld")[0];
				this.name = map.get("name_fld")[0];
				
				// 성별
				this.gender = map.get("gender")[0].charAt(0);
							
				// 이메일
				StringBuilder email = new StringBuilder();
				
				String email2 = map.get("email2")[0].equals("사용자 직접 입력") ?
								map.get("email3")[0] : map.get("email2")[0];

				email.append(map.get("email1")[0])
					 .append("@") 	
					 .append(email2);
				this.email = email.toString();
				
				// 휴대폰
				StringBuilder mobile = new StringBuilder();
				mobile.append(map.get("mobile1")[0])
					  .append("-")
					  .append(map.get("mobile2")[0])
					  .append("-")
					  .append(map.get("mobile3")[0]);
				this.mobile = mobile.toString();
				
				// 일반 전화
				StringBuilder phone = new StringBuilder();
				phone.append(map.get("phone1")[0])
					 .append("-")
					 .append(map.get("phone2")[0])
					 .append("-")
					 .append(map.get("phone2")[0]);
				this.phone = phone.toString();
				
				// 우편번호-1
				this.zip1 = map.get("zip1")[0];
				
				// 도로명 주소 조건부 병합 : ex) 기본 주소+"*"+상세주소
				StringBuilder address1 = new StringBuilder();
				address1.append(map.get("roadAddr1")[0])
				        .append("*")
				        .append(map.get("roadAddr2")[0]);
				this.address1 = address1.toString();
				
				// 기존 우편번호
				StringBuilder zip2 = new StringBuilder();
				zip2.append(map.get("zip2_1")[0])
					.append(map.get("zip2_2")[0]);
				this.zip2 = zip2.toString();
				
				// 구 주소 조건부 병합 : ex) 기본 주소+"*"+상세주소
				StringBuilder address2 = new StringBuilder();
				address2.append(map.get("oldAddr1")[0])
				        .append("*")
				        .append(map.get("oldAddr2")[0]);
				this.address2 = address2.toString();
				
				// 생일
				this.birthday = Date.valueOf(map.get("birthday")[0]);
				
				// 가입일
				this.joindate = Date.valueOf(map.get("joindate")[0]);

			} // if
		    
		} // while
		
	} // */

	public MemberVO(Map<String, String[]> map) {
		
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		Field field; // reflection 정보 활용
		
		// 전체 인자 인쇄
		// map.forEach((k,v)->System.out.println(k+"="+v[0]));

		// 보유 멤버 필드 인쇄 : reflection 정보 활용
//		
//		   Field[] flds = this.getClass().getDeclaredFields(); // 멤버 필드들
//		   
//		   for (Field s : flds) {
//			System.out.println(s);
//		}
		 
		while (it.hasNext()) {
			
			String fldName = it.next();
			
			if (!fldName.equals("submit_btn"))  { // 버튼 필드 제외
				
			    try {
			    		// VO(MemberVO)와 1:1 대응되는 필드들 처리 
			    		// 주의) 현재 상황과 같이 VO와 전송인자들이 
  			    	    // 불일치하는 경우가 많은 경우는 코드량을 줄일 수 없음.   
				    	try {
								field = this.getClass().getDeclaredField(fldName);
								// System.out.println(fldName + " : "+field.getName());
								field.setAccessible(true);
								// System.out.println("field : "+field);
								
								// 성별, 생일, 가입일
								if (fldName.equals("gender")) {
									field.set(this, map.get(fldName)[0].charAt(0));
								} else if (fldName.equals("birthday")) {
									field.set(this, Date.valueOf(map.get(fldName)[0]));
								} else if (fldName.equals("joindate"))
									field.set(this, Date.valueOf(map.get(fldName)[0]));
								else {
									field.set(this, map.get(fldName)[0]);
								}
								
						} catch (NoSuchFieldException e) {
							
							// VO(MemberVo)와 1:1 대응되지 않는 필드들 처리 : 예외 발생
							
							// 아이디, 패쓰워드, 이름
							this.id = map.get("id_fld")[0];
							this.pw = map.get("pw_fld")[0];
							this.name = map.get("name_fld")[0];
							
							// 이메일
							StringBuilder email = new StringBuilder();
							
							String email2 = map.get("email2")[0].equals("사용자 직접 입력") ?
											map.get("email3")[0] : map.get("email2")[0];

							email.append(map.get("email1")[0])
								 .append("@") 	
								 .append(email2);
							this.email = email.toString();
							
							// 휴대폰
							StringBuilder mobile = new StringBuilder();
							mobile.append(map.get("mobile1")[0])
								  .append("-")
								  .append(map.get("mobile2")[0])
								  .append("-")
								  .append(map.get("mobile3")[0]);
							
							this.mobile = mobile.toString();
							
							// 일반 전화
							StringBuilder phone = new StringBuilder();
							phone.append(map.get("phone1")[0])
								 .append("-")
								 .append(map.get("phone2")[0])
								 .append("-")
								 .append(map.get("phone2")[0]);
							
							this.phone = phone.toString();
							
							// 도로명 주소 조건부 병합 : ex) 기본 주소+"*"+상세주소
							StringBuilder address1 = new StringBuilder();
							address1.append(map.get("roadAddr1")[0])
							        .append("*")
							        .append(map.get("roadAddr2")[0]);
							
							this.address1 = address1.toString();
							
							// 기존 우편번호
							StringBuilder zip2 = new StringBuilder();
							zip2.append(map.get("zip2_1")[0])
								.append(map.get("zip2_2")[0]);
							this.zip2 = zip2.toString();
							
							// 구 주소 조건부 병합 : ex) 기본 주소+"*"+상세주소
							StringBuilder address2 = new StringBuilder();
							address2.append(map.get("oldAddr1")[0])
							        .append("*")
							        .append(map.get("oldAddr2")[0]);
							
							this.address2 = address2.toString();

						} // try
						
				} catch (SecurityException |
						 IllegalArgumentException | 
						 IllegalAccessException e) 
			    {
					e.printStackTrace();
				} // try
		    
			} // if
		    
		} // while
		
	} 
	
	// 회원정보 갱신시 기존의 정보(session)와 신규 정보를 비교하여 신규정보를 반영
	public MemberVO(MemberVO oldMember, MemberVO newMember) {
		
		// 구 객체간 비교 다른 점이 있으면 갱신과정 시작
		if (oldMember.equals(newMember) == false) {
			
			// 갱신할 필드들 선별
			
			
		} else {
			
		} //
	} //

	public MemberVO(String id, String pw, String name, char gender, String email, String mobile) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.mobile = mobile;
	}

	public MemberVO(String id, String pw, String name, char gender, String email, String mobile, String phone,
			String zip1, String address1, String zip2, String address2, Date birthday, Date joindate) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.mobile = mobile;
		this.phone = phone;
		this.zip1 = zip1;
		this.address1 = address1;
		this.zip2 = zip2;
		this.address2 = address2;
		this.birthday = birthday;
		this.joindate = joindate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberVO [id=");
		builder.append(id);
		builder.append(", pw=");
		builder.append(pw);
		builder.append(", name=");
		builder.append(name);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", email=");
		builder.append(email);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", zip1=");
		builder.append(zip1);
		builder.append(", address1=");
		builder.append(address1);
		builder.append(", zip2=");
		builder.append(zip2);
		builder.append(", address2=");
		builder.append(address2);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append(", joindate=");
		builder.append(joindate);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address1 == null) ? 0 : address1.hashCode());
		result = prime * result + ((address2 == null) ? 0 : address2.hashCode());
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + gender;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((joindate == null) ? 0 : joindate.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((pw == null) ? 0 : pw.hashCode());
		result = prime * result + ((zip1 == null) ? 0 : zip1.hashCode());
		result = prime * result + ((zip2 == null) ? 0 : zip2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MemberVO)) {
			return false;
		}
		MemberVO other = (MemberVO) obj;
		if (address1 == null) {
			if (other.address1 != null) {
				return false;
			}
		} else if (!address1.equals(other.address1)) {
			return false;
		}
		if (address2 == null) {
			if (other.address2 != null) {
				return false;
			}
		} else if (!address2.equals(other.address2)) {
			return false;
		}
		if (birthday == null) {
			if (other.birthday != null) {
				return false;
			}
		} else if (!birthday.equals(other.birthday)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (gender != other.gender) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (joindate == null) {
			if (other.joindate != null) {
				return false;
			}
		} else if (!joindate.equals(other.joindate)) {
			return false;
		}
		if (mobile == null) {
			if (other.mobile != null) {
				return false;
			}
		} else if (!mobile.equals(other.mobile)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (phone == null) {
			if (other.phone != null) {
				return false;
			}
		} else if (!phone.equals(other.phone)) {
			return false;
		}
		if (pw == null) {
			if (other.pw != null) {
				return false;
			}
		} else if (!pw.equals(other.pw)) {
			return false;
		}
		if (zip1 == null) {
			if (other.zip1 != null) {
				return false;
			}
		} else if (!zip1.equals(other.zip1)) {
			return false;
		}
		if (zip2 == null) {
			if (other.zip2 != null) {
				return false;
			}
		} else if (!zip2.equals(other.zip2)) {
			return false;
		}
		return true;
	}
	
	// 두 객체간 차이를 확인하여 갱신할 필드들을 선별
	public void compareUpdateFields(MemberVO other) {
		
		if (address1 == null) {
			if (other.address1 != null) {
				this.address1 = other.address1; 
			}
		} else if (!address1.equals(other.address1)) {
			this.address1 = other.address1; 
		}
		if (address2 == null) {
			if (other.address2 != null) {
				this.address2 = other.address2; 
			}
		} else if (!address2.equals(other.address2)) {
			this.address2 = other.address2; 
		}
		if (birthday == null) {
			if (other.birthday != null) {
				this.birthday = other.birthday; 
			}
		} else if (!birthday.equals(other.birthday)) {
			this.birthday = other.birthday; 
		}
		if (email == null) {
			if (other.email != null) {
				this.email = other.email; 
			}
		} else if (!email.equals(other.email)) {
			this.email = other.email;
		}
				
		if (mobile == null) {
			if (other.mobile != null) {
				this.mobile = other.mobile;
			}
		} else if (!mobile.equals(other.mobile)) {
			this.mobile = other.mobile;
		}
		if (name == null) {
			if (other.name != null) {
				this.name = other.name;
			}
		} else if (!name.equals(other.name)) {
			this.name = other.name;
		}
		if (phone == null) {
			if (other.phone != null) {
				this.phone =  other.phone;
			}
		} else if (!phone.equals(other.phone)) {
			this.phone = other.phone;
		}
		
		// 변경
		if (pw == null) {
			
			this.pw = other.pw;

		} else if (other.pw.equals("")) { // 조건 추가
			System.out.println("신규 패쓰워드 : "+other.pw);
			// this.pw = other.pw;
		
		} else if (!pw.equals(other.pw)) {
			this.pw = other.pw;
		} //
		
		if (zip1 == null) {
			if (other.zip1 != null) {
				this.zip1 = other.zip1;
			}
		} else if (!zip1.equals(other.zip1)) {
			this.zip1 = other.zip1;
		}
		if (zip2 == null) {
			if (other.zip2 != null) {
				this.zip2 = other.zip2;
			}
		} else if (!zip2.equals(other.zip2)) {
			this.zip2 = other.zip2;
		}
		
	} //

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZip1() {
		return zip1;
	}

	public void setZip1(String zip1) {
		this.zip1 = zip1;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getZip2() {
		return zip2;
	}

	public void setZip2(String zip2) {
		this.zip2 = zip2;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	
}