package kr.co.moments.main;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.moments.domain.GoodsVO;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private MainMapper mainMapper;


	@Override
	public List<GoodsVO> getAllGoods() {
		return mainMapper.selectAllGoods();
	}

	@Override
	public List<GoodsVO> getGoodsByCategory(int category_no) {
		return mainMapper.selectGoodsByCategory(category_no);  
	}
	
	
	//가격 정렬 2가지 
	public List<GoodsVO> getSortedGoods(String sort) {
	    if ("price_asc".equals(sort)) {
	        return mainMapper.selectGoodsPriceAsc(8);
	    } else if ("price_desc".equals(sort)) {
	        return mainMapper.selectGoodsPriceDesc(8);
	    } else {
	        return mainMapper.selectAllGoods(); // 기본
	    }
	}

	
	//위시순
	public List<GoodsVO> getGoodsWishDesc() {
	    return mainMapper.selectGoodsWishDesc();
	}

	
	
	//카테고리별 가격 정렬 수정2차
	@Override
	public List<GoodsVO> selectGoodsCategorySorted(int category_no, String sort) {
	    switch (sort) {
	        case "price_desc":
	            return mainMapper.selectGoodsByCategoryOrderByPriceDesc(category_no);
	        case "wish_cnt":
	            return mainMapper.selectGoodsByCategoryOrderByWishCountDesc(category_no);
	        case "price_asc":
	        default:
	            return mainMapper.selectGoodsByCategoryOrderByPriceAsc(category_no);
	    }
	}

	 
    //검색 기능 추가

    @Override
    public List<GoodsVO> searchGoods(String keyword, String sort) {
        return mainMapper.searchGoodsByKeyword(keyword, sort);
    }

    
	@Override
	public List<GoodsVO> getGoodsDropDesc() {
	    return mainMapper.selectGoodsDropDesc();
	}
	
}
