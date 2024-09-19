package com.elitehomes.view.utils;

import com.elitehomes.core.config.LocaleContext;
import com.elitehomes.core.constants.CoreReturnMessage;
import com.vaadin.flow.component.UI;

import java.util.ResourceBundle;

public class TranslationHelper {

    private static final String BUNDLE_PATH = "translation/messages";

    public static String getMessage(String msg) {
        return findMessage(msg);
    }

    public static String getMessageWithField(String msg, String field) {
        String message = findMessage(msg);
        String placeholder = "%s [ %s ]";
        return String.format(placeholder, message, field);
    }

    private static String findMessage(String msg) {
        String message;
        try {
            ResourceBundle rs = ResourceBundle.getBundle(BUNDLE_PATH, UI.getCurrent().getLocale());
            message = rs.getString(msg);
        } catch (Exception e) {
            message = "UNDEFINED";
        }
        return message;
    }

}
