package com.paypal.bfs.test.employeeserv.api.models;

import com.fasterxml.jackson.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"line1", "line2", "city", "state", "country", "zip_code"})
@Embeddable
public class Address implements Serializable {
    @JsonProperty("line1")
    @NotEmpty(message = "address line1 should not be empty")
    private String line1;
    @JsonProperty("line2")
    private String line2;
    @NotEmpty(message = "city should not be empty")
    @JsonProperty("city")
    private String city;
    @NotEmpty(message = "state should not be empty")
    @JsonProperty("state")
    private String state;
    @NotEmpty(message = "country should not be empty")
    @JsonProperty("country")
    private String country;
    @NotEmpty(message = "zip code should not be empty")
    @JsonProperty("zip_code")
    private String zipCode;

    public Address() {
    }

    @JsonProperty("line1")
    public String getLine1() {
        return this.line1;
    }

    @JsonProperty("line1")
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    @JsonProperty("line2")
    public String getLine2() {
        return this.line2;
    }

    @JsonProperty("line2")
    public void setLine2(String line2) {
        this.line2 = line2;
    }

    @JsonProperty("city")
    public String getCity() {
        return this.city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("state")
    public String getState() {
        return this.state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("country")
    public String getCountry() {
        return this.country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("zip_code")
    public String getZipCode() {
        return this.zipCode;
    }

    @JsonProperty("zip_code")
    public void setZipCode(String zipCode) throws Exception {
        if (zipCode.length() != 6)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "zip code must be of 6 characters");
        for (char c : zipCode.toCharArray()) {
            if (!Character.isDigit(c))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "zip code should only have digits");
        }
        this.zipCode = zipCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(com.paypal.bfs.test.employeeserv.api.model.Address.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("line1");
        sb.append('=');
        sb.append(this.line1 == null ? "<null>" : this.line1);
        sb.append(',');
        sb.append("line2");
        sb.append('=');
        sb.append(this.line2 == null ? "<null>" : this.line2);
        sb.append(',');
        sb.append("city");
        sb.append('=');
        sb.append(this.city == null ? "<null>" : this.city);
        sb.append(',');
        sb.append("state");
        sb.append('=');
        sb.append(this.state == null ? "<null>" : this.state);
        sb.append(',');
        sb.append("country");
        sb.append('=');
        sb.append(this.country == null ? "<null>" : this.country);
        sb.append(',');
        sb.append("zipCode");
        sb.append('=');
        sb.append(this.zipCode == null ? "<null>" : this.zipCode);
        sb.append(',');
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.setCharAt(sb.length() - 1, ']');
        } else {
            sb.append(']');
        }

        return sb.toString();
    }
}

