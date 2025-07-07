package kr.co.moments.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider provider) {
        this.tokenProvider = provider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = resolveToken(request);

        //login url block
        String path = request.getRequestURI();
        
        if (path.equals("/moments/login") && token != null && tokenProvider.validateToken(token)) {
        	response.sendRedirect("/moments/mainpage");
            return; // 필터 체인 중단
        }
        
//        if (token != null && tokenProvider.validateToken(token)) {
//            String userEmail = tokenProvider.getUsername(token);
//            UsernamePasswordAuthenticationToken auth =
//                    new UsernamePasswordAuthenticationToken(userEmail, null, List.of());
//            SecurityContextHolder.getContext().setAuthentication(auth);
//        }
//
//        try {
//            if (token != null && tokenProvider.validateToken(token)) {
//                String email = tokenProvider.getUserEmail(token);
//                UsernamePasswordAuthenticationToken auth =
//                    new UsernamePasswordAuthenticationToken(email, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            }
//        } catch (JwtException e) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Expired");
//            return; // 필수!
//        }
//        try {
//        	  if (token != null && tokenProvider.validateToken(token)) {
//        	    String email = tokenProvider.getUserEmail(token);
//        	    UsernamePasswordAuthenticationToken auth =
//        	      new UsernamePasswordAuthenticationToken(
//        	        email,
//        	        null,
//        	        List.of(new SimpleGrantedAuthority("ROLE_USER")) // ✅ 최소 권한!
//        	      );
//        	    SecurityContextHolder.getContext().setAuthentication(auth);
//        	  }
//        	} catch (JwtException e) {
//        	  response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Expired");
//        	  return;
//        	}
        try {
            if (token != null && tokenProvider.validateToken(token)) {
                String email = tokenProvider.getUserEmail(token);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_USER")));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (ExpiredJwtException e) {
//            // ✅ 만료된 JWT의 경우: 401 + 에러메세지 -> jsp에서는 사용 불가 
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().write("{\"message\":\"Token Expired\"}");
//            return;
        	 response.sendRedirect("/moments/auth/refresh");
        	  return;
        } catch (Exception e) {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().write("{\"message\":\"Invalid Token\"}");
//            return;
        	response.sendRedirect("/moments/auth/refresh");
      	  	return;
        }

        filterChain.doFilter(request, response);
        
        //filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        // 1) 헤더 우선
        String bearer = request.getHeader("Authorization");
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }

        // 2) 쿠키 fallback
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("accessToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}