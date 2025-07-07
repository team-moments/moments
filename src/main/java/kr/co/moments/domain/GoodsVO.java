package kr.co.moments.domain;

import lombok.Data;

@Data
public class GoodsVO {
    private int goods_no;
    private String goods_id;
    private String goods_name;
    private String goods_image;
    private int category_no;
    private int wish_cnt;
    
    
    //kys추가 - 오늘 날짜 기준 가격 main페이지 출력하려고...
    private int price;
    
    //급락순 정렬
    private double drop_rate;
    
    //goodsdetail.jsp에 카테고리 이름 출력하려고
    private String category_name;
    
    
    
    //급락순 도전 10회차
    private Integer current_price;
    private Integer prev_price;
    private Integer drop_amount;
    
    
    
   //  상품 상세 페이지 
    private Integer max_price;
    private Integer min_price;
    private Double discount_rate;

}
