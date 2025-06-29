package kr.co.moments.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.moments.domain.GoodsVO;

@Controller
public class GoodsController {

      @Autowired
       private GoodsService goodsService;
      
      // 상품 목록을 가져오는 메서드
//      @RequestMapping("/mainpage")
//      public String showGoodsList(Model model) {
//          List<GoodsVO> goodsList = goodsService.getAllGoods();
//          model.addAttribute("goodsList", goodsList);
//          return  "forward:/WEB-INF/views/mainpage/main.jsp";  
//      }
      
      //카테고리별 상품 목록 띄우기 
//      @GetMapping("/goods/category")
//      public String getGoodsByCategory(@RequestParam("category_no") int category_no, Model model) {
//          List<GoodsVO> goodsList = goodsService.getGoodsByCategory(category_no);
//          model.addAttribute("goodsList", goodsList);
//          return "forward:/WEB-INF/views/mainpage/category.jsp"; 
//      }
      
      
  	//06.28
      @GetMapping("/goodspage/goodsDetail")
      public String getGoodsDetail(@RequestParam("goods_id") String goodsId, Model model) {
    	  System.out.println("컨트롤러 진입=============================");
          GoodsVO goods = goodsService.getGoodsById(goodsId); // 상품 조회
          model.addAttribute("goods", goods); // 모델에 전달
          System.out.println(">> 상세 페이지 진입: " + goodsId); // 디버깅 로그
          System.out.println(">> 현재가: " + goods.getPrice());
          System.out.println(">> 최고가: " + goods.getMax_price());
          System.out.println(">> 최저가: " + goods.getMin_price());
          System.out.println(">> 할인율: " + goods.getDiscount_rate());
          return "forward:/WEB-INF/views/goodspage/goodsDetail.jsp"; // JSP로 이동
      }

      
      @GetMapping("/testGoodsDetail")
      public String testGoodsDetail() {
          return "goodspage/goodsDetail";  // goodsDetail.jsp만 바로 보여줌
      }

}
