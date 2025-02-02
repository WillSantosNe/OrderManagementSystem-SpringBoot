package com.githubProjects.demo.entities;

public enum OrderStatus {
    PENDING(0),
    CONFIRMED(1),
    SHIPPED(2),
    DELIVERED(3),
    CANCELED(4);

    private int code;

    OrderStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static OrderStatus valueOf(int code) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code: " + code);
    }
}
