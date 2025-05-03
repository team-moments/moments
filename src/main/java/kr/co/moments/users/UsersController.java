package kr.co.moments.users;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.moments.domain.UsersVO;


@Controller
public class UsersController {
	@Autowired
	UsersService service;
	
    
    @GetMapping("/mypage")
    public String myPage() {
        return "mypage/info";
    }
    
 // 마이페이지 수정화면으로 이동
    @GetMapping("/userEdit")
    public String userEdit(@RequestParam("field") String field, Model model, HttpSession session) {
        UsersVO user = (UsersVO) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("field", field);
        return "mypage/userEdit";  // => /WEB-INF/views/mypage/userEdit.jsp
    }

//    // 수정 완료 시 처리
//    @PostMapping("/userUpdate")
//    public String userUpdate(@RequestParam("field") String field,
//                             @RequestParam("value") String value,
//                             HttpSession session,
//                             RedirectAttributes redirectAttributes) {
//        User user = (User) session.getAttribute("user");
//
//        switch (field) {
//            case "name":
//                user.setName(value);
//                break;
//            case "phone":
//                user.setPhone(value);
//                break;
//            case "email":
//                user.setEmail(value);
//                break;
//            case "password":
//                user.setPassword(value); // 실제로는 암호화 필요
//                break;
//        }
//
//        // DB 저장 로직 (UserService 이용)
//        // userService.update(user);
//
//        // 업데이트된 user 세션에 다시 저장
//        session.setAttribute("user", user);
//
//        redirectAttributes.addFlashAttribute("message", "정보가 수정되었습니다.");
//        return "redirect:/mypage/info";
//    }
}
