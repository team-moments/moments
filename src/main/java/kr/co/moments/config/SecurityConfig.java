package kr.co.moments.config;

import kr.co.moments.security.JwtTokenProvider;
import kr.co.moments.security.JwtAuthenticationFilter;
import kr.co.moments.security.LoginFilter;
import kr.co.moments.users.UsersService;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UsersService userService; 

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            	.antMatchers("/css/**", "/js/**", "/images/**").permitAll() //모두 허
                .antMatchers("/login", "/signUp", "/signUpAccept", "/agreeTerms","/auth/refresh",
                             "/signUpInfo", "/signUpCompleted", "/mainpage").permitAll()
                .anyRequest().authenticated()
            .and()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    // 토큰 없거나 만료되었을 때 401 리턴
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                })
            .and()
            // JWT 인증 필터
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
            // 로그인 요청 처리 필터
            .addFilterAt(new LoginFilter(authenticationManagerBean(), jwtTokenProvider, userService), UsernamePasswordAuthenticationFilter.class);
    }
}