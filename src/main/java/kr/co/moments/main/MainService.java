package kr.co.moments.main;

import java.util.List;
import kr.co.moments.domain.GoodsVO;

public interface MainService {
	public List<GoodsVO> getAllGoods();
	public List<GoodsVO> getGoodsByCategory(int category_no);
	
	public List<GoodsVO> getGoodsWishDesc(); //위시순 정렬 
	
	public List<GoodsVO> getSortedGoods(String sort);  // 전체 상품 + 정렬
	public List<GoodsVO> selectGoodsCategorySorted(int category_no, String sort); // 카테고리별 + 정렬

	public List<GoodsVO> getGoodsDropDesc();
	
	public List<GoodsVO> searchGoods(String keyword, String sort); // 검색 + 정렬  -> 아직 기능 미완성 


}
