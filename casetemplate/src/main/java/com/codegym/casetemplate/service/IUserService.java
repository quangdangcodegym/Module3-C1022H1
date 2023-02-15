package com.codegym.casetemplate.service;

import com.codegym.casetemplate.model.User;

public interface IUserService {
    User findUserById(long id);

    User findUserByUserNamePassword(String username, String password);
}
