package com.mySampleApplication.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mySampleApplication.client.MySampleApplicationService;
import com.mySampleApplication.client.objects.Book;
import com.mySampleApplication.client.objects.BookResult;


public class MySampleApplicationServiceImpl extends RemoteServiceServlet implements MySampleApplicationService {

    @Override
    public BookResult addBook(Book book) {
        Library.addBook(book);
        BookResult bookResult = new BookResult(Library.getBooksSize()-1,book.getTitle(),book.getAuthor(),book.getDate());
        return bookResult;
    }

    @Override
    public BookResult changeBook(BookResult bookResult) {
        Book book = new Book(bookResult.getTitle(),bookResult.getAuthor(),bookResult.getDate());
        Library.updateBook(bookResult.getId(), book);
        return bookResult;
    }

    @Override
    public BookResult deleteBook(int id) {
        Book book = Library.getBook(id);
        BookResult bookresult = new BookResult(id,book.getTitle(),book.getAuthor(),book.getDate());
        Library.deleteBook(id);
        return bookresult;
    }


}