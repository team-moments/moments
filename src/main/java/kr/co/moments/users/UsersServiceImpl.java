package kr.co.moments.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsersServiceImpl implements UsersService{
	@Autowired
	private UsersMapper mapper;
}	