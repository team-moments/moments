package kr.co.moments.wish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import kr.co.moments.util.EmailUtil;

@Controller
public class TestMailController {

    @Autowired
    private EmailUtil emailUtil;

    @GetMapping("/test/send-mail")
    public String sendTestEmail() {
        emailUtil.sendEmail(
            "jsy2409@naver.com",        // 테스트로 받을 이메일
            "테스트 메일 제목",      // 제목
            "메일 전송이 성공했습니다."     // 본문
        );
        return "redirect:/"; // 성공 시 리다이렉트할 페이지
    }
}
