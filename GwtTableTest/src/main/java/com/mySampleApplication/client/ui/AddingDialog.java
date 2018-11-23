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

public class AddingDialog extends Dialog {
    interface AddingDialogUiBinder extends UiBinder<HTMLPanel, AddingDialog> {}
    private static AddingDialogUiBinder ourUiBinder = GWT.create(AddingDialogUiBinder.class);

    private Button addButton = new Button();
    @UiField
    DialogBox dialogBox = new DialogBox();

    public AddingDialog() {
        initWidget(ourUiBinder.createAndBindUi(this));
        initDialog(dialogBox, addButton);
        initButton(addButton);
    }

    @Override
    protected void initButton(Button button) {
        button.setText("Добавить");
        button.addClickHandler(event -> {
            Book book = new Book(titleInput.getText(),authorInput.getText(),dateInput.getValue());
            if(!(Validator.isBookValid(book))){ return;}
            MySampleApplicationService.App.getInstance().addBook(book, new AsyncCallback<BookResult>() {
                @Override
                public void onFailure(Throwable caught) {
                    Window.alert(caught.getMessage());
                }
                @Override
                public void onSuccess(BookResult result) {
                    MySampleApplication.getProvider().getList().add(result);
                    dialogBox.hide();
                }
            });
        });
    }
    public void show(){
        dialogBox.show();
    }
}