package com.mySampleApplication.client.ui;


import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DefaultDateTimeFormatInfo;
import com.google.gwt.i18n.client.impl.cldr.DateTimeFormatInfoImpl_en;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;

public abstract class Dialog extends Composite {
    protected  Button cancelButton = new Button("Отмена");
    protected  DateBox dateInput = new DateBox();
    protected  TextBox titleInput = new TextBox();
    protected  TextBox authorInput = new TextBox();
    protected  VerticalPanel verticalPanel = new VerticalPanel();

    public Button getCancelButton() {
        return cancelButton;
    }

    public DateBox getDateInput() {
        return dateInput;
    }

    public TextBox getTitleInput() {
        return titleInput;
    }

    public TextBox getAuthorInput() {
        return authorInput;
    }

    public VerticalPanel getVerticalPanel() {
        return verticalPanel;
    }

    protected void initDialog(DialogBox box, Button button) {
        DefaultDateTimeFormatInfo formatEN =  new DateTimeFormatInfoImpl_en();
        dateInput.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat(formatEN.dateFormatMedium())));
        dateInput.setStyleName("dateInput");
        verticalPanel.setWidth("160");
        verticalPanel.add(new HTML("Название: "));
        verticalPanel.add(titleInput);
        verticalPanel.add(new HTML("<p></p>"));
        verticalPanel.add(new HTML("Автор: "));
        verticalPanel.add(authorInput);
        verticalPanel.add(new HTML("<p></p>"));
        verticalPanel.add(new HTML("Дата: "));
        verticalPanel.add(dateInput);
        verticalPanel.add(new HTML("<p></p>"));
        verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        verticalPanel.add(cancelButton);
        verticalPanel.add(button);

        box.add(verticalPanel);
        box.addStyleName("dialog");
        cancelButton.addClickHandler(event -> box.hide());
    }

    protected abstract void initButton(Button button);

    protected abstract void show();


}
