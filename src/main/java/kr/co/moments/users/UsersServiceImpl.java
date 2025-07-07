package kr.co.moments.users;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.moments.domain.UsersVO;
import kr.co.moments.util.Sha512SaltUtil;


@Service
public class UsersServiceImpl implements UsersService{
	@Autowired
	private UsersMapper mapper;

	@Autowired
    private PasswordEncoder passwordEncoder;

	
	@Override
	@Transactional
	public int userSignUp(Map<String, String> map) {
	   
	  
        UsersVO vo = new UsersVO();
        vo.setUsers_email(map.get("email"));
        vo.setUsers_name(map.get("name"));
        vo.setUsers_phone(map.get("phone"));
        vo.setUsers_pwd(map.get("users_pwd"));
        return mapper.userInsert(vo);
	}
	
	@Override
	public UsersVO findByEmail(String email) {
		return mapper.findByUserEmail(email);
	}
	
	@Override
	public UsersVO authenticate(String userEmail, String rawPassword) {
	  UsersVO user = mapper.findByUserEmail(userEmail);
        if (user != null && passwordEncoder.matches(rawPassword, user.getUsers_pwd())) {
            return user;
        }
        return null;
    }
	@Override
	public void updateRefreshToken(String userEmail, String refreshToken) {
        mapper.updateRefreshToken(userEmail, refreshToken);
    }
	
	@Override
	public void deleteRefreshToken(String userEmail) {
        mapper.deleteRefreshToken(userEmail);
    }
	
	
	
	@Override
    public UsersVO findByUserNo(int userNo) {
        return mapper.selectUserByUserNo(userNo);
    }
	
	//회원정보수정
	@Override
	public int updateUserFieldByUserEmail(String userEmail, String field, String value) {
	    return mapper.updateUserFieldByUserEmail(userEmail, field, value);
	}
	
}	


