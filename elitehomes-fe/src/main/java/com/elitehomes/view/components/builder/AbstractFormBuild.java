package com.elitehomes.view.components.builder;

import com.elitehomes.view.components.ref.StyleConstants;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.textfield.TextFieldBase;

import java.util.Objects;

@SuppressWarnings({"rawtypes"})
public abstract class AbstractFormBuild<B> {

    private Boolean widthFull;
    private String width;
    private String height;
    private Boolean heigthFull;
    private String label;
    private String placeholder;

    protected abstract B self();

    protected void configureText(TextFieldBase component) {
        if (Objects.nonNull(label)) {
            component.setLabel(label);
        }

        if (Objects.nonNull(placeholder)) {
            component.setPlaceholder(placeholder);
        }
    }

    protected void configureSize(HasSize component) {
        setWidthLayout(component);
        setHeightLayout(component);
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

    public B setWidthFull(Boolean widthFull) {
        this.widthFull = widthFull;
        return self();
    }

    public B setWidth(String width) {
        this.width = width;
        return self();
    }

    public B setHeight(String height) {
        this.height = height;
        return self();
    }

    public B setHeigthFull(Boolean heigthFull) {
        this.heigthFull = heigthFull;
        return self();
    }

    public B setLabel(String label) {
        this.label = label;
        return self();
    }

    public B setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        return self();
    }
}
