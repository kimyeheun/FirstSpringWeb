package org.example.firstSpringWeb.config.auth.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.firstSpringWeb.domain.user.Role;
import org.example.firstSpringWeb.domain.user.User;


import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributKey, String name, String email, String picture){
        this.attributes = attributes;
        this.nameAttributKey = nameAttributKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    //OAuth2User에서 반환하는 사용자 정보는 모두 Map이라 값을 하나씩 변환해야 한다. -> of()
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }


    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributKey(userNameAttributeName)
                .build();
    }

    //User 엔티티를 생성.
    //가입시 기본 권한은 GUEST -> [.role(Role.GUEST)]
    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
