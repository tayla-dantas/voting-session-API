package com.votingsessionAPI.votingsession.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Vote {

    @JsonProperty("yes")
    YES("yes"),
    @JsonProperty("no")
    NO("no");

    private final String description;

    Vote(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
