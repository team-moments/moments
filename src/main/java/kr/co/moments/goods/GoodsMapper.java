package kr.co.moments.goods;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.moments.domain.DailyPriceVO;
import kr.co.moments.domain.GoodsVO;


@Mapper
public interface GoodsMapper {
   public int goodsInsert (GoodsVO vo);
   public int dailyPriceInsert (DailyPriceVO vo);

   // 상품 목록을 가져오는 SQL 쿼리 실행
   List<GoodsVO> selectAllGoods();
   List<GoodsVO> selectGoodsByCategory(@Param("category_no") int getGoodsByCategory);

   int checkGoodsExists(String goods_id);
	int checkDailyPriceExists(String goods_id); // 오늘 날짜 가격 존재 여부 확인
   List<GoodsVO> getAllGoods(); // 모든 상품 정보 조회

}