package com.kotenkov.input_output.file;

import com.kotenkov.entity.BookType;
import com.kotenkov.entity.UserRole;
import com.kotenkov.security.SimpleCrypto;
import com.kotenkov.entity.Author;
import com.kotenkov.entity.Book;
import com.kotenkov.entity.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileProcessing {

    public List<Book> readBooks(String path) throws IOException {
        List<Book> books = new ArrayList<>();

        String allBooks = null;
        try {
            allBooks = readAllFileToString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(new StringReader(allBooks.trim()));
        String s = null;
        Book book = null;
        int i = 1;
        book = new Book();
        while((s = br.readLine()) != null){
            if(i == 1){
                fillBookInformation(s.trim(), book);
                i++;
            } else {
                book.setShortDesc(s.trim());
                i = 1;
                books.add(book);
                book = new Book();
            }

        }

        return books;
    }

    public List<User> readUsers(String path) throws IOException {
        List<User> users = new ArrayList<>();

        String allUsers = null;
        try {
            allUsers = readAllFileToString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(new StringReader(allUsers.trim()));
        String s = null;
        while((s = br.readLine()) != null) {
            User user = new User();
            fillUserInformation(s.trim(), user);
            users.add(user);
        }
        return users;
    }

    public void writeBooks(List<Book> books, String path) throws IOException {
        BufferedWriter bw = null;
        FileOutputStream fos = null;
        try {
            File file = new File(path);
            if(!file.exists()) {
                try {
                    Files.createFile(Paths.get(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fos = new FileOutputStream(file);
            bw = new BufferedWriter(new OutputStreamWriter(fos,"utf8"));

            for (Book b: books) {
                bw.write(bookToString(b));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            bw.close();
            fos.close();
        }
    }


    public void writeUsers(List<User> users, String path) throws IOException {
        BufferedWriter bw = null;
        FileOutputStream fos = null;
        try {
            File file = new File(path);
            if(!file.exists()) {
                try {
                    Files.createFile(Paths.get(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fos = new FileOutputStream(file);
            bw = new BufferedWriter(new OutputStreamWriter(fos, "utf8"));

            for (User u: users) {
                bw.write(userToString(u));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            bw.close();
            fos.close();
        }
    }


    private static void fillBookInformation(String oneBookInfo, Book book){
        String[] bookInfoArr = oneBookInfo.split("/");

        book.setAuthor(new Author(bookInfoArr[0].trim(), bookInfoArr[1].trim()));
        book.setTitle(bookInfoArr[2].trim());
        book.setYear(Integer.parseInt(bookInfoArr[3].trim()));
        book.setType(BookType.valueOf(bookInfoArr[4].trim()));
    }

    private void fillUserInformation(String userInfo, User user) {
        String [] userInfoArr = userInfo.split("&&");

        user.setLogin(userInfoArr[0]);
        user.setPass(SimpleCrypto.decode(userInfoArr[1].trim()));
        user.setEmail(userInfoArr[2].trim());
        user.setRole(UserRole.valueOf(userInfoArr[3].trim()));
    }

    private String bookToString(Book book){
        StringBuilder sb = new StringBuilder();

        sb.append(book.getAuthor().getFirstName());
        sb.append(" /");
        sb.append(book.getAuthor().getLastName());
        sb.append(" /\"");
        sb.append(book.getTitle());
        sb.append("\" /");
        sb.append(book.getYear());
        sb.append(" /");
        sb.append(book.getType());
        sb.append("\n");
        sb.append(book.getShortDesc());
        sb.append("\n\n");

        return sb.toString();
    }

    private String userToString(User user){
        StringBuilder sb = new StringBuilder();

        sb.append(user.getLogin());
        sb.append("&&");
        sb.append(SimpleCrypto.code(user.getPass()));
        sb.append("&&");
        sb.append(user.getEmail());
        sb.append("&&");
        sb.append(user.getRole());
        sb.append("\n\n");

        return sb.toString();
    }


    private String readAllFileToString(String path) throws IOException {

        BufferedReader br = null;
        FileInputStream fis = null;
        StringBuilder tmp = new StringBuilder();
        StringBuilder allBooks = new StringBuilder();
        try {
            File file = new File(path);
            if(file.exists()) {
                fis = new FileInputStream(file);
                br = new BufferedReader(new InputStreamReader(fis,"utf-8"));
            }
            while (br.ready()){
                tmp.append(br.readLine());
                if(!tmp.toString().isEmpty()){
                    allBooks.append(tmp);
                    allBooks.append("\n");
                }
                tmp.delete(0, tmp.length());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            br.close();
            fis.close();
        }

        return allBooks.toString();
    }

}
