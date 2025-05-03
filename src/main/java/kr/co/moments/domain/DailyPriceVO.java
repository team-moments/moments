package kr.co.moments.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class DailyPriceVO {
    private int logs_no;
    private String goods_id;
    private Timestamp register_date;
    private int price;
}
