package org.example.firstSpringWeb.config.auth;

import lombok.RequiredArgsConstructor;
import org.example.firstSpringWeb.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //아래 옵션 .disable()하는 이유 = h2-console 화면 서용하려고
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests() //URL 별 권한 관리를 설정하는 옵션의 시작점. 이게 있어야 antMatchers 사용할 수 있음
                    //antMatchers : 권한 관리 대상 지정. URL, Http 메소드별로 관리 가능.
                    //.permitAll() : 전체 열람 권한
                    ///api/v1/** 주소를 가진 API는 USER 권한을 가진 사람만 열 수 있다.
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated() //위에서 설정되고 나머지 url은 인증된 (로그인한) 사용자만 허용.
                .and()
                    .logout()
                    .logoutSuccessUrl("/") //로그아웃 성공하면 "/" 주소로 이동
                .and()
                    .oauth2Login() //OAuth2 로그인 기능 시작점
                    .userInfoEndpoint() //사용자 정보 가져올 때 설정
                    .userService(customOAuth2UserService);
    }
}
