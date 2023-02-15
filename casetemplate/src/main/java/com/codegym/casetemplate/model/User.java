package com.codegym.casetemplate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    private long id;
    private String name;
    private String username;
    private String password;
    private int roleId;
    public User(long id) {
        User a = new User();
//        a.setId(id).setName("Quang");
    }
}
