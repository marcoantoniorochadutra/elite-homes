package com.elitehomes.view.components;



import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;

public class DefaultDialogs {

    public static Dialog exclusionDialog(String title, String description, Button deleteButton, Button cancelButton) {
        Dialog dialog = new Dialog();
        dialog.setCloseOnOutsideClick(true);
        dialog.setHeaderTitle(title);
        dialog.add(description);
        cancelButton.addClickListener(event -> dialog.close());
        deleteButton.addClickListener(event -> dialog.close());



        return dialog;
    }

}
