package kr.co.moments.users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.moments.domain.UsersVO;


@Mapper
public interface UsersMapper {
	int userInsert(UsersVO vo);
	UsersVO findByUserEmail(String user_email);
	int updateRefreshToken(@Param("email") String email, @Param("refreshToken") String refreshToken);
	int deleteRefreshToken(String email);

}
