package kr.co.moments.wish;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import kr.co.moments.domain.AlertVO;
import kr.co.moments.domain.DailyPriceVO;
import kr.co.moments.domain.GoodsVO;
import kr.co.moments.domain.UsersVO;

@Mapper
public interface WishMapper {
	List<AlertVO> selectOngoingAlerts(int userId);
    List<AlertVO> selectAchievedAlerts(int userId);
    List<AlertVO> selectExpiredAlerts(int userId);
    void deleteAlertById(int alertNo);
    GoodsVO selectProductByGoodsNo(int goodsNo);
    
    
    //alert
    List<AlertVO> selectAllAlerts();
    void updateAlertStatus(AlertVO alert);
    UsersVO getUserById(int usersNo);
    DailyPriceVO getLatestPriceByGoodsId(String goodsId);

    List<AlertVO> getAchievedAlerts(int userId);
    List<AlertVO> getOngoingAlerts(int userId);
    List<AlertVO> getExpiredAlerts(int userId);
    List<AlertVO> selectSentAlerts(int userId);
    int countUnreadAlerts(int userId); // 읽지 않은 알림 수
    void markAlertsAsRead(int userId); // 읽음 처리

}
