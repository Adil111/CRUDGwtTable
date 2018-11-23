package com.mySampleApplication.client.ui;


import com.google.gwt.cell.client.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.*;
import com.mySampleApplication.client.MySampleApplication;
import com.mySampleApplication.client.MySampleApplicationService;
import com.mySampleApplication.client.objects.BookResult;

import java.util.List;
//Todo: use DataGrid
public class CellTableTest extends Composite {
    interface CellTableTestUiBinder extends UiBinder<HTMLPanel, CellTableTest> { }
    private static CellTableTestUiBinder ourUiBinder = GWT.create(CellTableTestUiBinder.class);
    final static SelectionModel<BookResult> selectionModel = new MultiSelectionModel<>();

    @UiField
    CellTable<BookResult> table;
    @UiField
    Button addButton;
    @UiField
    Button deleteButton;

    public CellTableTest() {
        initWidget(ourUiBinder.createAndBindUi(this));
        initColumns(table);
        addButton.setText("Добавить");
        deleteButton.setText("Удалить");
        initAddButton(addButton);
        initDeleteButton(deleteButton);
    }

    private void initDeleteButton(Button deleteButton) {
        deleteButton.addClickHandler(event -> {
            List<BookResult> resultList = MySampleApplication.getProvider().getList();
            for (int i = 0; i < resultList.size(); i++) {
                if(table.getVisibleItems().get(i).isChecked()){
                    MySampleApplication.getProvider().getList().remove(resultList.get(i));
                    MySampleApplication.getProvider().refresh();
                    MySampleApplicationService.App.getInstance().deleteBook(i, new AsyncCallback<BookResult>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            Window.alert(caught.getMessage());
                        }
                        @Override
                        public void onSuccess(BookResult result) { }
                    });
                    i--;
                }
            }
        });
    }

    private void initAddButton(Button button) {
        button.addClickHandler(event -> MySampleApplication.getAddingDialog().show());
    }

    private void initColumns(CellTable<BookResult> table) {
        TextColumn<BookResult> title = new TextColumn<BookResult>() {
            @Override
            public String getValue(BookResult book) {
                return book.getTitle();
            }
        };
        TextColumn<BookResult> author = new TextColumn<BookResult>() {
            @Override
            public String getValue(BookResult book) {
                return book.getAuthor();
            }
        };
        TextColumn<BookResult> date = new TextColumn<BookResult>() {
            @Override
            public String getValue(BookResult book) {
                return MySampleApplication.getFormatInput().format(book.getDate());
            }
        };

        Column<BookResult, Boolean> checkBoxColumn = new Column<BookResult, Boolean>(new CheckboxCell()) {
            @Override
            public Boolean getValue(BookResult bookResult) {
                bookResult.setChecked(selectionModel.isSelected(bookResult));
                return bookResult.isChecked();
            }
        };
        ButtonCell buttonCell = new ButtonCell();
        Column buttonColumn = new Column<BookResult, String>(buttonCell) {
            @Override
            public String getValue(BookResult object) {
                return "Изменить";
            }
        };

        buttonColumn.setFieldUpdater(new FieldUpdater<BookResult, String>() {
            public void update(int index, BookResult result, String value) {
                MySampleApplication.getChangingDialog().show(result);
            }
        });
        table.addColumn(checkBoxColumn, "");
        table.addColumn(title, "Название");
        table.addColumn(author, "Автор");
        table.addColumn(date, "Дата");
        table.addColumn(buttonColumn, "Button");

        table.setSelectionModel(selectionModel, DefaultSelectionEventManager.<BookResult> createCheckboxManager());
    }

    public static SelectionModel <BookResult> getSelectionModel() { return selectionModel; }
    public CellTable<BookResult> getTable() { return table; }
    public Button getAddButton() { return addButton; }
    public Button getDeleteButton() { return deleteButton; }
}