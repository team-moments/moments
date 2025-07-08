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
      @RequestMapping("/mainpage")
      public String showGoodsList(Model model) {
          List<GoodsVO> goodsList = goodsService.getAllGoods();
          model.addAttribute("goodsList", goodsList);
          return  "forward:/WEB-INF/views/mainpage/main.jsp";  
      }
      
      //카테고리별 상품 목록 띄우기 
      @GetMapping("/goods/category")
      public String getGoodsByCategory(@RequestParam("category_no") int category_no, Model model) {
          List<GoodsVO> goodsList = goodsService.getGoodsByCategory(category_no);
          model.addAttribute("goodsList", goodsList);
          return "forward:/WEB-INF/views/mainpage/category.jsp"; 
      }

}
