//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.paypal.bfs.test.employeeserv.api.models;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="employee")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"id", "first_name", "last_name", "date_of_birth", "address"})
public class Employee implements Serializable {
    @JsonProperty("id")
    @JsonPropertyDescription("employee id")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @JsonProperty("first_name")
    @JsonPropertyDescription("first name")
    @NotEmpty(message = "first name should not be empty")
    @Column(name="first_name")
    private String firstName;
    @JsonProperty("last_name")
    @JsonPropertyDescription("last name")
    @NotEmpty(message = "last name should not be empty")
    @Column(name = "last_name")
    private String lastName;
    @JsonProperty("date_of_birth")
    @NotNull
    @Column(name = "date_of_birth")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Valid
    private LocalDate dateOfBirth;
    @JsonProperty("address")
    @JsonPropertyDescription("address")
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "line1", column = @Column(name = "address_line1")),
            @AttributeOverride( name = "line2", column = @Column(name = "address_line2")),
            @AttributeOverride( name = "city", column = @Column(name = "city")),
            @AttributeOverride( name = "state", column = @Column(name = "state")),
            @AttributeOverride( name = "country", column = @Column(name = "country")),
            @AttributeOverride( name = "zipCode", column = @Column(name = "zip_code"))
    })
    private Address address;

    public Employee() {
    }

    @JsonProperty("id")
    public Integer getId() {
        return this.id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return this.firstName;
    }

    @JsonProperty("first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return this.lastName;
    }

    @JsonProperty("last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("date_of_birth")
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonProperty("date_of_birth")
    public void setDateOfBirth(String dateOfBirth) throws ParseException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(dateOfBirth, formatter);
            if(date.getYear() >= (Calendar.getInstance().get(Calendar.YEAR) - 18))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee must be of more than or equal to 18 years");
            else this.dateOfBirth = LocalDate.parse(dateOfBirth, formatter);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @JsonProperty("address")
    public Address getAddress() {
        return this.address;
    }

    @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Employee.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(this.id == null ? "<null>" : this.id);
        sb.append(',');
        sb.append("firstName");
        sb.append('=');
        sb.append(this.firstName == null ? "<null>" : this.firstName);
        sb.append(',');
        sb.append("lastName");
        sb.append('=');
        sb.append(this.lastName == null ? "<null>" : this.lastName);
        sb.append(',');
        sb.append("address");
        sb.append('=');
        sb.append(this.address == null ? "<null>" : this.address);
        sb.append(',');
        sb.append("dateOfBirth");
        sb.append('=');
        sb.append(this.dateOfBirth == null ? "<null>" : this.dateOfBirth);
        sb.append(',');
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.setCharAt(sb.length() - 1, ']');
        } else {
            sb.append(']');
        }

        return sb.toString();
    }


}
