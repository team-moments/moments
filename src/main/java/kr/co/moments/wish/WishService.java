package kr.co.moments.wish;

import kr.co.moments.domain.GoodsVO;

import java.util.List;

import kr.co.moments.domain.AlertVO;

public interface WishService {
	List<AlertVO> getOngoingAlerts(int userId);
    List<AlertVO> getAchievedAlerts(int userId);
    List<AlertVO> getExpiredAlerts(int userId);
    void deleteAlert(int alertNo);
    GoodsVO getProductByGoodsNo(int goodsNo);
    void checkAlerts(); // 가격 도달 및 기간 만료 체크
}
