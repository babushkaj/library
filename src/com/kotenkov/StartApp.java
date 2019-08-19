package com.kotenkov;

//  Задание 1: создать консольное приложение “Учет книг в домашней библиотеке”.
//        Общие требования к заданию:
//        • Система учитывает книги как в электронном, так и в бумажном варианте.
//        • Существующие роли: пользователь, администратор.
//        • Пользователь может просматривать книги в каталоге книг, осуществлять поиск
//        книг в каталоге.
//        • Администратор может модифицировать каталог.
//        • *При добавлении описания книги в каталог оповещение о ней рассылается на
//        e-mail всем пользователям
//        • **При просмотре каталога желательно реализовать постраничный просмотр
//        • ***Пользователь может предложить добавить книгу в библиотеку, переслав её
//        администратору на e-mail.
//        • Каталог книг хранится в текстовом файле.
//        • Данные аутентификации пользователей хранятся в текстовом файле. Пароль
//        не хранится в открытом виде

//      There are two users to try the application.
//      Login: adminadmin, pass: adminadmin
//      Login: useruser, pass: useruser

import com.kotenkov.control.BookManager;
import com.kotenkov.entity.User;
import com.kotenkov.entity.Book;
import com.kotenkov.control.UserManager;
import com.kotenkov.input_output.file.FileProcessing;
import com.kotenkov.input_output.console.InputHandler;
import com.kotenkov.mail.MailSender;
import com.kotenkov.menu.AdminMenu;
import com.kotenkov.menu.UserMenu;
import com.kotenkov.security.Authorization;

import java.io.IOException;
import java.util.List;

public class StartApp {

    public static void main(String[] args) {
        List<User> users = null;
        List<Book> books = null;
        FileProcessing fp = new FileProcessing();
        InputHandler ih = new InputHandler();
        MailSender mailSender = new MailSender();


        try {
            books = fp.readBooks("books.txt");
            users = fp.readUsers("users.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        UserManager userManager = new UserManager(users, fp);
        int result = Authorization.authorize(ih,userManager);
        BookManager bookManager = new BookManager(books, fp);
        switch (result){
            case 1:{
                AdminMenu am = new AdminMenu(ih, books, bookManager, userManager, mailSender);
                am.doUserMenu();
                System.out.println("Good bye!");
                break;
            }
            case 2:{
                UserMenu um = new UserMenu(ih, books, bookManager);
                um.doUserMenu();
                System.out.println("Good bye!");
                break;
            }
            case 3:{
                System.out.println("Good bye!");
                break;
            }
        }
    }

}
