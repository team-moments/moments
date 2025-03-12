package kr.co.moments.goods;

import kr.co.moments.domain.DailyPriceVO;
import kr.co.moments.domain.GoodsVO;

public interface GoodsService {
	public int insertGoods (GoodsVO vo);
	public int insertPrice (DailyPriceVO vo);
}
