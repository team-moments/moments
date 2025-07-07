package kr.co.moments.security;

import kr.co.moments.users.UsersService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UsersService userService; 
    

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            // JSON 요청 body로 아이디/비번 파싱 예시 (필요 시 Form 방식은 obtainUsername() 사용)
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String body = sb.toString();

            String userEmail = parseValue(body, "email");
            String password = parseValue(body, "password");

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userEmail, password);

            return authenticationManager.authenticate(authToken);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 로그인 성공 시 JWT 발급 및 응답 쿠키/헤더 세팅
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication)
            throws IOException, ServletException {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
 
        String email = userDetails.getUsername();
        String accessToken = jwtTokenProvider.createAccessToken(userDetails.getUsername());
        String refreshToken = jwtTokenProvider.createRefreshToken(userDetails.getUsername());
        
       userService.updateRefreshToken(email, refreshToken); //로그인 유지 시 1주~1달 이상 유지시사용..
        
        // Header에 JWT 세팅
        response.addHeader("Authorization", "Bearer " + accessToken);

        // 쿠키에 JWT 세팅 (HttpOnly)
        Cookie accessCookie = new Cookie("accessToken", accessToken);
        accessCookie.setHttpOnly(true);
        accessCookie.setSecure(false); // 실무 배포 시 true
        accessCookie.setPath("/");
        accessCookie.setMaxAge(10); 

        Cookie refreshCookie = new Cookie("refreshToken", refreshToken);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(false); // 실무 배포 시 true
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(60 * 60 * 24 * 7); // 7일

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);

        // 성공 응답 메시지
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"message\": \"Login Success\"}");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed)
            throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"message\": \"Invalid username or password\"}");
    }

    private String parseValue(String body, String key) {
        int keyIdx = body.indexOf(key);
        if (keyIdx == -1) return null;
        int start = body.indexOf("\"", keyIdx + key.length() + 2);
        int end = body.indexOf("\"", start + 1);
        return body.substring(start + 1, end);
    }
}
