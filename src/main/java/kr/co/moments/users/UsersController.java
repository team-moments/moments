package kr.co.moments.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class UsersController {
	@Autowired
	UsersService service;
}
