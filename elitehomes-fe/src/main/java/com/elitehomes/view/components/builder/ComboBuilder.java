package com.elitehomes.view.components.builder;

import com.elitehomes.view.components.ref.ComponentType;
import com.elitehomes.view.components.ref.StyleConstants;
import com.elitehomes.view.entity.ref.PropertyGoal;
import com.vaadin.flow.component.HasLabel;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ComboBuilder {


    private Class<?> clezz;

    private String title;
    private ComponentType type;
    private Boolean widthFull;
    private String width;
    private String height;
    private Boolean heigthFull;
    private ItemLabelGenerator labelGen;
    private List<?> items;

    public static ComboBuilder builder(Class<?> clz) {
        return new ComboBuilder(clz);
    }

    public ComboBuilder(Class<?> clazz) {
        this.clezz = clazz;
    }


    public <clezz> ComboBox<clezz> build() {
        ComboBox<clezz> combo = new ComboBox<>();
        configureSize(combo);
        setItemLabel(combo);
        setVariant(combo);
        combo.setItemLabelGenerator(labelGen);
        combo.setItems((Collection<clezz>) items);
        return combo;
    }

    public <clezz> MultiSelectComboBox<clezz> buildMulti() {
        MultiSelectComboBox<clezz> combo = new MultiSelectComboBox<>();
        configureSize(combo);
        setItemLabel(combo);
        setVariant(combo);
        combo.setItemLabelGenerator(labelGen);
        combo.setItems((Collection<clezz>) items);
        return combo;
    }

    private void configureSize(HasSize component) {
        setWidthLayout(component);
        setHeightLayout(component);
    }

    private void setVariant(HasStyle cb) {
        if(Objects.nonNull(type)) {
            switch (type) {
                case PRIMARY : setPrimary(cb); break;
                default: break;

            }

        }
    }

    private void setPrimary(HasStyle button) {
        button.setClassName(StyleConstants.DEFAULT_FORM);
    }

    private void setItemLabel(HasLabel component) {
        if(Objects.nonNull(title)) {
            component.setLabel(title);
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

    public ComboBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ComboBuilder setType(ComponentType type) {
        this.type = type;
        return this;
    }

    public ComboBuilder setWidthFull(Boolean widthFull) {
        this.widthFull = widthFull;
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


    public ComboBuilder setHeigthFull(Boolean heigthFull) {
        this.heigthFull = heigthFull;
        return this;
    }

    public ComboBuilder setItemLabel(ItemLabelGenerator labelGen) {
        this.labelGen = labelGen;
        return this;
    }

    public ComboBuilder setItems(List<?> item) {
        this.items = item;
        return this;
    }


}
