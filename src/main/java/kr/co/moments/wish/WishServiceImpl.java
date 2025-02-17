package kr.co.moments.wish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishServiceImpl implements WishService{
	
	@Autowired
	private WishMapper mapper;
}
