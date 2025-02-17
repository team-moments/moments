package kr.co.moments.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GoodsController {

	@Autowired
	GoodsService service;
}
