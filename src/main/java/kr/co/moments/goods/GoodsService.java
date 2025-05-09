package kr.co.moments.goods;

import java.util.List;

import kr.co.moments.domain.DailyPriceVO;
import kr.co.moments.domain.GoodsVO;

public interface GoodsService {
public int insertGoods (GoodsVO vo);
public int insertPrice (DailyPriceVO vo);
public List<GoodsVO> getAllGoods();
public List<GoodsVO> getGoodsByCategory(int category_no);

//오늘 날짜에 가격 정보가 존재하는지 확인
int checkDailyPriceExists(String goods_id);

// 오늘 가격 정보가 없으면 삽입하는 메서드
void saveDailyPrice(String goodsId, int price);
List<GoodsVO> getAllGoodsInfo(); // 모든 상품 정보 조회
}