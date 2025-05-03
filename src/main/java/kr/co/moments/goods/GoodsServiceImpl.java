package kr.co.moments.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moments.domain.DailyPriceVO;
import kr.co.moments.domain.GoodsVO;

@Service
public class GoodsServiceImpl implements GoodsService{
	@Autowired
	private GoodsMapper mapper;
	
	@Override
	public int insertGoods(GoodsVO vo) {
		return mapper.goodsInsert(vo);
	}
	
	@Override
	public int insertPrice(DailyPriceVO vo) {
		return mapper.dailyPriceInsert(vo);
	}

    @Override
    public List<GoodsVO> getAllGoods() {
        return mapper.selectAllGoods();
    }

    @Override
    public List<GoodsVO> getGoodsByCategory(int category_no) {
         return mapper.selectGoodsByCategory(category_no);  
    }


	
}
