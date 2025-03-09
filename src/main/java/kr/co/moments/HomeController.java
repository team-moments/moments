package kr.co.moments;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.moments.api.OpenApiApplication;

@Controller
public class HomeController {
	@Autowired
	private OpenApiApplication openApi;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home"; // home.jsp 또는 home.html이 있어야 함
    }
    
    @GetMapping("/openApiTest")
    public String openApiTest(Model model) {
        try {
            // API 요청 보내고 결과를 모델에 추가
            String apiResponse = openApi.sendApiRequest();
            model.addAttribute("apiResponse", apiResponse);
        } catch (Exception e) {
            model.addAttribute("error", "API 요청에 실패했습니다.");
        }

        // 결과를 JSP로 반환
        return "openApiTestResult"; // openApiTestResult.jsp
    
    }
}