package kr.co.moments.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AlertVO {
		private int alert_no;
		private int users_no;
		private int goods_no;
		private int is_achieve;
		private int is_sent;
		private int wish_price;
		private int period;
		private Timestamp register_date;
}	

