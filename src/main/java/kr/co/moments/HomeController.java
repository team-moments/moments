package kr.co.moments;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import kr.co.moments.api.OpenApiApplication;
import kr.co.moments.domain.DailyPriceVO;
import kr.co.moments.domain.GoodsVO;




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
    	//db 저장
//    	try {
//			openApi.sendApiRequest();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
        return "openApiTestResult"; 
    
    }
}