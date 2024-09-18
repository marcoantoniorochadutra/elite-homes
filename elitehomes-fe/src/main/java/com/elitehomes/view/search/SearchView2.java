//package com.elitehomes.view.search;
//
//
//import com.elitehomes.core.entity.base.SelectableDto;
//import com.elitehomes.core.model.result.PropertyResultDto;
//import com.elitehomes.view.base.MainLayout;
//import com.elitehomes.view.client.PropertyClient;
//import com.elitehomes.view.components.builder.ButtonBuilder;
//import com.elitehomes.view.components.builder.ComboBuilder;
//import com.elitehomes.view.components.builder.NumberFieldBuilder;
//import com.elitehomes.view.components.builder.LayoutBuilder;
//import com.elitehomes.view.components.ref.ComponentType;
//import com.elitehomes.view.entity.LoginDto;
//import com.vaadin.flow.component.Component;
//import com.vaadin.flow.component.Composite;
//import com.vaadin.flow.component.Text;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.combobox.ComboBox;
//import com.vaadin.flow.component.dependency.CssImport;
//import com.vaadin.flow.component.dependency.Uses;
//import com.vaadin.flow.component.html.Div;
//import com.vaadin.flow.component.html.H3;
//import com.vaadin.flow.component.html.H6;
//import com.vaadin.flow.component.html.Image;
//import com.vaadin.flow.component.icon.Icon;
//import com.vaadin.flow.component.orderedlayout.FlexComponent;
//import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
//import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
//import com.vaadin.flow.component.orderedlayout.FlexLayout;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.shared.Tooltip;
//import com.vaadin.flow.component.textfield.BigDecimalField;
//import com.vaadin.flow.component.textfield.IntegerField;
//import com.vaadin.flow.component.textfield.NumberField;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.router.BeforeEnterEvent;
//import com.vaadin.flow.router.BeforeEnterObserver;
//import com.vaadin.flow.router.PageTitle;
//import com.vaadin.flow.router.Route;
//import com.vaadin.flow.theme.lumo.LumoUtility;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.vaadin.lineawesome.LineAwesomeIcon;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//@PageTitle("Search")
//@Route(value = "search/#/:rs", layout = MainLayout.class)
//@Uses(Icon.class)
//@CssImport(value = "./styles/styles.css")
//public class SearchView extends Composite<VerticalLayout> implements BeforeEnterObserver {
//
//    private final PropertyClient propertyClient;
//
//    private List<PropertyResultDto> properties;
//    private List<SelectableDto> goalList;
//    private List<SelectableDto> groupList;
//    private List<SelectableDto> typeList;
//
//    private ComboBox<SelectableDto> goalCombo;
//    private ComboBox<SelectableDto> groupCombo;
//    private ComboBox<SelectableDto> typeCombo;
//    private IntegerField bathroomCount;
//    private IntegerField bedroomCount;
//    private IntegerField parkingSpaces;
//
//    private VerticalLayout propertiesList;
//    private VerticalLayout locFilter;
//
//    @Autowired
//    public SearchView(PropertyClient propertyClient) {
//        this.propertyClient = propertyClient;
//        populateComboItems();
//        createLayout();
//    }
//
//    private void populateComboItems() {
//        goalList = propertyClient.searchGoal();
//        groupList = propertyClient.searchGroup();
//    }
//
//    private void createLayout() {
//        getContent().setClassName("layout-inner-shadow");
//
//        VerticalLayout property = createPropertySection();
//        VerticalLayout filter = createFilterSection();
//
//        VerticalLayout footer = LayoutBuilder.builder()
//                .setComponents(new H6("Butters"))
//                .buildVertical();
//
//        HorizontalLayout main = LayoutBuilder.builder()
//                .setWidthFull()
//                .setHeightFull()
//                .setComponents(filter, property)
//                .buildHorizontal();
//
//        getContent().add(main, footer);
//        getContent().setHeightFull();
//    }
//
//    private VerticalLayout createFilterSection() {
//
//        goalCombo = ComboBuilder.builder()
//                .setTitle("Finalidade")
//                .setWidthFull()
//                .setItems(goalList)
//                .buildSelectable();
//
//        bathroomCount = createIntegerField("Banheiros");
//        bedroomCount = createIntegerField("Quartos");
//        parkingSpaces = createIntegerField("Vagas");
//
//        VerticalLayout filterLayout = LayoutBuilder.builder()
//                .setWidthFull()
//                .setHeightFull()
//                .setComponents(goalCombo,
//                        createValueRange(),
//                        createLocationFilter(),
//                        createTypeFilter(),
//                        bathroomCount,
//                        bedroomCount,
//                        parkingSpaces)
//                .buildVertical();
//
//        Button buscar = ButtonBuilder.builder()
//                .setType(ComponentType.PRIMARY)
//                .setWidthFull(true)
//                .setText("Buscar")
//                .build();
//
//        return LayoutBuilder.builder()
//                .setClassName("bg-border-nh")
//                .setWidth("25%")
//                .setComponents(filterLayout, buscar)
//                .setHeightFull()
//                .buildVertical();
//    }
//
//    private VerticalLayout createTypeFilter() {
//        groupCombo = ComboBuilder.builder()
//                .setTitle("Tipo de properiedade")
//                .setPlaceholder("Grupo de propriedades")
//                .setWidthFull()
//                .setItems(groupList)
//                .buildSelectable();
//
//        typeCombo = ComboBuilder.builder()
//                .setClassName("eh-hidden")
//                .setWidthFull()
//                .setPlaceholder("Tipo de propriedade")
//                .setEnabled(false)
//                .buildSelectable();
//
//
//        groupCombo.addValueChangeListener(event -> {
//            if (Objects.nonNull(event.getValue())) {
//                typeList = propertyClient.searchType(event.getValue().getKey());
//                List<SelectableDto> list = new ArrayList<>();
//                list.add(new SelectableDto("RETURN", "Return"));
//                list.addAll(typeList);
//
//                typeCombo.setItems(list);
//                typeCombo.setEnabled(true);
//                groupCombo.setEnabled(false);
//            }
//        });
//
//        typeCombo.addValueChangeListener(event -> {
//            if(Objects.isNull(event.getValue()) || event.getValue().getKey().equals("RETURN")) {
//                groupCombo.setEnabled(true);
//                typeCombo.setEnabled(false);
//            }
//        });
//
//        locFilter = LayoutBuilder.builder()
//                .setSpacing(false)
//                .setPadding(false)
//                .setComponents(groupCombo, typeCombo)
//                .buildVertical();
//
//        return LayoutBuilder.builder()
//                .setComponents(locFilter)
//                .setPadding(false)
//                .setWidthFull()
//                .buildVertical();
//    }
//
//    private VerticalLayout createLocationFilter() {
//        ComboBox<String> estado = new ComboBox<>("Localização");
//        estado.setPlaceholder("Estado");
//        estado.setWidthFull();
//        estado.setItems(List.of("SC", "SP", "AM"));
//
//        ComboBox<String> cidade = new ComboBox<>();
//        cidade.setClassName("eh-hidden");
//        cidade.setWidthFull();
//        cidade.setPlaceholder("Cidade");
//        cidade.setEnabled(false);
//        cidade.setItems(List.of("", "Tubarão", "Imbituba", "Laguna"));
//
//        estado.addValueChangeListener(event -> {
//            cidade.setEnabled(true);
//            estado.setEnabled(false);
//        });
//
//        cidade.addValueChangeListener(event -> {
//            if(StringUtils.isBlank(event.getValue())) {
//                estado.setValue(null);
//                estado.setEnabled(true);
//                cidade.setEnabled(false);
//            }
//        });
//
//        locFilter = LayoutBuilder.builder()
//                .setSpacing(false)
//                .setPadding(false)
//                .setComponents(estado, cidade)
//                .buildVertical();
//
//        return LayoutBuilder.builder()
//                .setComponents(locFilter)
//                .setPadding(false)
//                .setWidthFull()
//                .buildVertical();
//    }
//
//    private IntegerField createIntegerField(String title) {
//        return NumberFieldBuilder.builder()
//                .setMin(0)
//                .setLabel(title)
//                .setPlaceholder("0")
//                .setWidthFull(true)
//                .setStepButton(true)
//                .buildInt();
//    }
//
//    private FlexLayout createValueRange() {
//
//        BigDecimalField minVal = new BigDecimalField("Valor Mínimo");
//        minVal.setPlaceholder("0,00");
//        minVal.setWidth("100%");
//        minVal.setPrefixComponent(createPrefix());
//
//        BigDecimalField maxVal = new BigDecimalField("Valor Maximo");
//        maxVal.setPlaceholder("0,00");
//        maxVal.setWidth("100%");
//        maxVal.setPrefixComponent(createPrefix());
//
//
//        FlexLayout dateRangeComponent = new FlexLayout(minVal, new Text(" – "), maxVal);
//        dateRangeComponent.setAlignItems(FlexComponent.Alignment.BASELINE);
//        dateRangeComponent.addClassName(LumoUtility.Gap.XSMALL);
//        dateRangeComponent.setWidth("100%");
//
//        return dateRangeComponent;
//    }
//
//    private Div createPrefix() {
//        Div dollarPrefix = new Div();
//        dollarPrefix.setText("R$");
//        return dollarPrefix;
//    }
//
//    private VerticalLayout createPropertySection() {
//
//        TextField textField = new TextField();
//        textField.setPlaceholder("Pesquisa Direta");
//        textField.setTooltipText("Utilizado para buscar imóveis com base no seu endereço ou título");
//
//        Button table = ButtonBuilder.builder()
//                .setType(ComponentType.PRIMARY)
//                .setIcon(LineAwesomeIcon.TH_SOLID.create())
//                .build();
//
//        Button tableLarge = ButtonBuilder.builder()
//                .setType(ComponentType.PRIMARY)
//                .setIcon(LineAwesomeIcon.TH_LARGE_SOLID.create())
//                .build();
//
//        Button tableList = ButtonBuilder.builder()
//                .setType(ComponentType.PRIMARY)
//                .setIcon(LineAwesomeIcon.LIST_SOLID.create())
//                .build();
//
//        HorizontalLayout tools = LayoutBuilder.builder()
//                .setWidthFull()
//                .setJustify(JustifyContentMode.END)
//                .setComponents(textField, table, tableLarge, tableList)
//                .buildHorizontal();
//
//        propertiesList = LayoutBuilder.builder()
//                .setClassName("prop-grid")
//                .setHeightFull()
//                .setWidthFull()
//                .buildVertical();
//
//        HorizontalLayout footer = LayoutBuilder.builder()
//                .setComponents(new Button())
//                .buildHorizontal();
//
//
//        return LayoutBuilder.builder()
//                .setComponents(tools, propertiesList, footer)
//                .buildVertical();
//    }
//
//    private VerticalLayout createCard(PropertyResultDto item) {
//        Image img = new Image("https://images.unsplash.com/photo-1513147122760-ad1d5bf68cdb?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=400&q=80", "Image");
//        img.setWidthFull();
//
//        H3 title = new H3(item.getTitle());
//        H6 descriptionn = new H6(StringUtils.abbreviate(item.getDescription(), 150));
//
//        HorizontalLayout bedroom = createInfo(LineAwesomeIcon.BED_SOLID, item.getNumBedroom(), "Quartos");
//        HorizontalLayout bathroom = createInfo(LineAwesomeIcon.TOILET_SOLID, item.getNumBathroom(), "Banheiros");
//        HorizontalLayout suite = createInfo(LineAwesomeIcon.BATH_SOLID, item.getNumSuite(), "Suítes");
//        HorizontalLayout parking = createInfo(LineAwesomeIcon.WAREHOUSE_SOLID, item.getParkingSpaces(), "Vagas");
//
//        HorizontalLayout info = LayoutBuilder.builder()
//                .setWidthFull()
//                .setComponents(bedroom, bathroom, suite, parking)
//                .setAlignment(Alignment.CENTER, bedroom, bathroom, suite, parking)
//                .buildHorizontal();
//
//        info.getStyle().set("font-size", "17px");
//
//        return LayoutBuilder.builder()
//                .setWidthFull()
//                .setComponents(img, title, descriptionn, info)
//                .setAlignment(Alignment.CENTER, img)
//                .setClassName("prop-card")
//                .buildVertical();
//    }
//
//    private HorizontalLayout createInfo(LineAwesomeIcon icon, Integer val, String toolText) {
//        Component s = icon.create();
//        Text value = new Text(val.toString());
//
//        Tooltip.forComponent(s)
//                .withText(toolText)
//                .withPosition(Tooltip.TooltipPosition.TOP);
//
//        return LayoutBuilder.builder()
//                .setAlignItem(Alignment.CENTER)
//                .setComponents(s, value)
//                .buildHorizontal();
//    }
//
//    @Override
//    public void beforeEnter(BeforeEnterEvent event) {
//
//        String path = event.getRouteParameters().get("rs").orElse(null);
//        String goal = event.getLocation().getQueryParameters().getParameters("goal").getFirst();
//        if(Objects.isNull(path)) {
//            // TODO Redirect to blank page
//        } else {
//            LoginDto login = new LoginDto();
//            login.setRealEstateKey(path);
//            properties =  propertyClient.searchProperties(login);
//            properties.forEach(item -> propertiesList.add(createCard(item)));
//        }
//    }
//
//}
