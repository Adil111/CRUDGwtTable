package com.mySampleApplication.client.objects;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Book implements Serializable {
    private String title;
    private String author;
    private Date date;

    public Book(String title, String author, Date date) {
        this.title = title;
        this.author = author;
        this.date = date;
    }

    public Book(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(date, book.date);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = prime * getTitle().hashCode() *
                getAuthor().hashCode() + getDate().hashCode();
        return result;
    }
}
