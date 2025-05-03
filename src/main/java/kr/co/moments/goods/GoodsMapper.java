package kr.co.moments.goods;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moments.domain.DailyPriceVO;
import kr.co.moments.domain.GoodsVO;


@Mapper
public interface GoodsMapper {
   public int goodsInsert (GoodsVO vo);
   public int dailyPriceInsert (DailyPriceVO vo);

   // 상품 목록을 가져오는 SQL 쿼리 실행
   List<GoodsVO> selectAllGoods();

}