package com.elitehomes.core.config;

import com.elitehomes.core.constants.CoreReturnMessage;

import java.util.ResourceBundle;

public class ReturnMessage {

    private static final String BUNDLE_PATH = "return_messages/messages";

    public static String getMessage(CoreReturnMessage msg) {
        return findMessage(msg);
    }

    public static String getMessageWithField(CoreReturnMessage msg, String field) {
        String message = findMessage(msg);
        String placeholder = "%s [ %s ]";
        return String.format(placeholder, message, field);
    }

    private static String findMessage(CoreReturnMessage msg) {
        String message;
        try {
            ResourceBundle rs = ResourceBundle.getBundle(BUNDLE_PATH, LocaleContext.getCurrentLocale());
            message = rs.getString(msg.name());
        } catch (Exception e) {
            message = "UNDEFINED";
        }
        return message;
    }

}
