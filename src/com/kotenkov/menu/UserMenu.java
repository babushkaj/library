package com.kotenkov.menu;

import com.kotenkov.control.BookManager;
import com.kotenkov.entity.BookType;
import com.kotenkov.entity.Author;
import com.kotenkov.entity.Book;
import com.kotenkov.input_output.console.InputHandler;

import java.util.ArrayList;
import java.util.List;

public class UserMenu {

    public static final String USER_MENU_TEXT = "=========================\n" +
                                              "1. Show all books.\n" +
                                              "2. Search.\n" +
                                              "3. Exit.\n" +
                                              "=========================";

    public static final String SEARCH_MENU = "=========================\n" +
                                            "1. Find books by author.\n" +
                                            "2. Find books by title.\n" +
                                            "3. Find books by type.\n" +
                                            "4. Back.\n" +
                                            "=========================";

    protected InputHandler ih;
    protected List<Book> books;
    protected BookManager bookManager;

    public UserMenu(InputHandler ih, List<Book> books, BookManager bookManager) {
        this.ih = ih;
        this.books = books;
        this.bookManager = bookManager;
    }

    public void doUserMenu(){
        while(true){
            System.out.println(USER_MENU_TEXT);
            int menuNum = ih.enterInt(1,3);
            switch (menuNum){
                case 1:{
                    bookManager.showAllBooks();
                    break;
                }
                case 2:{
                    doSearchMenu();
                    break;
                }
            }
            if(menuNum == 3){
                break;
            }
        }
    }

    public void doSearchMenu(){
        while(true){
            System.out.println(SEARCH_MENU);
            int menuNum = ih.enterInt(1,4);
            switch (menuNum){
                case 1:{
                    doSearchByAuthorMenu();
                    break;
                }
                case 2:{
                    doSearchByTitleMenu();
                    break;
                }
                case 3:{
                    doSearchByTypeMenu();
                    break;
                }
            }
            if(menuNum == 4){
                break;
            }
        }
    }

    protected void doSearchByAuthorMenu(){

        System.out.println("Enter first name (or \'back\'):");
        String firstName = ih.enterWord();
        if(firstName.equalsIgnoreCase("back")){
            return;
        }
        System.out.println("Enter last name(or \'back\'):");
        String lastName = ih.enterWord();
        if(lastName.equalsIgnoreCase("back")){
            return;
        }
        List<Book> booksByAuthor = bookManager.getBooksByAuthor(new Author(firstName, lastName));
        System.out.println("===== Search results =====\n");
        if(!booksByAuthor.isEmpty()){
            bookManager.showAllBooks(booksByAuthor);
        } else {
            System.out.println("There are no books of " + firstName + " " + lastName + ".");
        }

    }

    protected void doSearchByTitleMenu(){

        System.out.println("Enter the title (or \'back\'):");
        String title = ih.enterPhrase();
        if(title.equalsIgnoreCase("back")){
            return;
        }
        List<Book> booksByTitle = bookManager.getBooksByTitle(title);
        System.out.println("===== Search results =====\n");
        if(!booksByTitle.isEmpty()){
            bookManager.showAllBooks(booksByTitle);
        } else {
            System.out.println("There are no books with title \'" + title +  "\'.");
        }

    }

    protected void doSearchByTypeMenu(){

        System.out.println("Enter the type of book (\'paper\', \'ebook\') or \'back\':");
        String type = ih.enterWord();
        List<Book> booksByType = new ArrayList<>();
        if(type.equalsIgnoreCase("back")){
            return;
        } else if(type.equalsIgnoreCase("paper")){
            booksByType = bookManager.getBooksByType(BookType.PAPER);
        } else if(type.equalsIgnoreCase("ebook")){
            booksByType = bookManager.getBooksByType(BookType.PAPER);
        }
        System.out.println("===== Search results =====\n");
        if(!booksByType.isEmpty()){
            bookManager.showAllBooks(booksByType);
        } else {
            System.out.println("There are no books with type \'" + type +  "\'.");
        }

    }
}
