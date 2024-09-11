package com.elitehomes.view.home;

import com.elitehomes.view.base.MainLayout;
import com.elitehomes.view.components.builder.LayoutBuilder;
import com.elitehomes.view.entity.ref.PropertyGoal;
import com.elitehomes.view.search.SearchView;
import com.flowingcode.vaadin.addons.carousel.Carousel;
import com.flowingcode.vaadin.addons.carousel.Slide;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
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

		Slide s1 = new Slide(createSlideContent("Slide 1","green"));
		Slide s2 = new Slide(createSlideContent("Slide 2","blue"));
		Slide s3 = new Slide(createSlideContent("Slide 3","red"));
		Slide s4 = new Slide(createSlideContent("Slide 4","yellow"));

		Carousel c = new Carousel(s1,s2,s3,s4);
		c.setWidth("100%");
		c.setHeight("180px");
		c.addChangeListener(e -> Notification.show("changed!", 1000, Notification.Position.BOTTOM_END));

		wrapper.add(c);
		return wrapper;
	}

	private HorizontalLayout createSlideContent(String s, String green) {
		Image img = new Image("https://images.unsplash.com/photo-1513147122760-ad1d5bf68cdb?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1920&q=80", s);
		return LayoutBuilder.builder().setBackground(green).setComponents(img).buildHorizontal();
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
