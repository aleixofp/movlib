package com.fpa.softw.movlib.main;

import com.fpa.softw.movlib.models.User;

public class LoggedInUser {

    private static User user;

    public static void setProfile(User user){
        LoggedInUser.user = user;
    }

    public static User getLoggedInUser(){
        return LoggedInUser.user;
    }

}
