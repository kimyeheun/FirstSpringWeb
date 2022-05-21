package org.example.firstSpringWeb.config.auth;

import lombok.RequiredArgsConstructor;
import org.example.firstSpringWeb.config.auth.dto.OAuthAttributes;
import org.example.firstSpringWeb.config.auth.dto.SessionUser;
import org.example.firstSpringWeb.domain.user.User;
import org.example.firstSpringWeb.domain.user.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User>
                delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest
                .getClientRegistration()
                .getRegistrationId(); //현재 로그인 진행중인 서비스(네이버, 구글)를 구분하는 코드
        String userNameAttributeName = userRequest
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName(); //OAuth 로그인 진행시 키가 되는 필드 값(pk 같은거)
                                             //구글은 "sub"로 기본 지원해주지만, 네이버나 카카오는 안해줌. 구글과 다른 서비스를 동시 지원할 때 사용

        OAuthAttributes attributes = OAuthAttributes.of(registrationId,
                userNameAttributeName,
                oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user)); //세션에 사용자 정보를 저장하기 위한 Dto 클래스 (*User 클래스를 쓰지 않음)
        //*-> 오류 발생! 왜??: User 클래스는 엔티티인데 세션에 저장하려고 하니까, 직렬화를 구현하지 않았다는 이유로 오류 발생.
        //                 : 따라서 직렬화 기능을 가진 세션 Dto를 추가로 만든다.

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity-> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
