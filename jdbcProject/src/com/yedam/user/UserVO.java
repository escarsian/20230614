package com.yedam.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   // lombok
@AllArgsConstructor //
@NoArgsConstructor //기본생성자
public class UserVO {
	private String userId;
	private String userPw;
	private String userName;
	private String userBirth;
	private String userPhone;
	private String userAddr;
	
}
