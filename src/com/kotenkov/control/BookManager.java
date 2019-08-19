package com.kotenkov.control;

import com.kotenkov.entity.Author;
import com.kotenkov.entity.Book;
import com.kotenkov.entity.BookType;
import com.kotenkov.input_output.file.FileProcessing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    List<Book> books;
    FileProcessing fp;

    public BookManager(List<Book> books, FileProcessing fp) {
        this.books = books;
        this.fp = fp;
    }

    public List<Book> getBooksByAuthor(Author author){
        List<Book> list = new ArrayList<>();

        for (Book b: this.books) {
            if(b.getAuthor().equals(author)){
                list.add(b);
            }
        }

        return list;
    }

    public List<Book> getBooksByTitle(String title){
        List<Book> list = new ArrayList<>();

        for (Book b: this.books) {
            String bookName = b.getTitle();
            if(bookName.substring(1, bookName.length()-1).equalsIgnoreCase(title)){
                list.add(b);
            }
        }

        return list;
    }

    public List<Book> getBooksByType(BookType type){
        List<Book> list = new ArrayList<>();

        for (Book b: this.books) {
            if(b.getType() == type){
                list.add(b);
            }
        }

        return list;
    }

    public void addBook(Book book){
        if(!this.books.contains(book)){
            this.books.add(book);
            try {
                fp.writeBooks(this.books, "books.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("The book has been added.");
        } else {
            System.out.println("There is the same book in the bookManager. The book hasn't been added.");
        }
    }

    public void deleteBook(int bookNumber){
        this.books.remove(bookNumber - 1);
        try {
            fp.writeBooks(this.books, "books.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("The book has been deleted.");
    }

    public void showAllBooks(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.books.size(); i++) {
            System.out.print(i+1 + ". ");
            System.out.println(oneBookToText(books.get(i), sb));
        }
    }

    public void showAllBooks(List<Book> books){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < books.size(); i++) {
            System.out.print(i+1 + ". ");
            System.out.println(oneBookToText(books.get(i), sb));
        }
    }

    private String oneBookToText(Book book, StringBuilder sb){
        sb.delete(0, sb.length());
        sb.append("Author: ");
        sb.append(book.getAuthor().getFirstName());
        sb.append(" ");
        sb.append(book.getAuthor().getLastName());
        sb.append("\nTitle: ");
        sb.append(book.getTitle());
        sb.append("\nInfo: ");
        sb.append(book.getYear());
        sb.append(", ");
        sb.append(book.getType());
        sb.append("\nShort description: ");
        sb.append(book.getShortDesc());
        sb.append("\n");

        return sb.toString();
    }

    public List<Book> getBooks() {
        return books;
    }

}
