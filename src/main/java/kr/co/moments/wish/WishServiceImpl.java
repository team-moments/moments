package kr.co.moments.wish;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import kr.co.moments.domain.GoodsVO;
import kr.co.moments.domain.UsersVO;
import kr.co.moments.domain.AlertVO;
import kr.co.moments.domain.DailyPriceVO;
import kr.co.moments.util.EmailUtil;

@Service
public class WishServiceImpl implements WishService{
	
	@Autowired
	private WishMapper mapper;
	
	@Autowired
    private EmailUtil emailUtil;

    @Override
    public void checkAlerts() {
        List<AlertVO> alertList = mapper.selectAllAlerts();

        for (AlertVO alert : alertList) {
            if (alert.getIs_achieve() == 1) continue;

            DailyPriceVO price = mapper.getLatestPriceByGoodsId(String.valueOf(alert.getGoods_no()));
            if (price == null) continue;

            int currentPrice = price.getPrice();

            //가격 도달 알림상태 달성표시, 메일 발송 처리표시 
            if (currentPrice <= alert.getWish_price()) {
                alert.setIs_achieve(1);
                alert.setIs_sent(1);
                mapper.updateAlertStatus(alert);

                UsersVO user = mapper.getUserById(alert.getUsers_no());
                emailUtil.sendEmail(
                        user.getUsers_email(),
                        "MOMENTS 위시 가격 도달 알림 메일입니다!",
                        alert.getName() + "이 희망 가격(" + alert.getWish_price() + "원)에 도달했습니다!" + 
                        "\n\n마이페이지에서 확인해보세요: http://localhost:8080/mypage/myWishGoods"
                    );
                    continue;
            }

            //기간 만료 표시, 메일 발송 처리 표시 
            long now = System.currentTimeMillis();
            long registered = alert.getRegister_date().getTime();
            long validMs = alert.getPeriod() * 24L * 60 * 60 * 1000;

            if (now - registered > validMs && alert.getIs_sent() != 2) {
                alert.setIs_sent(2);
                mapper.updateAlertStatus(alert);

                UsersVO user = mapper.getUserById(alert.getUsers_no());
                emailUtil.sendEmail(
                        user.getUsers_email(),
                        "MOMENTS 위시 기간 만료 알림 메일입니다!",
                        "설정한 기간 내에 '" + alert.getName() + "'의 가격이 도달하지 못했습니다.\n" +
                        "위시 가격을 재설정하거나 삭제해 주세요!" +
                        "\n\n마이페이지 바로가기: http://localhost:8080/mypage/myWishGoods"
                    );
            }
        }
    }

	@Override
    public List<AlertVO> getOngoingAlerts(int userId) {
        return mapper.selectOngoingAlerts(userId);
    }

    @Override
    public List<AlertVO> getAchievedAlerts(int userId) {
        return mapper.selectAchievedAlerts(userId);
    }

    @Override
    public List<AlertVO> getExpiredAlerts(int userId) {
        return mapper.selectExpiredAlerts(userId);
    }

    @Override
    public void deleteAlert(int alertNo) {
    	mapper.deleteAlertById(alertNo);
    }

    @Override
    public GoodsVO getProductByGoodsNo(int goodsNo) {
        return mapper.selectProductByGoodsNo(goodsNo);
    }
}
