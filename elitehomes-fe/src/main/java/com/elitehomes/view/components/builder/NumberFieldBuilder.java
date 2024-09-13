package com.elitehomes.view.components.builder;

import com.vaadin.flow.component.textfield.AbstractNumberField;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;

import java.util.Objects;

public class NumberFieldBuilder extends AbstractFormBuild<NumberFieldBuilder> {

    private Integer min;
    private Integer max;
    private Boolean stepButton;

    public IntegerField buildInt() {
        IntegerField field = new IntegerField();
        configureSize(field);
        configureText(field);
        setMinMax(field);
        enableStep(field);
        return field;
    }

    public NumberField buildNum() {
        NumberField field = new NumberField();
        configureSize(field);
        configureText(field);
        setMinMax(field);
        enableStep(field);
        return field;
    }

    public BigDecimalField buildBd() {
        BigDecimalField field = new BigDecimalField();
        configureSize(field);
        configureText(field);
        return field;
    }

    private void enableStep(AbstractNumberField<?, ?> field) {
        if (Objects.nonNull(stepButton)) {
            field.setStepButtonsVisible(stepButton);
        }
    }

    private void setMinMax(NumberField field) {
        if (Objects.nonNull(min)) {
            field.setMin(min);
        }
        if (Objects.nonNull(max)) {
            field.setMax(max);
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

    public static NumberFieldBuilder builder() {
        return new NumberFieldBuilder();
    }

    @Override
    protected NumberFieldBuilder self() {
        return this;
    }

    public NumberFieldBuilder setMin(Integer min) {
        this.min = min;
        return this;
    }

    public NumberFieldBuilder setMax(Integer max) {
        this.max = max;
        return this;
    }

    public NumberFieldBuilder setStepButton(Boolean stepButton) {
        this.stepButton = stepButton;
        return this;
    }
}
