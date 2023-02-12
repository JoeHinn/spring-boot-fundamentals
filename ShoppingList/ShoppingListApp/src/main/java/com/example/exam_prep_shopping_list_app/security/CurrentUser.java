package com.example.exam_prep_shopping_list_app.security;

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

    public boolean isLoggedIn(){
        return this.id != null;
    }

    public void setLoggedIn(boolean isLoggedIn){
        this.id = null;
    }
}
