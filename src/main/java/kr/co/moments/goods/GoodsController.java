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

//       @GetMapping("/mainpage")
//       public String mainPage(Model model) {
//           List<GoodsVO> goodsList = goodsService.getAllGoods();
//           model.addAttribute("goodsList", goodsList); 
//           return "forward:/WEB-INF/views/mainpage/main.jsp";
//       }
      
      
//      //목록 잘 가지고 와지는지 확인해보기 
//      @GetMapping("/mainpage")
//      public String mainPage(Model model) {
//          List<GoodsVO> goodsList = goodsService.getAllGoods();
//          System.out.println("상품개수:" + goodsList.size());  // 상품 개수 확인
//          for (GoodsVO goods : goodsList) {
//              System.out.println(goods.getGoods_name());
//          }
//          model.addAttribute("goodsList", goodsList);
//          return "forward:/WEB-INF/views/mainpage/main.jsp";
//      }

}
