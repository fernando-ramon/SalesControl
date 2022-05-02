package com.softwarehouse.salescontrol.customer.domain;

public enum Gender {
    MALE('M'),
    FEMALE('F'),
    UNDEFINED('U');

    private Character gender;

    Gender(Character gender) {
        this.gender = gender;
    }

    public Character getValue() {
        return gender;
    }
}
