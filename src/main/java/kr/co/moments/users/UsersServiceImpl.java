package kr.co.moments.users;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.moments.domain.UsersVO;
import kr.co.moments.util.Sha512SaltUtil;


@Service
public class UsersServiceImpl implements UsersService{
	@Autowired
	private UsersMapper mapper;
	
	@Override
	@Transactional
	public int userSignUp(Map<String, String> map) {
	   
	    String rawPwd = map.get("password");

        // 1) 랜덤 문자열 Salt 생성 (예: 16글자)
        String salt = Sha512SaltUtil.generateSalt(16);
        // 2) salt + pwd → SHA-512 해시(hex 문자열)
        String hashed = Sha512SaltUtil.hashWithSalt(salt, rawPwd);

        // 3) DB에 저장 (salt, hashedPassword 모두 String)
        UsersVO vo = new UsersVO();
        vo.setUsers_email(map.get("email"));
        vo.setUsers_name(map.get("name"));
        vo.setUsers_phone(map.get("phoneNumber"));
        vo.setUsers_pwd(hashed);
        vo.setUsers_salt(salt);
        return mapper.userInsert(vo);
	}
	
	@Override
	public UsersVO findByEmail(String email) {
		return mapper.userSelect(email);
	}
	
	
}	


