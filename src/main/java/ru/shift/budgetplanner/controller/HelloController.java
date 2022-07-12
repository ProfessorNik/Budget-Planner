package ru.shift.budgetplanner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shift.budgetplanner.domain.AuthenticationJwt;

@RestController
@RequestMapping("api/hello")
public class HelloController {
    @GetMapping
    public ResponseEntity<String> sayHello(@AuthenticationPrincipal AuthenticationJwt authInfo){
        if(authInfo != null)
            return ResponseEntity.ok("Hello, " + authInfo.getName());
        else
            return ResponseEntity.ok("Hello, let's register!");
    }


}
