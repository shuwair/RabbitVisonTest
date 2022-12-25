package com.rabbitvission.demo.response;

import com.rabbitvission.demo.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserResponse {

    public UserResponse(List<User> userList) {
        this.userList = userList;

    }

    public UserResponse(User user) {
        userList = new ArrayList<>();
        userList.add(user);
    }

    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
