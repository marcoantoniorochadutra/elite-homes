package com.elitehomes.view.base;

import com.elitehomes.core.config.LocaleContext;
import com.elitehomes.view.components.builder.ButtonBuilder;
import com.elitehomes.view.components.builder.LayoutBuilder;
import com.elitehomes.view.home.HomeView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Locale;


public class MainHeader extends Composite<HorizontalLayout>  {

    private ComboBox<Locale> locale;

    public MainHeader() {

        HorizontalLayout leftLayout = LayoutBuilder.builder()
                .setComponents(getIcon(LineAwesomeIcon.CHEVRON_DOWN_SOLID))
                .setWidthFull()
                .setPadding(true)
                .buildHorizontal();

        MenuBar menuBar = new MenuBar();
        menuBar.addItem(buildAreaCliente());

        MenuBar tresp = new MenuBar();
        tresp.addItem(buildMore());

        Button btnLoc = ButtonBuilder.builder().setText("Locações").setNavigate(HomeView.class).build();
        Button btnVen = ButtonBuilder.builder().setText("Vendas").build();
        Button btnLan = ButtonBuilder.builder().setText("Lançamentos").build();


        locale = new ComboBox<>();
        locale.setClassName("locale");
        locale.setItems(LocaleContext.AVAILABLE_LOCALE);
        locale.setValue(UI.getCurrent().getSession().getLocale());
        locale.addValueChangeListener(event -> updateComponents(event.getValue()));
        locale.setPrefixComponent(getIcon(LineAwesomeIcon.GLOBE_SOLID));
        locale.setItemLabelGenerator(Locale::getDisplayCountry);

        HorizontalLayout divisor = LayoutBuilder.builder()
                .setClassName("divisor")
                .buildHorizontal();

        HorizontalLayout rightLayout = LayoutBuilder.builder()
                .setPadding(true)
                .setAlignment(FlexComponent.Alignment.CENTER, btnLoc, btnVen, btnLan, locale, divisor, menuBar, tresp)
                .setComponents(btnLoc, btnVen, btnLan, locale, divisor, menuBar, tresp)
                .buildHorizontal();

        getContent().add(leftLayout, rightLayout);
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().getStyle().set("gap", "0");
        getContent().setPadding(false);
    }

    private void updateComponents(Locale value) {
        UI.getCurrent().getSession().setLocale(value);

    }

    private HorizontalLayout buildMore() {
        return LayoutBuilder.builder()
                .setClassName("tes")
                .setComponents(LineAwesomeIcon.ELLIPSIS_V_SOLID.create())
                .setJustify(FlexComponent.JustifyContentMode.CENTER)
                .buildHorizontal();
    }

    private HorizontalLayout buildAreaCliente() {
        H6 h6 =  new H6("Área do Cliente");
        return LayoutBuilder.builder()
                .setComponents(LineAwesomeIcon.USER_CIRCLE.create(), h6)
                .setAlignment(FlexComponent.Alignment.CENTER, h6)
                .setJustify(FlexComponent.JustifyContentMode.CENTER)
                .buildHorizontal();
    }



    private Component getIcon(LineAwesomeIcon icon) {
        Component s = icon.create();
        s.getStyle().set("color", "white");

        return s;
    }


}
