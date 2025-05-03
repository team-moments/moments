package kr.co.moments.goods;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moments.domain.DailyPriceVO;
import kr.co.moments.domain.GoodsVO;


@Mapper
public interface GoodsMapper {
   public int goodsInsert (GoodsVO vo);
   public int dailyPriceInsert (DailyPriceVO vo);
   public List<GoodsVO> getAllGoods();

}