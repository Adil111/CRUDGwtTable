package com.mySampleApplication.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.mySampleApplication.client.MySampleApplication;
import com.mySampleApplication.client.objects.BookResult;

import java.util.ArrayList;
import java.util.List;
public class Filter extends Composite {
    private String searchString;
    private List<BookResult> formList;
    @UiField
    VerticalPanel panel = new VerticalPanel();
    TextBox searchBox = new TextBox();
    Button searchButton = new Button("Искать!");
    interface FilterUiBinder extends UiBinder<HTMLPanel, Filter> {
    }

    private static FilterUiBinder ourUiBinder = GWT.create(FilterUiBinder.class);

    public Filter() {
        initWidget(ourUiBinder.createAndBindUi(this));
        panel.add(new HTML("Название или имя автора: "));
        panel.add(searchBox);
        panel.add(searchButton);
        panel.addStyleName("dialog");

        searchButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Window.alert("Ищу");
                search();
            }
        });
    }

    private void search() {
        searchString = searchBox.getText();
        if((searchString == null) || searchString.equals("")){MySampleApplication.getProvider().refresh();}
        else {
            setData();
            Window.alert(searchString);
        }
    }


    public void setData() {
        formList = MySampleApplication.getProvider().getList();
        if (formList != null && formList.size() > 0) {
            Window.alert(formList.size()+"");
            AsyncDataProvider<BookResult> provider = new AsyncDataProvider<BookResult>() {
                @Override
                protected void onRangeChanged(HasData<BookResult> display) {
                    int start = 0;
                    int end = formList.size();
                    List<BookResult> sub = getSubList();
                    formList = sub;
                    Window.alert("" + sub.size());
                    updateRowData(0, sub);
                }
            };
            provider.addDataDisplay(MySampleApplication.getTable().getTable());
            provider.updateRowCount(formList.size(), true);
        }
    }

    private List<BookResult> getSubList() {
        List<BookResult> filtered_list = null;
        if (searchString != null) {
            filtered_list= new ArrayList<BookResult>();
            for (BookResult form : formList) {
                if (form.getTitle().equals(searchString) || form.getAuthor().equals(searchString))
                    filtered_list.add(form);
            }
        }
        else
            filtered_list = formList;
        return filtered_list;
    }
}