package com.elitehomes.view.base;

import com.elitehomes.view.components.builder.ButtonBuilder;
import com.elitehomes.view.components.builder.LayoutBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.vaadin.lineawesome.LineAwesomeIcon;


@CssImport(value = "./styles/component-style.css")
public class MainHeader extends Composite<HorizontalLayout>  {

    public MainHeader() {

        HorizontalLayout leftLayout = LayoutBuilder.builder()
                .setComponents(getIcon(LineAwesomeIcon.CHEVRON_DOWN_SOLID))
                .setWidthFull()
                .setPadding(true)
                .buildHorizontal();

        MenuBar menuBar = new MenuBar();
        menuBar.addItem(buildItem(LineAwesomeIcon.USER_CIRCLE, "Área do Cliente"));

        Button btnLoc = ButtonBuilder.builder().setText("Locações").build();
        Button btnVen = ButtonBuilder.builder().setText("Vendas").build();
        Button btnLan = ButtonBuilder.builder().setText("Lançamentos").build();

        HorizontalLayout divisor = LayoutBuilder.builder()
                .setClassName("divisor")
                .buildHorizontal();

        HorizontalLayout rightLayout = LayoutBuilder.builder()
                .setPadding(true)
                .setAlignment(FlexComponent.Alignment.CENTER, btnLoc, btnVen, btnLan, divisor, menuBar)
                .setComponents(btnLoc, btnVen, btnLan, divisor, menuBar, divisor, menuBar)
                .buildHorizontal();

        getContent().add(leftLayout, rightLayout);
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().getStyle().set("gap", "0");
        getContent().setPadding(false);
    }

    private HorizontalLayout buildItem(LineAwesomeIcon icon, String title) {
        H6 h6 =  new H6(title);
        return LayoutBuilder.builder()
                .setComponents(icon.create(), h6)
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
