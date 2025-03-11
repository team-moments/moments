package kr.co.moments.domain;

import lombok.Data;

@Data
public class GoodsVO {
    private int goods_no;
    private int goods_id;
    private String goods_name;
    private String goods_image;
    private int category_no;
    private int wish_cnt;
}
