package com.example.exam_prep_coffee_shop.security;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@Setter
@NoArgsConstructor
@Component
@SessionScope
public class CurrentUser {

    private Long id;
    private String username;
    private Boolean isLoggedIn;

    public boolean isLoggedIn() {
        return this.id != null;
    }

    public CurrentUser setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
        return this;
    }
}
