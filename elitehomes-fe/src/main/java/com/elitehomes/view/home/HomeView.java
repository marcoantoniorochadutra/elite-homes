package com.elitehomes.view.home;

import com.elitehomes.view.base.MainLayout;
import com.elitehomes.view.components.builder.LayoutBuilder;
import com.elitehomes.view.entity.ref.PropertyGoal;
import com.elitehomes.view.search.SearchView;
import com.infraleap.animatecss.Animated;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouteParameters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PageTitle("Home")
@Route(value = "home", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
@CssImport(value = "./styles/styles.css")
public class HomeView extends Composite<VerticalLayout> implements AfterNavigationObserver {

	private ComboBox<String> ten;
	private ComboBox<PropertyGoal> goalCombo;

	public HomeView() {
        getContent().setPadding(false);
		getContent().setHeightFull();
		getContent().setWidthFull();

		VerticalLayout searchPart = buildSearchPart();

		VerticalLayout suggestionPart = buildSuggestionPart();

		HorizontalLayout informationPart = buildInfoPart();

		HorizontalLayout contactPart = buildContactPart();

		getContent().add(searchPart, suggestionPart);
	}

	private VerticalLayout buildSearchPart() {

		Button btn = new Button("Buscar");
		btn.addClickListener(event -> {

			Map<String, String> query = new HashMap<>();
			query.put("goal", goalCombo.getValue().name());

			Map<String, String> path = new HashMap<>();
			path.put("rs", ten.getValue());

			UI.getCurrent().navigate(SearchView.class, new RouteParameters(path), QueryParameters.simple(query));
		});
		ten = new ComboBox<>();
		ten.setItems(List.of("elite_homes_root", "eh_cl1", "eh_cliente"));

		goalCombo = new ComboBox<>();
		goalCombo.setItems(PropertyGoal.values());
		goalCombo.setValue(PropertyGoal.RENT);
		goalCombo.setItemLabelGenerator(PropertyGoal::getValue);

		ComboBox<String> cb2 = new ComboBox<>();
		ComboBox<String> cb3 = new ComboBox<>();


		HorizontalLayout filters = LayoutBuilder.builder()
				.setJustify(FlexComponent.JustifyContentMode.CENTER)
				.setComponents(goalCombo, ten, cb2, cb3, btn)
				.buildHorizontal();

		VerticalLayout container = LayoutBuilder.builder()
				.setClassName("image-filter")
				.setComponents(filters)
				.buildVertical();

        return LayoutBuilder.builder()
				.setClassName("image-container")
				.setHeight("36rem")
				.setWidthFull()
				.setSpacing(false)
				.setAlignment(FlexComponent.Alignment.CENTER, container)
				.setJustify(FlexComponent.JustifyContentMode.END)
				.setComponents(container)
				.buildVertical();
	}

	private VerticalLayout buildSuggestionPart() {
		VerticalLayout wrapper = LayoutBuilder.builder().buildVertical();

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setWidthFull();
		horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

		HorizontalLayout sandbox = new HorizontalLayout();
		sandbox.add(new H1("Animate.CSS"));

		horizontalLayout.add(sandbox);
		wrapper.add(horizontalLayout);

		Button nextButton = new Button("Next Animation");
		wrapper.add(nextButton);

		ComboBox<Animated.Animation> comboBox = new ComboBox<>();
		comboBox.setItems(Animated.Animation.values());
		comboBox.setItemLabelGenerator(Enum::name);
		comboBox.setWidth("300px");
		wrapper.add(comboBox);

		ComboBox<Animated.Modifier> modifierComboBox = new ComboBox<>();
		modifierComboBox.setItems(Animated.Modifier.values());
		modifierComboBox.setItemLabelGenerator(Enum::name);
		modifierComboBox.setValue(Animated.Modifier.REPEAT_1);
		modifierComboBox.setWidth("300px");
		wrapper.add(modifierComboBox);

		nextButton.addClickListener( e -> {
			int nextOrdinal = (comboBox.getValue().ordinal() + 1) % Animated.Animation.values().length;
			comboBox.setValue(Animated.Animation.values()[nextOrdinal]);
		});
		comboBox.addValueChangeListener(event -> {
			Animated.animate(sandbox, event.getValue(), modifierComboBox.getValue());
		});
		modifierComboBox.addValueChangeListener (event-> {
			Animated.animate(sandbox, comboBox.getValue(), event.getValue());
		} );

		comboBox.setValue(Animated.Animation.ROLL_IN);
		return wrapper;
	}

	private HorizontalLayout buildInfoPart() {
		return null;
	}

	private HorizontalLayout buildContactPart() {
		return null;
	}


	@Override
	public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {

	}
}
