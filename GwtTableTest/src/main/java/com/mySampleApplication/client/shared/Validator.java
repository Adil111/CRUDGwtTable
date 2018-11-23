package com.mySampleApplication.client.shared;

import com.google.gwt.user.client.Window;
import com.mySampleApplication.client.MySampleApplication;
import com.mySampleApplication.client.objects.Book;
import com.mySampleApplication.client.objects.BookResult;


import java.util.Date;

public class Validator {
    private Validator(){}

    public static boolean isBookValid(Book book){
        String title = book.getTitle();
        String author = book.getAuthor();
        Date date = book.getDate();
        BookResult result = new BookResult(title,author,date);

        boolean isValid1 = !(title == null || title.length() == 0 ||
                author == null || author.length() == 0 ||
                date == null || MySampleApplication.getProvider().getList().contains(result));

        if(!isValid1) {
            Window.alert("Введены пустые поля или такая книга уже есть!");
            return false;
        }

        boolean isValid2 = author.matches("^[^0-9]*$");

        if(!isValid2) {
            Window.alert("Имя введено неверно!");
            return false;
        }
        return true;
    }
}
