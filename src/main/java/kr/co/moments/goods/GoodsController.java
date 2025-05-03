package kr.co.moments.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import kr.co.moments.domain.GoodsVO;


@Controller
public class GoodsController {

      @Autowired
       private GoodsService goodsService;  

      
      
      //목록 잘 가지고 와지는지 확인해보기 
      @GetMapping("/mainpage")
      public String mainPage(Model model) {
          List<GoodsVO> goodsList = goodsService.getAllGoods();
          model.addAttribute("goodsList", goodsList);
          return "forward:/WEB-INF/views/mainpage/main.jsp";
      }

}
