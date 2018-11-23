package com.mySampleApplication.client.objects;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class BookResult implements Serializable {
    private int id;
    private boolean checked;
    private String Title;
    private String Author;
    private Date date;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public BookResult(){}

    public BookResult(int id, String title, String author, Date date) {
        this.id = id;
        Title = title;
        Author = author;
        this.date = date;
    }

    public BookResult(String title, String author, Date date) {
        Title = title;
        Author = author;
        this.date = date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookResult that = (BookResult) o;
        return Objects.equals(Title, that.Title) &&
                Objects.equals(Author, that.Author) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = prime * getTitle().hashCode() *
                getAuthor().hashCode() + getDate().hashCode();
        return result;
    }
}
