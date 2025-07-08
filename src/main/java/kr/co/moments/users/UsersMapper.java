package kr.co.moments.users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.moments.domain.UsersVO;


@Mapper
public interface UsersMapper {
	int userInsert(UsersVO vo);
	UsersVO userSelect(String user_email);

	
	
	//회원정보수정
	int updateUserFieldByUserEmail(@Param("userEmail") String userEmail,
            @Param("field") String field,
            @Param("value") String value);
	
	UsersVO selectUserByUserNo(int userNo);


}
