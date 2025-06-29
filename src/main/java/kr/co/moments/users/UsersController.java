package kr.co.moments.users;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	
	
	
	
	
	
	
	// GET 요청으로 userEdit.jsp 띄우기
    @GetMapping("/userEdit")
    public String userEditPage(@RequestParam("field") String field, Model model) {
        model.addAttribute("field", field);
        UsersVO user = service.findByUserNo(1); // !!!!!!세션에서 가져오는걸로 수정해야함(안돼있으면 로그인 페이지로)!!!!!!
        model.addAttribute("user", user);
        model.addAttribute("field", field);
        return "mypage/userEdit";  
    }
    
    @PostMapping("/userEdit")
    public String updateUserInfo(
            @RequestParam String field,
            @RequestParam String email,
            @RequestParam String value,
            RedirectAttributes redirectAttributes,
            HttpSession session
    ) {
        UsersVO user = (UsersVO) session.getAttribute("loginUser");

        if (user == null) {
            return "redirect:/login";
        }

        int result = service.updateUserFieldByUserEmail(user.getUsers_email(), field, value);
        if (result > 0) {
            redirectAttributes.addFlashAttribute("message", "회원 정보가 수정되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("message", "회원 정보 수정에 실패했습니다.");
        }

        return "redirect:/mypage";
    }

	
}