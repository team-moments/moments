package kr.co.moments.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.moments.api.OpenApiApplication;


@Controller
public class MainController {
   
   @Autowired
   private OpenApiApplication openApi;
    
//    @GetMapping("/mainpage")
//    public String mainPage(Model model) {
//       return "forward:/WEB-INF/views/mainpage/main.jsp";
//    }
    
//    @GetMapping("/goodspage")
//    public String goodsPage(Model model) {
//        return "forward:/WEB-INF/views/goods.jsp";
//    }
    

}
