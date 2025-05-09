package kr.co.moments.users;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.moments.domain.UsersVO;
import kr.co.moments.util.Sha512SaltUtil;


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

	@GetMapping("/login")
	public String userLogin () {
		return "users/login";
	}
	
	@PostMapping("/login/auth")
	public void loginAuth(@RequestParam Map<String,String> map, HttpSession session) {
	    UsersVO user = service.findByEmail(map.get("email"));
	    //로그인 실패 구
	    if (user == null) {
//	        return "redirect:/login?error";
	    	 System.out.println("유저 없음");
	    } 

	    // DB에서 꺼낸 salt
	    String salt = user.getUsers_salt();
	    // 입력 비밀번호 + salt → 해시
	    String hashedInput = Sha512SaltUtil.hashWithSalt(salt, map.get("password"));

	    if (hashedInput.equals(user.getUsers_pwd())) {
	        session.setAttribute("loginUser", user);
	        //return "redirect:/dashboard";
	        System.out.println("로그인 성공");
	    } else {
	   //     return "redirect:/login?error";
	    	System.out.println("해시실패");
	    }
	}
	
	
	
	
	@GetMapping("/signUpAccept")
	public String userSingUpAccept () {
		return "users/signUpAccept";
	}
	
	@PostMapping("/agreeTerms")
	public String agreeTerms(HttpSession session) {
		 session.setAttribute("accessSignUpInfo", true);
	    return "redirect:/signUpInfo";
	}
	
	
	@GetMapping("/signUpInfo")
	public String usersignUpInfo (HttpSession session) {
		Boolean validAccess = (Boolean) session.getAttribute("accessSignUpInfo");

	    if (validAccess == null || !validAccess) {
	        return "redirect:/signUpAccept";
	    }

	    session.removeAttribute("accessSignUpInfo");
	    return "users/signUpInfo"; 
	}
	
	@PostMapping("/signUp")
	public String userSignUp (@RequestParam Map<String, String> map) {
		
		int flag = service.userSignUp(map);
		//실패 여부처리하기...
		return "users/signUpCompleted";
	}
	
	
}
