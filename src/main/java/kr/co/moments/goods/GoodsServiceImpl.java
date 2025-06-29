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

//    @Override
//    public List<GoodsVO> getAllGoods() {
//        return mapper.selectAllGoods();
//    }
//
//
//    @Override
//    public List<GoodsVO> getGoodsByCategory(int category_no) {
//         return mapper.selectGoodsByCategory(category_no);  
//    }


	//06.28
    @Override
    public GoodsVO getGoodsById(String goodsId) {
        return mapper.selectGoodsById(goodsId);
    }
    
    public List<GoodsVO> getSortedGoods(String sort) {
        switch (sort) {
            case "drop_rate":
                return mapper.getGoodsOrderByDropRate();
            case "price_asc":
                return mapper.getGoodsOrderByPriceAsc();
            // 필요한 정렬 추가
            default:
                throw new IllegalArgumentException("지원하지 않는 정렬 방식입니다: " + sort);
        }
    }



}
