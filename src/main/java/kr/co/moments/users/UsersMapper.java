package kr.co.moments.users;
import org.apache.ibatis.annotations.Mapper;

import kr.co.moments.domain.UsersVO;


@Mapper
public interface UsersMapper {
	int userInsert(UsersVO vo);
	UsersVO userSelect(String user_email);

}
