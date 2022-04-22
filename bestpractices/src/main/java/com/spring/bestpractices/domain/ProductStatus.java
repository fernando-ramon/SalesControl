package com.spring.bestpractices.domain;

public enum ProductStatus {
    ACTIVE(1),
    INACTIVE(0);

    private Integer value;

    ProductStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
