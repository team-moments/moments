package kr.co.moments.main;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import kr.co.moments.domain.GoodsVO;

import kr.co.moments.api.OpenApiApplication;


@Controller
public class MainController {
   
    @Autowired
    private MainService mainService;
    
//    @GetMapping("/mainpage")
//    public String mainPage(Model model) {
//       return "forward:/WEB-INF/views/mainpage/main.jsp";
//    }
   
   
   //수정3차
   @GetMapping("/mainpage")
   public String mainPage(Model model) {
       List<GoodsVO> goodsListAsc = mainService.getSortedGoods("price_asc");
       List<GoodsVO> goodsListDesc = mainService.getSortedGoods("price_desc");
       List<GoodsVO> goodsListWish = mainService.getGoodsWishDesc();
       List<GoodsVO> goodsListDrop = mainService.getGoodsDropDesc(); // 

       model.addAttribute("goodsListAsc", goodsListAsc);
       model.addAttribute("goodsListDesc", goodsListDesc);
       model.addAttribute("goodsListWish", goodsListWish);
       model.addAttribute("goodsListDrop", goodsListDrop);
       

       return "forward:/WEB-INF/views/mainpage/main.jsp";
   }
   
   
   //카테고리별 상품 목록 띄우기 ( 버튼 선택 시 )
   @GetMapping("/goods/category")
   public String getGoodsByCategory(@RequestParam("category_no") int category_no, Model model) {
       List<GoodsVO> goodsList = mainService.getGoodsByCategory(category_no);
       model.addAttribute("goodsList", goodsList);
       return "forward:/WEB-INF/views/mainpage/category.jsp"; 
   }
   
   //수정2차
   @GetMapping("/goods/category/sort")
   public String getGoodsByCategory(
           @RequestParam("category_no") int category_no,
           @RequestParam(value = "sort", required = false, defaultValue = "price_asc") String sort,
           Model model) {

       List<GoodsVO> goodsList = mainService.selectGoodsCategorySorted(category_no, sort);
       model.addAttribute("goodsList", goodsList);
       return "forward:/WEB-INF/views/mainpage/category.jsp";
   }
}
