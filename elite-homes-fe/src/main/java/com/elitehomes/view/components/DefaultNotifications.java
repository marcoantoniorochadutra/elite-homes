package com.elitehomes.view.components;



import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

public class DefaultNotifications {

    public static Notification successNotification(String message) {
        Notification success = new Notification();
        success.setPosition(Notification.Position.TOP_CENTER);
        success.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        success.setText(message);
        success.setDuration(5000);
        success.open();
        return success;
    }

    public static Notification errorNotification(String message) {
        Notification error = new Notification();
        error.setPosition(Notification.Position.TOP_CENTER);
        error.addThemeVariants(NotificationVariant.LUMO_ERROR);
        error.setText(message);
        error.setDuration(5000);
        error.open();
        return error;
    }

}
