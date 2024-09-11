package com.elitehomes.view.components.builder;

import com.vaadin.flow.component.textfield.IntegerField;

import java.util.Objects;

public class IntegerFieldBuilder extends AbstractFormBuild<IntegerFieldBuilder> {

    private Integer min;
    private Integer max;
    private Boolean stepButton;

    public IntegerField build() {
        IntegerField field = new IntegerField();
        configureSize(field);
        configureText(field);
        setMinMax(field);
        enableStep(field);
        return field;
    }

    private void enableStep(IntegerField field) {
        if (Objects.nonNull(stepButton)) {
            field.setStepButtonsVisible(stepButton);
        }
    }

    private void setMinMax(IntegerField field) {
        if (Objects.nonNull(min)) {
            field.setMin(min);
        }
        if (Objects.nonNull(max)) {
            field.setMax(max);
        }
    }

    public static IntegerFieldBuilder builder() {
        return new IntegerFieldBuilder();
    }

    @Override
    protected IntegerFieldBuilder self() {
        return this;
    }

    public IntegerFieldBuilder setMin(Integer min) {
        this.min = min;
        return this;
    }

    public IntegerFieldBuilder setMax(Integer max) {
        this.max = max;
        return this;
    }

    public IntegerFieldBuilder setStepButton(Boolean stepButton) {
        this.stepButton = stepButton;
        return this;
    }
}
