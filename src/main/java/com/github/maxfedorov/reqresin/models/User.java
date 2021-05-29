package com.github.maxfedorov.reqresin.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @JsonProperty("data")
    private UserData data;
    @JsonProperty("support")
    private Support support;

    @JsonProperty("data")
    public UserData getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(UserData data) {
        this.data = data;
    }

    @JsonProperty("support")
    public Support getSupport() {
        return support;
    }

    @JsonProperty("support")
    public void setSupport(Support support) {
        this.support = support;
    }
}