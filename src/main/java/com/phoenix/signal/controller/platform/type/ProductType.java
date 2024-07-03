package com.phoenix.signal.controller.platform.type;

public enum ProductType {
    SIGNAL_MACHINE("信号机");

    private String description;

    ProductType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
