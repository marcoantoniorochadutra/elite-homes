package com.elitehomes.view.search;


import com.elitehomes.view.base.MainLayout;
import com.elitehomes.view.client.PropertyClient;
import com.elitehomes.view.components.builder.ButtonBuilder;
import com.elitehomes.view.components.builder.LayoutBuilder;
import com.elitehomes.view.components.ref.ButtonType;
import com.elitehomes.view.entity.LoginDto;
import com.elitehomes.view.entity.PropertyResultDto;
import com.elitehomes.view.entity.ref.PropertyGoal;
import com.infraleap.animatecss.Animated;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.List;
import java.util.Objects;

@PageTitle("Search")
@Route(value = "search/#/:rs", layout = MainLayout.class)
@Uses(Icon.class)
@CssImport(value = "./styles/styles.css")
public class SearchView extends Composite<VerticalLayout> implements BeforeEnterObserver {

    private final PropertyClient propertyClient;

    private ComboBox<PropertyGoal> goalCombo;
    private List<PropertyResultDto> properties;
    private VerticalLayout propertiesList;
    private VerticalLayout locFilter;

    @Autowired
    public SearchView(PropertyClient propertyClient) {
        this.propertyClient = propertyClient;
        getContent().setClassName("layout-inner-shadow");

        VerticalLayout property = createPropertySection();
        VerticalLayout filter = createFilterSection();

        HorizontalLayout main = LayoutBuilder.builder()
                .setWidthFull()
                .setHeightFull()
                .setComponents(filter, property)
                .buildHorizontal();

        getContent().add(main);
        getContent().setHeightFull();
    }

    private VerticalLayout createFilterSection() {
        VerticalLayout filterWrapper = LayoutBuilder.builder()
                .setClassName("bg-border-nh")
                .setWidth("25%")
                .setHeightFull()
                .buildVertical();


        goalCombo = new ComboBox<>();
        goalCombo.setLabel("Finalidade");
        goalCombo.setItems(PropertyGoal.values());
        goalCombo.setItemLabelGenerator(PropertyGoal::getValue);
        goalCombo.setWidthFull();

        IntegerField bathroomCount = createIntegerField("Banheiros");
        IntegerField bedroomCount = createIntegerField("Quartos");
        IntegerField parkingSpaces = createIntegerField("Vagas");


        VerticalLayout filterLayout = LayoutBuilder.builder()
                .setWidthFull()
                .setHeightFull()
                .setComponents(goalCombo, createValueRange(), createLocationFilter(), createTypeFilter(), bathroomCount, bedroomCount, parkingSpaces)
                .buildVertical();

        Button buscar = ButtonBuilder.builder()
                .setType(ButtonType.PRIMARY)
                .setWidthFull(true)
                .setText("Buscar")
                .build();

        filterWrapper.add(filterLayout, buscar);
        return filterWrapper;
    }

    private VerticalLayout createTypeFilter() {
        ComboBox<String> grupo = new ComboBox<>("Tipo de propriedade");
        grupo.setPlaceholder("Grupo de propriedades");
        grupo.setWidthFull();
        grupo.setItems(List.of("Residential", "Industrial"));

        ComboBox<String> type = new ComboBox<>();
        type.setClassName("eh-hidden");
        type.setWidthFull();
        type.setPlaceholder("Tipo de propriedade");
        type.setEnabled(false);
        type.setItems(List.of("", "RESIDENTIAL", "CONDOMINIUM", "STUDIO"));

        grupo.addValueChangeListener(event -> {
            type.setEnabled(true);
            grupo.setEnabled(false);
        });

        type.addValueChangeListener(event -> {
            if(StringUtils.isBlank(event.getValue())) {
                grupo.setValue(null);
                grupo.setEnabled(true);
                type.setEnabled(false);
            }
        });

        locFilter = LayoutBuilder.builder()
                .setSpacing(false)
                .setPadding(false)
                .setComponents(grupo, type)
                .buildVertical();

        return LayoutBuilder.builder()
                .setComponents(locFilter)
                .setPadding(false)
                .setWidthFull()
                .buildVertical();
    }

    private VerticalLayout createLocationFilter() {
        ComboBox<String> estado = new ComboBox<>("Localização");
        estado.setPlaceholder("Estado");
        estado.setWidthFull();
        estado.setItems(List.of("SC", "SP", "AM"));

        ComboBox<String> cidade = new ComboBox<>();
        cidade.setClassName("eh-hidden");
        cidade.setWidthFull();
        cidade.setPlaceholder("Cidade");
        cidade.setEnabled(false);
        cidade.setItems(List.of("", "Tubarão", "Imbituba", "Laguna"));

        estado.addValueChangeListener(event -> {
            cidade.setEnabled(true);
            estado.setEnabled(false);
        });

        cidade.addValueChangeListener(event -> {
            if(StringUtils.isBlank(event.getValue())) {
                estado.setValue(null);
                estado.setEnabled(true);
                cidade.setEnabled(false);
            }
        });

        locFilter = LayoutBuilder.builder()
                .setSpacing(false)
                .setPadding(false)
                .setComponents(estado, cidade)
                .buildVertical();

        return LayoutBuilder.builder()
                .setComponents(locFilter)
                .setPadding(false)
                .setWidthFull()
                .buildVertical();
    }

    private IntegerField createIntegerField(String title) {
        IntegerField bathroomCount = new IntegerField(title);
        bathroomCount.setMin(0);
        bathroomCount.setPlaceholder("0");
        bathroomCount.setWidthFull();
        bathroomCount.setStepButtonsVisible(true);
        return bathroomCount;
    }

    private FlexLayout createValueRange() {

        BigDecimalField minVal = new BigDecimalField("Valor Mínimo");
        minVal.setPlaceholder("0,00");
        minVal.setWidth("100%");
        minVal.setPrefixComponent(createPrefix());

        BigDecimalField maxVal = new BigDecimalField("Valor Maximo");
        maxVal.setPlaceholder("0,00");
        maxVal.setWidth("100%");
        maxVal.setPrefixComponent(createPrefix());


        FlexLayout dateRangeComponent = new FlexLayout(minVal, new Text(" – "), maxVal);
        dateRangeComponent.setAlignItems(FlexComponent.Alignment.BASELINE);
        dateRangeComponent.addClassName(LumoUtility.Gap.XSMALL);
        dateRangeComponent.setWidth("100%");

        return dateRangeComponent;
    }

    private Div createPrefix() {
        Div dollarPrefix = new Div();
        dollarPrefix.setText("R$");
        return dollarPrefix;
    }

    private VerticalLayout createPropertySection() {

        TextField textField = new TextField();
        textField.setPlaceholder("Pesquisa Direta");
        textField.setTooltipText("Utilizado para buscar imóveis com base no seu endereço ou título");

        Button table = ButtonBuilder.builder()
                .setType(ButtonType.PRIMARY)
                .setIcon(LineAwesomeIcon.TH_SOLID.create())
                .build();

        Button tableLarge = ButtonBuilder.builder()
                .setType(ButtonType.PRIMARY)
                .setIcon(LineAwesomeIcon.TH_LARGE_SOLID.create())
                .build();

        Button tableList = ButtonBuilder.builder()
                .setType(ButtonType.PRIMARY)
                .setIcon(LineAwesomeIcon.LIST_SOLID.create())
                .build();

        HorizontalLayout tools = LayoutBuilder.builder()
                .setWidthFull()
                .setJustify(JustifyContentMode.END)
                .setComponents(textField, table, tableLarge, tableList)
                .buildHorizontal();

        propertiesList = LayoutBuilder.builder()
                .setClassName("prop-grid")
                .setHeightFull()
                .setWidthFull()
                .buildVertical();

        HorizontalLayout footer = LayoutBuilder.builder()
                .setComponents(new Button())
                .buildHorizontal();


        return LayoutBuilder.builder()
                .setComponents(tools, propertiesList, footer)
                .buildVertical();
   }

    private VerticalLayout createCard(PropertyResultDto item) {
        Image img = new Image("https://images.unsplash.com/photo-1513147122760-ad1d5bf68cdb?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=400&q=80", "Image");
        img.setWidthFull();

        H3 title = new H3(item.getTitle());
        H6 descriptionn = new H6(StringUtils.abbreviate(item.getDescription(), 150));

        HorizontalLayout bedroom = createInfo(LineAwesomeIcon.BED_SOLID, item.getNumBedroom(), "Quartos");
        HorizontalLayout bathroom = createInfo(LineAwesomeIcon.TOILET_SOLID, item.getNumBathroom(), "Banheiros");
        HorizontalLayout suite = createInfo(LineAwesomeIcon.BATH_SOLID, item.getNumSuite(), "Suítes");
        HorizontalLayout parking = createInfo(LineAwesomeIcon.WAREHOUSE_SOLID, item.getParkingSpaces(), "Vagas");

        HorizontalLayout info = LayoutBuilder.builder()
                .setWidthFull()
                .setComponents(bedroom, bathroom, suite, parking)
                .setAlignment(Alignment.CENTER, bedroom, bathroom, suite, parking)
                .buildHorizontal();

        info.getStyle().set("font-size", "17px");

        return LayoutBuilder.builder()
                .setWidthFull()
                .setComponents(img, title, descriptionn, info)
                .setAlignment(Alignment.CENTER, img)
                .setClassName("prop-card")
                .buildVertical();
    }

    private HorizontalLayout createInfo(LineAwesomeIcon icon, Integer val, String toolText) {
        Component s = icon.create();
        Text value = new Text(val.toString());

        Tooltip.forComponent(s)
                .withText(toolText)
                .withPosition(Tooltip.TooltipPosition.TOP);

        return LayoutBuilder.builder()
                .setAlignItem(Alignment.CENTER)
                .setComponents(s, value)
                .buildHorizontal();
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {

        String path = event.getRouteParameters().get("rs").orElse(null);
        String goal = event.getLocation().getQueryParameters().getParameters("goal").getFirst();
        goalCombo.setValue(PropertyGoal.fromStr(goal));
        if(Objects.isNull(path)) {
            // TODO Redirect to blank page
        } else {
            LoginDto login = new LoginDto();
            login.setRealEstateKey(path);
            properties =  propertyClient.searchProperties(login);
            properties.forEach(item -> propertiesList.add(createCard(item)));
        }
    }

    private void populateCards() {
        if(Objects.nonNull(properties)) {
            createPropertySection();
            properties.forEach(item -> {
                VerticalLayout s = createCard(item);
                getContent().add(s);
                propertiesList.add(s);
            });
        }
    }
}
