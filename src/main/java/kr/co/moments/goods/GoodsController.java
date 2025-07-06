package kr.co.moments.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.moments.domain.GoodsVO;
import kr.co.moments.domain.DailyPriceVO;


@Controller
public class GoodsController {

      @Autowired
       private GoodsService goodsService;
 
      @GetMapping("/goodspage/goodsDetail")
      public String getGoodsDetail(@RequestParam("goods_no") int goodsNo, Model model) {
          System.out.println("컨트롤러 진입=============================");
          System.out.println(">> 상세 페이지 진입: " + goodsNo);

          GoodsVO goods = goodsService.getGoodsByNo(goodsNo);

          if (goods == null) {
              System.out.println(">> 상품이 존재하지 않습니다. goods_no = " + goodsNo);
              return "redirect:/errorPage"; // 또는 404 페이지 등
          }

          System.out.println(">> 현재가: " + goods.getPrice());
          System.out.println(">> 최고가: " + goods.getMax_price());
          System.out.println(">> 최저가: " + goods.getMin_price());
          System.out.println(">> 할인율: " + goods.getDiscount_rate());

          model.addAttribute("goods", goods);
          
          //차트
          List<DailyPriceVO> priceHistory = goodsService.getPriceHistoryLastMonth(goods.getGoods_id());
          model.addAttribute("priceHistory", priceHistory);
          
          
          return "forward:/WEB-INF/views/goodspage/goodsDetail.jsp";
      }


      @GetMapping("/testGoodsDetail")
      public String testGoodsDetail() {
          return "goodspage/goodsDetail";  // goodsDetail.jsp만 바로 보여줌
      }

}
