package kr.co.moments.wish;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.moments.domain.UsersVO;


@Controller
public class WishController {
	@Autowired
	WishService service;
	
	@GetMapping("/mypage/myWishGoods")
    public String getWishGoods(HttpSession session, Model model) {
//        UsersVO user = (UsersVO) session.getAttribute("user");
//
//        if (user == null) {
//            return "redirect:/login";
//        }
//
//        int userId = user.getUsers_no();
//
//        model.addAttribute("user", user);
//        model.addAttribute("achievedList",service.getAchievedAlerts(userId));
//        model.addAttribute("ongoingList", service.getOngoingAlerts(userId));
//        model.addAttribute("expiredList", service.getExpiredAlerts(userId));
		
		int userId = 46; // 원하는 테스트용 사용자 번호로 설정
        model.addAttribute("achievedList", service.getAchievedAlerts(userId));
        model.addAttribute("ongoingList", service.getOngoingAlerts(userId));
        model.addAttribute("expiredList", service.getExpiredAlerts(userId));
        
        model.addAttribute("alertList", service.getSentAlerts(userId));
        model.addAttribute("unreadCount", service.countUnreadAlerts(userId));
        service.markAlertsAsRead(userId); // 팝업 열 때 읽음 처리

        return "mypage/myWishGoods";
    }
	//test
	@GetMapping("/test/trigger-alert")
	public String triggerAlertCheck() {
	    service.checkAlerts(); // 강제 실행
	    return "redirect:/mypage/myWishGoods"; // 마이페이지로 리다이렉트
	}
	
    @PostMapping("/wish/delete")
    public String deleteWish(@RequestParam("alertNo") int alertNo) {
    	service.deleteAlert(alertNo);
        return "redirect:/mypage/myWishGoods";
    }

    @GetMapping("/product/{goodsNo}")
    public String viewProduct(@PathVariable("goodsNo") int goodsNo, Model model) {
        model.addAttribute("product", service.getProductByGoodsNo(goodsNo));
        return "product/detail";
    }
}
