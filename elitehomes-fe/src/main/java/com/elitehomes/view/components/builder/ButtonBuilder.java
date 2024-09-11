package com.elitehomes.view.components.builder;

import com.elitehomes.view.components.ref.ComponentType;
import com.elitehomes.view.components.ref.StyleConstants;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.router.RouteParam;

import java.util.Objects;

public class ButtonBuilder {

    private Class<? extends Component> navigate;
    private String routeParam;
    private String text;
    private ComponentType type;
    private Component icon;
    private Boolean widthFull;
    private String width;
    private String height;
    private Boolean heigthFull;

    public ButtonBuilder() {}

    public static ButtonBuilder builder() {
        return new ButtonBuilder();
    }

    public Button build() {
        Button button = new Button();
        return buildButton(button);
    }

    public Button build(Button button) {
        return buildButton(button);
    }

    private Button buildButton(Button button) {
        setNavigation(button);
        setButtonText(button);
        setButtonVariant(button);
        setButtonIcon(button);
        configureSize(button);
        return button;
    }

	private void setNavigation(Button button) {
		button.addClickListener(event -> {
			if (Objects.nonNull(navigate)) {
				if (Objects.nonNull(routeParam)) {
					UI.getCurrent().navigate(navigate, new RouteParam("id", routeParam));
				} else {
					UI.getCurrent().navigate(navigate);
				}
			}
		});
    }

    private void setButtonText(Button button) {
        if(Objects.nonNull(text)) {
            button.setText(text);
        }
    }

    private void setButtonIcon(Button button) {
        if(Objects.nonNull(icon)) {
            button.setIcon(icon);
        }
    }

    private void configureSize(HasSize component) {
        setWidthLayout(component);
        setHeightLayout(component);
    }

    private void setButtonVariant(Button button) {
        if(Objects.nonNull(type)) {
            switch (type) {
				case PRIMARY : setPrimary(button); break;
                case TERTIARY: setTertiary(button); break;
                case SECONDARY: setSecondary(button); break;
                case ERROR: setError(button); break;
            }

        }
    }

    private void setError(Button button) {
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
    }

    private void setTertiary(Button button) {
		button.getStyle()
                .set("background-color", StyleConstants.TRANSPARENT_COLOR)
                .set("color", StyleConstants.PRIMARY_COLOR);
	}

	private void setSecondary(Button button) {
		button.getStyle()
                .set("background-color", StyleConstants.TRANSPARENT_COLOR)
                .set("border", "2px solid " + StyleConstants.PRIMARY_COLOR);
	}

	private void setPrimary(Button button) {
		button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.getStyle()
                .set("color", "white");
	}

    private void setHeightLayout(HasSize component) {
        if(Objects.nonNull(heigthFull) && heigthFull) {
            component.setHeight(StyleConstants.WIDTH_FULL);
        } else if(Objects.nonNull(height)) {
            component.setHeight(height);
        }
    }

    private void setWidthLayout(HasSize component) {
        if(Objects.nonNull(widthFull) && widthFull) {
            component.setWidth(StyleConstants.WIDTH_FULL);
        } else if(Objects.nonNull(width)) {
            component.setWidth(width);
        }
    }

    public ButtonBuilder setType(ComponentType variant) {
        this.type = variant;
        return this;
    }

    public ButtonBuilder setIcon(Component icon) {
        this.icon = icon;
        return this;
    }

    public ButtonBuilder setIcon(Component icon, String color) {
        icon.getStyle().set("color", color);
        this.icon = icon;
        return this;
    }

    public ButtonBuilder setWidthFull(Boolean widthFull) {
        this.widthFull = widthFull;
        return this;
    }

    public ButtonBuilder setHeigthFull(Boolean heigthFull) {
        this.heigthFull = heigthFull;
        return this;
    }

    public ButtonBuilder setWidth(String width) {
        this.width = width;
        return this;
    }

    public ButtonBuilder setHeight(String height) {
        this.height = height;
        return this;
    }

    public ButtonBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public ButtonBuilder setNavigate(Class<? extends Component> navigate, String routeParam) {
        this.navigate = navigate;
        this.routeParam = routeParam;
        return this;
    }

    public ButtonBuilder setNavigate(Class<? extends Component> navigate) {
        this.navigate = navigate;
        return this;
    }

}
