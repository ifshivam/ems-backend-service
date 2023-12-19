package com.emp.mgmt.constants;

public enum Role {

    ADMIN("ROLE_ADMIN"),
    EMPLOYEE("ROLE_EMPLOYEE");

    private String key;

    Role(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
