package com.elitehomes.view.base;


import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;

@CssImport(value = "./styles/styles.css")
public class MainLayout extends AppLayout {

    private static final String SELECT_OPERADOR = "Selecione um operador.";

    private MainHeader mainHeader;

    public MainLayout() {
        mainHeader = new MainHeader();
        addToNavbar(mainHeader);
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
    }

}
