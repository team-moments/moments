package kr.co.moments.main;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.moments.domain.GoodsVO;


@Mapper
public interface MainMapper {
	List<GoodsVO> getGoodsOrderByDropRate();
    List<GoodsVO> getGoodsOrderByPriceAsc();
    
    
	List<GoodsVO> selectAllGoods();
	List<GoodsVO> selectGoodsByCategory(int category_no);
	
	//가격순 정렬 - 카테고리별(수정 추가)
	List<GoodsVO> selectGoodsByCategoryOrderByPriceAsc(int category_no);
	List<GoodsVO> selectGoodsByCategoryOrderByPriceDesc(int category_no);
	List<GoodsVO> selectGoodsByCategoryOrderByWishCountDesc(int category_no);

	
	//가격순 정렬
	List<GoodsVO> selectGoodsPriceAsc(int i);
	List<GoodsVO> selectGoodsPriceDesc(int i);
	List<GoodsVO> searchGoodsByKeyword(String keyword, String sort);
	List<GoodsVO> selectGoodsCategorySorted(Integer category_no, String sort);
	
	//위시순 정렬
	List<GoodsVO> selectGoodsWishDesc();
	List<GoodsVO> selectGoodsDropDesc();
	

}
