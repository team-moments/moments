package kr.co.moments.goods;

import java.util.List;

import kr.co.moments.domain.DailyPriceVO;
import kr.co.moments.domain.GoodsVO;

public interface GoodsService {
public int insertGoods (GoodsVO vo);
public int insertPrice (DailyPriceVO vo);
public List<GoodsVO> getAllGoods();
public List<GoodsVO> getGoodsByCategory(int category_no);

}