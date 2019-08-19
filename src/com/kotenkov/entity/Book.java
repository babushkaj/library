package com.kotenkov.entity;

public class Book {
    protected Author author;
    protected String title;
    protected int year;
    protected BookType type;
    protected String shortDesc;

    public Book() {
    }

    public Book(Author author, String title, int year, BookType type, String shortDesc) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.type = type;
        this.shortDesc = shortDesc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author=" + author +
                ", year=" + year +
                ", type=" + type +
                ", shortDesc='" + shortDesc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (year != book.year) return false;
        if (!title.equals(book.title)) return false;
        if (!author.equals(book.author)) return false;
        if (type != book.type) return false;
        return shortDesc.equals(book.shortDesc);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + year;
        result = 31 * result + type.hashCode();
        result = 31 * result + shortDesc.hashCode();
        return result;
    }
}
