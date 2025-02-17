package kr.co.moments.domain;

import lombok.Data;

@Data
public class GoodsVO {
    private int goods_no;
    private String goods_name;
    private String goods_image;
    private int category_middle_no;
    private int wish_cnt;
}
