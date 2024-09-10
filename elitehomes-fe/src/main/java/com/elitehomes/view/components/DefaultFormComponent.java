package com.elitehomes.view.components;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxBase;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class DefaultFormComponent {




    private static void configureCombo(String title, ComboBoxBase s) {
        s.setWidthFull();
        s.setPlaceholder(title);
        s.setClassName("default-form");
    }


    public static TextArea createTextArea(String title) {
        TextArea s = new TextArea();
        s.setClassName("neomorphism");
        s.setPlaceholder(title);
        s.setWidthFull();
        return s;
    }

    public static TextField createTextField(String title) {
        return createTextField(title, true);
    }

    public static TextField createTextField(String title, Boolean visibility) {
        TextField s = new TextField();
        s.setClassName("neomorphism");
        s.setPlaceholder(title);
        s.setWidthFull();
        s.setVisible(visibility);
        return s;
    }

    public static PasswordField createPasswordField(String title) {
        PasswordField s = new PasswordField();
        s.setMinLength(8);
        s.setClassName("neomorphism");
        s.setPlaceholder(title);
        s.setWidthFull();
        return s;
    }
}
