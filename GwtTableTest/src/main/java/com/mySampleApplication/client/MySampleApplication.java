package com.mySampleApplication.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import com.mySampleApplication.client.objects.BookResult;
import com.mySampleApplication.client.ui.AddingDialog;
import com.mySampleApplication.client.ui.CellTableTest;
import com.mySampleApplication.client.ui.ChangingDialog;
import com.mySampleApplication.client.ui.Filter;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MySampleApplication implements EntryPoint {
    private static final ListDataProvider<BookResult> provider = new ListDataProvider<>();
    private static final  DateTimeFormat formatInput = DateTimeFormat.getFormat("dd.LL.yyyy");
    private static final AddingDialog addingDialogPanel = new AddingDialog();
    private static final ChangingDialog changingDialogPanel = new ChangingDialog();
    private static final Filter filter = new Filter();
    /**
     * This is the entry point method.
     */
    private static CellTableTest table = new CellTableTest();
    public void onModuleLoad() {
        provider.addDataDisplay(MySampleApplication.getTable().getTable());
        RootPanel.get("table").add(table);
        RootPanel.get("filter").add(filter);
    }

    public static DateTimeFormat getFormatInput() {
        return formatInput;
    }

    public static AddingDialog getAddingDialog() {return addingDialogPanel;}

    public static ChangingDialog getChangingDialog() { return changingDialogPanel; }

    public static CellTableTest getTable() { return table; }

    public static ListDataProvider<BookResult> getProvider() { return provider; }
}
