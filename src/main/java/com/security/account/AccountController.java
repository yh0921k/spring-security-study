package com.security.account;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @GetMapping("/account/{username}/{password}/{role}")
  public Account createAccount(@ModelAttribute Account account) {
    return accountService.createNew(account);
  }
}
