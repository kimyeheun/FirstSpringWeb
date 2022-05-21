package org.example.firstSpringWeb.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*IndexController에서 세션값을 가지고 오는 부분. 즉 다음 코드 부분의 불필요한 반복을 막기 위해 어노테이션 생성.
[SessionUser user = (SessionUser) httpSession.getAttribute("user");]
이 부분을 메소드 인자로 세션값을 바로 받아올 수 있도록 변경하는 과정이다.
*/
@Target(ElementType.PARAMETER) //어노테이션이 생성될 수 있는 위치 지정 [PARAMETER]:메소드의 파라미터로 선언된 객체에서만 사용할 수 있다.
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { //이 파일을 어노테이션 클래스로 지정함. 즉, LoginUser라는 어노테이션이 생성된 것.
}
