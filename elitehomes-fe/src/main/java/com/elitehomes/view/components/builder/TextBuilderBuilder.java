package com.elitehomes.view.components.builder;

import com.vaadin.flow.component.textfield.TextField;

public class TextBuilderBuilder extends AbstractFormBuild<TextBuilderBuilder> {

    public TextField build() {
        return null;
    }

    public static TextBuilderBuilder builder() {
        return new TextBuilderBuilder();
    }

    @Override
    protected TextBuilderBuilder self() {
        return this;
    }
}
