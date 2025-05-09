package kr.co.moments.users;

import java.util.Map;

import kr.co.moments.domain.UsersVO;

public interface UsersService {
	int userSignUp (Map<String, String> map);
	UsersVO findByEmail(String email);

}
