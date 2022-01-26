package com.votingsessionAPI.votingsession.domain.enums;

public enum Vote {
    YES("yes"),
    NO("no");

    private final String description;

    Vote(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
