package kr.co.moments.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService{
	@Autowired
	private GoodsMapper mapper;
}
