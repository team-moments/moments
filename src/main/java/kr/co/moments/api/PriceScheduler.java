package kr.co.moments.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.co.moments.wish.WishService;

@Component
public class PriceScheduler {
    
    @Autowired
    private OpenApiApplication openApiApplication;

    // 하루에 한번 실행 (매일 자정 12시)
    @Scheduled(cron = "0 0 0 * * *")
    public void schedulePriceUpdate() {
        try {
            openApiApplication.sendApiRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Autowired
    private WishService wishService;

    @Scheduled(cron = "0 0 * * * *") // 매 정시마다
    public void autoCheckWishAlerts() {
        wishService.checkAlerts();
    }
}