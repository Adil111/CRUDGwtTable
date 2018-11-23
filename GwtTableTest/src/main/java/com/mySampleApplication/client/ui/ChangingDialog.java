package com.mySampleApplication.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.mySampleApplication.client.MySampleApplication;
import com.mySampleApplication.client.MySampleApplicationService;
import com.mySampleApplication.client.objects.Book;
import com.mySampleApplication.client.objects.BookResult;
import com.mySampleApplication.client.shared.Validator;

import java.util.Date;

public class ChangingDialog extends Dialog {
    interface ChangingDialogUiBinder extends UiBinder<HTMLPanel, ChangingDialog> { }
    private static ChangingDialogUiBinder ourUiBinder = GWT.create(ChangingDialogUiBinder.class);

    private Button changeButton = new Button();
    @UiField
    DialogBox dialogBox = new DialogBox();
    private int currentIndex;

    public ChangingDialog() {
        initWidget(ourUiBinder.createAndBindUi(this));
        initDialog(dialogBox, changeButton);
        initButton(changeButton);
    }

    @Override
    protected void initButton(Button button) {
        button.setText("Сохранить");
        button.addClickHandler(event -> {
            String title = getTitleInput().getText();
            String author = getAuthorInput().getText();
            Date date = getDateInput().getValue();
            Book book = new Book(title,author,date);
            if(!(Validator.isBookValid(book))){ return;}
            BookResult result = new BookResult(currentIndex,title,author,date);
            MySampleApplicationService.App.getInstance().changeBook(result, new AsyncCallback<BookResult>() {
                @Override
                public void onFailure(Throwable caught) {
                    Window.alert(caught.getMessage());
                }

                @Override
                public void onSuccess(BookResult result) {
                    MySampleApplication.getProvider().getList().set(currentIndex,result);
                    dialogBox.hide();
                }
            });
        });
    }

    @Override
    protected void show() {dialogBox.show();}

    protected void show(BookResult result) {
        currentIndex = result.getId();
        dialogBox.show();
    }
}