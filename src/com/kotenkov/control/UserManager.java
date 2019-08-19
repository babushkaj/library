package com.kotenkov.control;

import com.kotenkov.entity.User;
import com.kotenkov.entity.UserRole;
import com.kotenkov.input_output.file.FileProcessing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    List<User> users;
    FileProcessing fp;

    public UserManager(List<User> users, FileProcessing fp) {
        this.users = users;
        this.fp = fp;
    }

    public void addUser(User user){
        if(!this.users.contains(user)){
            this.users.add(user);
            try {
                fp.writeUsers(this.users, "users.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("The user has been added.");
        } else {
            System.out.println("There is the same user already. The user hasn't been added.");
        }
    }

    public void deleteUser(int userNumber){
        User user = users.get(userNumber-1);
        if(!(user.getRole() == UserRole.ADMIN)){
            users.remove(user);
        }
        try {
            fp.writeUsers(this.users, "users.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("The user has been deleted.");
    }

    public void showAllUsers(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.users.size(); i++) {
            System.out.print(i+1 + ". ");
            System.out.println(oneUserToText(users.get(i), sb));
        }
    }

    public void showAllUsers(List<User> users){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < users.size(); i++) {
            System.out.print(i+1 + ". ");
            System.out.println(oneUserToText(users.get(i), sb));
        }
    }

    private String oneUserToText(User user, StringBuilder sb){
        sb.delete(0, sb.length());
        sb.append("Login: ");
        sb.append(user.getLogin());
        sb.append("\nPassword: ");
        sb.append(user.getPass());
        sb.append("\nemail: ");
        sb.append(user.getEmail());
        sb.append("\nRole: ");
        sb.append(user.getRole());
        sb.append("\n");

        return sb.toString();
    }

    public User checkUserName(String login){
        User user = null;
        for (User u: this.users) {
            if(u.getLogin().equals(login)){
                user = u;
                break;
            }
        }
        return user;
    }

    public List<String> getAllEmails(){
        List<String> emails = new ArrayList<>();

        for (User u:this.users) {
            if(u.getRole() != UserRole.ADMIN){
                emails.add(u.getEmail());
            }
        }

        return emails;
    }

    public List<User> getUsers() {
        return users;
    }

}
