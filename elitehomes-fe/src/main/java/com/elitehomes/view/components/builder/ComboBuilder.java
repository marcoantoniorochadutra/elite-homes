package com.elitehomes.view.components.builder;

import com.elitehomes.core.entity.base.SelectableDto;
import com.elitehomes.view.components.ref.ComponentType;
import com.elitehomes.view.components.ref.StyleConstants;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxBase;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ComboBuilder {


    private Class<?> clezz;

    private Boolean enabled;
    private String className;
    private String title;
    private String placeholder;
    private ComponentType type;
    private Boolean widthFull;
    private String width;
    private String height;
    private Boolean heigthFull;
    private ItemLabelGenerator labelGen;
    private List<?> items;

    public static ComboBuilder builder() {
        return new ComboBuilder();
    }

    public static ComboBuilder builder(Class<?> clz) {
        return new ComboBuilder(clz);
    }

    public ComboBuilder() {
    }

    public ComboBuilder(Class<?> clazz) {
        this.clezz = clazz;
    }

    public <clezz> ComboBox<clezz> build() {
        ComboBox<clezz> combo = new ComboBox<>();
        configureCombo(combo);

        if (Objects.nonNull(items)) {
            combo.setItems((Collection<clezz>) items);
        }
        if (Objects.nonNull(labelGen)) {
            combo.setItemLabelGenerator(labelGen);
        }
        return combo;
    }

    public <clezz> MultiSelectComboBox<clezz> buildMulti() {
        MultiSelectComboBox<clezz> combo = new MultiSelectComboBox<>();
        configureCombo(combo);

        if (Objects.nonNull(items)) {
            combo.setItems((Collection<clezz>) items);
        }
        if (Objects.nonNull(labelGen)) {
            combo.setItemLabelGenerator(labelGen);
        }
        return combo;
    }

    public ComboBox<SelectableDto> buildSelectable() {
        ComboBox<SelectableDto> combo = new ComboBox<>();
        configureCombo(combo);
        combo.setItemLabelGenerator(SelectableDto::getValue);
        if (Objects.nonNull(items)) {
            combo.setItems((Collection<SelectableDto>) items);
        }
        return combo;
    }

    public MultiSelectComboBox<SelectableDto> buildMultiSelectable() {
        MultiSelectComboBox<SelectableDto> combo = new MultiSelectComboBox<>();
        configureCombo(combo);
        combo.setItemLabelGenerator(SelectableDto::getValue);
        if (Objects.nonNull(items)) {
            combo.setItems((Collection<SelectableDto>) items);
        }
        return combo;
    }

    private void configureSize(HasSize component) {
        setWidthLayout(component);
        setHeightLayout(component);
    }

    private void configureCombo(ComboBoxBase component) {
        configureSize(component);
        configureVariant(component);

        if (Objects.nonNull(enabled)) {
            component.setEnabled(enabled);
        }

        if (Objects.nonNull(className)) {
            component.setClassName(className);
        }

        if (Objects.nonNull(title)) {
            component.setLabel(title);
        }

        if (Objects.nonNull(placeholder)) {
            component.setPlaceholder(placeholder);
        }
    }

    private void configureVariant(HasStyle cb) {
        if (Objects.nonNull(type)) {
            switch (type) {
                case PRIMARY:
                    setPrimary(cb);
                    break;
                default:
                    break;
            }
        }
    }

    private void setPrimary(HasStyle button) {
        button.setClassName(StyleConstants.DEFAULT_FORM);
    }

    private void setHeightLayout(HasSize component) {
        if (Objects.nonNull(heigthFull) && heigthFull) {
            component.setHeight(StyleConstants.WIDTH_FULL);
        } else if (Objects.nonNull(height)) {
            component.setHeight(height);
        }
    }

    private void setWidthLayout(HasSize component) {
        if (Objects.nonNull(widthFull) && widthFull) {
            component.setWidth(StyleConstants.WIDTH_FULL);
        } else if (Objects.nonNull(width)) {
            component.setWidth(width);
        }
    }

    public ComboBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ComboBuilder setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public ComboBuilder setType(ComponentType type) {
        this.type = type;
        return this;
    }

    public ComboBuilder setWidthFull() {
        this.widthFull = true;
        return this;
    }

    public ComboBuilder setWidth(String width) {
        this.width = width;
        return this;
    }

    public ComboBuilder setHeight(String height) {
        this.height = height;
        return this;
    }


    public ComboBuilder setHeigthFull() {
        this.heigthFull = true;
        return this;
    }

    public ComboBuilder configureCombo(ItemLabelGenerator labelGen) {
        this.labelGen = labelGen;
        return this;
    }

    public ComboBuilder setItems(List<?> item) {
        this.items = item;
        return this;
    }

    public ComboBuilder setClassName(String className) {
        this.className = className;
        return this;
    }

    public ComboBuilder setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
