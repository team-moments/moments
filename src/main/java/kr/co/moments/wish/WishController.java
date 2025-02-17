package kr.co.moments.wish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class WishController {
	@Autowired
	WishService service;
}
