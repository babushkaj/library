package com.kotenkov.menu;

import com.kotenkov.control.BookManager;
import com.kotenkov.control.UserManager;
import com.kotenkov.entity.BookType;
import com.kotenkov.entity.UserRole;
import com.kotenkov.entity.*;
import com.kotenkov.input_output.console.InputHandler;
import com.kotenkov.mail.MailSender;

import java.util.List;

public class AdminMenu extends UserMenu {

    public static final String USER_MENU_TEXT = "=========================\n" +
                                                "1. Show all books.\n" +
                                                "2. Search.\n" +
                                                "3. Manage books.\n" +
                                                "4. Manage users.\n" +
                                                "5. Exit.\n" +
                                                "=========================";

    public static final String MANAGE_BOOKS_MENU = "=========================\n" +
                                                    "1. Add book.\n" +
                                                    "2. Delete book.\n" +
                                                    "3. Back.\n" +
                                                    "=========================";

    public static final String MANAGE_USERS_MENU = "=========================\n" +
                                                    "1. Show all users.\n" +
                                                    "2. Add user.\n" +
                                                    "3. Delete user.\n" +
                                                    "4. Back.\n" +
                                                    "=========================";

    private UserManager userManager;
    private MailSender mailSender;

    public AdminMenu(InputHandler ih, List<Book> books, BookManager bookManager,
                     UserManager userManager, MailSender mailSender) {
        super(ih, books, bookManager);
        this.userManager = userManager;
        this.mailSender = mailSender;
    }

    @Override
    public void doUserMenu() {
        while(true){
            System.out.println(USER_MENU_TEXT);
            int menuNum = ih.enterInt(1,5);
            switch (menuNum){
                case 1:{
                    bookManager.showAllBooks();
                    break;
                }
                case 2:{
                    doSearchMenu();
                    break;
                }
                case 3:{
                    doManageBooksMenu();
                    break;
                }
                case 4:{
                    doManageUsersMenu();
                    break;
                }
            }
            if(menuNum == 5){
                break;
            }
        }
    }

    private void doManageBooksMenu() {
        while(true){
            System.out.println(MANAGE_BOOKS_MENU);
            int menuNum = ih.enterInt(1,3);
            switch (menuNum){
                case 1:{
                    doAddBookMenu();
                    break;
                }
                case 2:{
                    doDeleteBookMenu();
                    break;
                }
            }
            if(menuNum == 3){
                break;
            }
        }
    }

    private void doAddBookMenu() {
        Book book = new Book();
        System.out.println("Enter author's first name (or \'back\'):");
        String firstName = ih.enterWord();
        if(firstName.equalsIgnoreCase("back")){
            return;
        }

        System.out.println("Enter author's last name(or \'back\'):");
        String lastName = ih.enterWord();
        if(lastName.equalsIgnoreCase("back")){
            return;
        }
        Author author = new Author(firstName, lastName);
        book.setAuthor(author);

        System.out.println("Enter the title (or \'back\'):");
        String title = ih.enterPhrase();
        if(title.equalsIgnoreCase("back")){
            return;
        }
        book.setTitle(title);

        System.out.println("Enter the year (or \'0\' to back):");
        int year = ih.enterInt(0,2019);
        if(year == 0){
            return;
        }
        book.setYear(year);

        System.out.println("Enter a type of the book (\'paper\', \'ebook\') or \'back\':");
        while(true){
            String type = ih.enterWord();
            if(type.equalsIgnoreCase("back")){
                return;
            } else if(type.equalsIgnoreCase("paper")){
                book.setType(BookType.PAPER);
                break;
            } else if(type.equalsIgnoreCase("ebook")){
                book.setType(BookType.EBOOK);
                break;
            }
        }
        System.out.println("Enter a short description of the book (or \'back\'):");
        String shortDesc = ih.enterPhrase();
        if(shortDesc.equalsIgnoreCase("back")){
            return;
        }
        book.setShortDesc(ih.enterPhrase());

        System.out.println("Try to add the book...");
        bookManager.addBook(book);
        //if you need to send email - uncomment rows below

//        String newBook = "Hi! A new book appeared in the bookManager! Author: " + firstName +
//                         " " + lastName + ", title: " + title + ". ";
//        try {
//            mailSender.sendMessage(userManager.getAllEmails(), "New book!!!", newBook);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
    }


    private void doDeleteBookMenu() {
        System.out.println("Enter a number of the book (or \'0\' to back):");
        int number = ih.enterInt(0, bookManager.getBooks().size());
        if(number == 0){
            return;
        }
        bookManager.deleteBook(number);
    }

    private void doManageUsersMenu() {
        while(true){
            System.out.println(MANAGE_USERS_MENU);
            int menuNum = ih.enterInt(1,4);
            switch (menuNum){
                case 1:{
                    userManager.showAllUsers();
                    break;
                }
                case 2:{
                    doAddUserMenu();
                    break;
                }
                case 3:{
                    doDeleteUserMenu();
                    break;
                }
            }
            if(menuNum == 4){
                break;
            }
        }
    }

    private void doAddUserMenu() {
        User user = new User();
        System.out.println("Enter login (or \'back\'):");
        String login = ih.enterWord();
        if(login.equalsIgnoreCase("back")){
            return;
        }
        user.setLogin(login);

        System.out.println("Enter password (or \'back\'):");
        String pass = ih.enterWord();
        if(pass.equalsIgnoreCase("back")){
            return;
        }
        user.setPass(pass);

        System.out.println("Enter email (or \'back\'):");
        String email = ih.enterEmail();
        if(email.equalsIgnoreCase("back")){
            return;
        }
        user.setEmail(email);

        System.out.println("Enter a type of the user (\'admin\', \'user\') or \'back\':");
        while(true){
            String type = ih.enterWord();
            if(type.equalsIgnoreCase("back")){
                return;
            } else if(type.equalsIgnoreCase("admin")){
                user.setRole(UserRole.ADMIN);
                break;
            } else if(type.equalsIgnoreCase("user")){
                user.setRole(UserRole.USER);
                break;
            }
        }
        System.out.println("Try to add the user...");
        userManager.addUser(user);

    }

    private void doDeleteUserMenu(){
        System.out.println("Enter a number of the user (or \'0\' to back):");
        int number = ih.enterInt(0,userManager.getUsers().size());
        if(number == 0){
            return;
        }
        userManager.deleteUser(number);
    }
}
