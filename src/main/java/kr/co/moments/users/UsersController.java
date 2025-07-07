package kr.co.moments.users;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.moments.domain.UsersVO;
import kr.co.moments.security.JwtTokenProvider;


@Controller
public class UsersController {
	@Autowired
	UsersService service;
	
	@Autowired
    private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private PasswordEncoder passwordEncoder;
    
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
	public String userLogin (@AuthenticationPrincipal String user_email) {
		return "users/login";
	}
	
//	@PostMapping("/auth/refresh")
//	public ResponseEntity<?> refresh(@CookieValue("refreshToken") String refreshToken,
//	                                 HttpServletResponse response) {
//		System.out.print("재발급");
//	    // 1. 토큰 파싱해서 사용자 이메일 꺼냄
//	    String email = jwtTokenProvider.getUserEmail(refreshToken);
//
//	    // 2. DB에 저장된 Refresh Token과 비교
//	    UsersVO user = service.findByEmail(email);
//	    if (user == null || !refreshToken.equals(user.getRefresh_token())) {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh");
//	    }
//
//	    // 3. 새 Access Token 발급
//	    String newAccessToken = jwtTokenProvider.createAccessToken(email);
//
//	    Cookie cookie = new Cookie("accessToken", newAccessToken);
//	    cookie.setHttpOnly(true);
//	    cookie.setPath("/");
//	    cookie.setMaxAge(10);
//	    response.addCookie(cookie);
//
//	    return ResponseEntity.ok("Access Token refreshed");
//	}
	@PostMapping("/auth/refresh")
	public void refresh(
	    @CookieValue("refreshToken") String refreshToken,
	    HttpServletResponse response) throws IOException {
		System.out.println("재발급");
	  String email = jwtTokenProvider.getUserEmail(refreshToken);
	  UsersVO user = service.findByEmail(email);
	  if (user == null || !refreshToken.equals(user.getRefresh_token())) {
		  response.sendRedirect("/moments/login");
	    return;
	  }

	  String newAccessToken = jwtTokenProvider.createAccessToken(email);
	  Cookie cookie = new Cookie("accessToken", newAccessToken);
	  cookie.setPath("/");
	  cookie.setHttpOnly(true);
	  cookie.setMaxAge(60 * 10);
	  response.addCookie(cookie);

	  response.sendRedirect("/moments/mainpage"); // 원래 주소
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
	public String usersignUpInfo (HttpServletResponse response, HttpSession session) {
		// 캐시 무효화
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
	    response.setHeader("Pragma", "no-cache");
	    response.setDateHeader("Expires", 0);

	    Boolean validAccess = (Boolean) session.getAttribute("accessSignUpInfo");
	    if (validAccess == null || !validAccess) {
	        return "redirect:/signUpAccept";
	    }
	    session.removeAttribute("accessSignUpInfo");
	    return "users/signUpInfo";
	}
	
	@PostMapping("/signUp")
    @ResponseBody
	public String userSignUp (@RequestBody Map<String, String> map, HttpSession session) {
		 String plainPwd = map.get("password");
		 String encodedPwd = passwordEncoder.encode(plainPwd);
		 map.put("users_pwd", encodedPwd);
		 
		int result = service.userSignUp(map);

		   if (result > 0) {
			   session.setAttribute("signUpCompleted", true);
		        return "redirect:/signUpcompleted"; 
		    } else {
		        return "fail"; //흠...
		    }
	}

	@GetMapping("/signUpCompleted")
	public String signUpCompleted(HttpServletResponse response, HttpSession session) {
		// 캐시 무효화 헤더 추가
	    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	    response.setHeader("Pragma", "no-cache");
	    response.setDateHeader("Expires", 0);
		
		Boolean completed = (Boolean) session.getAttribute("signUpCompleted");
	   System.out.print(completed);
	    if (completed == null || !completed) {
	        return "redirect:/mainpage";  // 세션 없으면 접근 차단
	    }
	    session.removeAttribute("signUpCompleted");  // 한 번 보여주고 세션 삭제
	    return "users/signUpCompleted";               // JSP 뷰 이름 반환
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logout(@CookieValue("refreshToken") String refreshToken,
	                                     HttpServletResponse response) {
	    String email = jwtTokenProvider.getUserEmail(refreshToken);
	    service.deleteRefreshToken(email);

	    Cookie clearAccess = new Cookie("accessToken", null);
	    clearAccess.setMaxAge(0);
	    clearAccess.setPath("/");
	    clearAccess.setHttpOnly(true);

	    Cookie clearRefresh = new Cookie("refreshToken", null);
	    clearRefresh.setMaxAge(0);
	    clearRefresh.setPath("/");
	    clearRefresh.setHttpOnly(true);

	    response.addCookie(clearAccess);
	    response.addCookie(clearRefresh);

	    return ResponseEntity.ok("Logged out");
	}
}
