package com.kotenkov.security;

import com.kotenkov.entity.UserRole;
import com.kotenkov.entity.User;
import com.kotenkov.control.UserManager;
import com.kotenkov.input_output.console.InputHandler;

public class Authorization {

    public static int authorize(InputHandler ih, UserManager um) {
        System.out.println("Enter login:");
        String login = ih.enterWord();
        User user = um.checkUserName(login);
        if(user != null){
            System.out.println("Enter password:");
            String pass = ih.enterWord();
            if(user.getPass().equals(pass)){
                if(user.getRole() == UserRole.ADMIN){
                    return 1;
                }else{
                    return 2;
                }
            } else  {
                System.out.println("You entered false password! The program will be finished.");
                return 3;
            }
        } else {
            System.out.println("User with the login doesn't exist. The program will be finished.");
            return 3;
        }
    }

}
