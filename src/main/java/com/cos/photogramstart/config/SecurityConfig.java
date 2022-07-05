package com.cos.photogramstart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity  // 해당 파일로 시큐리티를 활성화
@Configuration //IoC
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super 삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화 됨
        //super.configure(http);
        http.authorizeRequests()
                .antMatchers("/","/user/**","/image/**", "subscribe/**", "/comment/**").authenticated() // 인증이 필요한 주소들
                .anyRequest().permitAll()  // 그 외의 요청은 접근 허용
                .and()
                .formLogin()    // 인증이 필요한 페이지로 접근이 오면 formLogin을 할건데
                .loginPage("/auth/signin")  // forLogin이 수행되는 페이지가 /auth/signin 이다
                .defaultSuccessUrl("/");    // 로그인이 정상처리되면 / 로 이동한다

        // csrf 토큰 비활성화
        http.csrf().disable();
    }
}