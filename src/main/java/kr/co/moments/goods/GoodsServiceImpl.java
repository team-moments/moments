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


 // 오늘 날짜에 가격 정보가 존재하는지 확인
    @Override
    public int checkDailyPriceExists(String goods_id) {
        return mapper.checkDailyPriceExists(goods_id);
    }

    // 오늘 가격 정보가 없으면 삽입 (상품이 존재하는 경우에만)
    @Override
    public void saveDailyPrice(String goodsId, int price) {
    	System.out.println("▶ saveDailyPrice 호출: " + goodsId);
        
        int goodsExists = mapper.checkGoodsExists(goodsId);
        if (goodsExists == 0) {
            System.out.println("상품이 DB에 존재하지 않음: " + goodsId);
            return; // 상품이 없으면 저장하지 않음
        }

        int priceExists = checkDailyPriceExists(goodsId);
        if (priceExists == 0) { // 가격 정보가 없다면 새로 삽입
            DailyPriceVO dailyPriceVO = new DailyPriceVO();
            dailyPriceVO.setGoods_id(goodsId);
            dailyPriceVO.setPrice(price);            
            
            insertPrice(dailyPriceVO);
        } else {
            System.out.println("이미 오늘 가격 정보가 저장되어 있습니다: " + goodsId);
        }
    }
 // 모든 상품 조회
    @Override
    public List<GoodsVO> getAllGoodsInfo() {
        return mapper.getAllGoods();
    }

	
}
