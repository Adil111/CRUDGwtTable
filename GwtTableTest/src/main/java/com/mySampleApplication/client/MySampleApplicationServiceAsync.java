package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mySampleApplication.client.objects.Book;
import com.mySampleApplication.client.objects.BookResult;

public interface MySampleApplicationServiceAsync {

    void addBook(Book book, AsyncCallback<BookResult> async);

    void changeBook(BookResult bookResult, AsyncCallback<BookResult> async);

    void deleteBook(int id, AsyncCallback<BookResult> async);
}
