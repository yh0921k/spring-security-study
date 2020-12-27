package com.security.form;

import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

  public void dashboard() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object principal = authentication.getPrincipal(); // 사실상 UserDeatils 타입, 사용자 정보
    Collection<? extends GrantedAuthority> authorities =
        authentication.getAuthorities(); // GrantAuthority, 해당 사용자의 권한
    Object credentials =
        authentication.getCredentials(); // 인증 이후에는 credentials 값을 가지고 있을 필요가 없으므로 빈 값일 것이다.
    boolean authenticated = authentication.isAuthenticated(); // 인증된 사용자인가?
  }
}
