package com.elitehomes.view.components.builder;

import com.elitehomes.view.components.ref.StyleConstants;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.ThemableLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Arrays;
import java.util.Objects;

public class LayoutBuilder {

    private JustifyContentMode justify;
    private Alignment alignment;
    private Alignment alignItem;
    private Boolean flexGrow;
    private String width;
    private Boolean widthFull;
    private String height;
    private Boolean heigthFull;
    private String background;
    private Boolean padding;
    private Boolean spacing;
    private String[] className;
    private Component[] components;
    private HasElement[] toAlign;

    public LayoutBuilder() {}

    public static LayoutBuilder builder() {
        return new LayoutBuilder();
    }

    public VerticalLayout buildVertical() {
        VerticalLayout layout = new VerticalLayout();
        configureComponent(layout);
        configureSize(layout);
        configureFlex(layout);
        configureThemableLayout(layout);
        setComponentsLayout(layout);
        return layout;
    }

    public HorizontalLayout buildHorizontal() {
        HorizontalLayout layout = new HorizontalLayout();
        configureComponent(layout);
        configureSize(layout);
        configureFlex(layout);
        configureThemableLayout(layout);
        setComponentsLayout(layout);
        return layout;
    }

    private void configureFlex(FlexComponent layout) {
        setJustifyLayout(layout);
        setAlignSelfLayout(layout);
        setAlignItemsLayout(layout);

    }



    private void configureSize(HasSize component) {
        setWidthLayout(component);
        setHeightLayout(component);
    }

    private void configureComponent(Component component) {
        setBackgroundLayout(component);
        setFlexGrowLayout(component);
        setClassNameLayout(component);
    }

    private void configureThemableLayout(ThemableLayout layout) {
        setLayoutPadding(layout);
        setLayoutSpacing(layout);
    }

    private void setLayoutPadding(ThemableLayout layout) {
        if(Objects.nonNull(padding)) {
            layout.setPadding(padding);
        }
    }

    private void setLayoutSpacing(ThemableLayout layout) {
        if(Objects.nonNull(spacing)) {
            layout.setSpacing(spacing);
        }
    }

    private void setFlexGrowLayout(Component comp) {
        if(Objects.nonNull(flexGrow) && flexGrow) {
            comp.getStyle().set("flex-grow", "1");
        }
    }

    private void setAlignSelfLayout(FlexComponent layout) {
        if(Objects.nonNull(alignment) && Objects.nonNull(toAlign)) {
            layout.setAlignSelf(alignment, toAlign);
        }
    }

    private void setAlignItemsLayout(FlexComponent layout) {
        if(Objects.nonNull(alignItem)) {
            layout.setAlignItems(alignItem);
        }
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

    private void setBackgroundLayout(Component component) {
        if(Objects.nonNull(background)) {
            component.getStyle().setBackground(background);
        }
    }

    private void setComponentsLayout(HasComponents component) {
        if(Objects.nonNull(components)) {
            Arrays.stream(components).forEach(component::add);
        }
    }

    private void setClassNameLayout(Component component) {
        if (Objects.nonNull(className)) {
            Arrays.stream(className).forEach(component::addClassName);
        }
    }

    private void setJustifyLayout(FlexComponent component) {
        if(Objects.nonNull(justify)) {
            component.setJustifyContentMode(justify);
        }
    }

    public LayoutBuilder setComponents(Component... components) {
        this.components = components;
        return this;

    }

    public LayoutBuilder setBackground(String color) {
        this.background = color;
        return this;
    }

    public LayoutBuilder setWidthFull() {
        this.widthFull = true;
        return this;
    }

    public LayoutBuilder setWidth(String width) {
        this.width = width;
        return this;
    }

    public LayoutBuilder setHeightFull() {
        this.heigthFull = true;
        return this;
    }

    public LayoutBuilder setHeight(String heigth) {
        this.height = heigth;
        return this;
    }

    public LayoutBuilder setFlexGrow() {
        this.flexGrow = true;
        return this;
    }

    public LayoutBuilder setJustify(JustifyContentMode justify) {
        this.justify = justify;
        return this;
    }

    public LayoutBuilder setClassName(String... className) {
        this.className = className;
        return this;
    }

    public LayoutBuilder setAlignment(Alignment alignment, HasElement... components) {
        this.alignment = alignment;
        this.toAlign = components;
        return this;
    }

    public LayoutBuilder setAlignItem(Alignment alignment) {
        this.alignItem = alignment;
        return this;
    }

    public LayoutBuilder setPadding(Boolean padding) {
        this.padding = padding;
        return this;
    }

    public LayoutBuilder setSpacing(Boolean spacing) {
        this.spacing = spacing;
        return this;
    }

}
