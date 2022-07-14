package ru.shift.budgetplanner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/hello")
public class HelloController {
    @GetMapping
    public ResponseEntity<String> sayHello() {
        Authentication authInfo = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok("Hello, " + authInfo.getName());
    }


}
