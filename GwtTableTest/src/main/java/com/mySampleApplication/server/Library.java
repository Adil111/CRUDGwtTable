package com.mySampleApplication.server;

import com.mySampleApplication.client.objects.Book;

import java.util.ArrayList;

public class Library {
    private static ArrayList<Book> books = new ArrayList<>();

    public static void addBook(Book book){ books.add(book); }
    public static void deleteBook(int id){ books.remove(id); }
    public static Book getBook(int num){ return books.get(num); }
    public static Book updateBook(int num, Book book){return books.set(num, book);}

    public static int getBooksSize() { return books.size(); }
}
