package io.fourfinanceit.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class LoanApplicationDto {

    @NotBlank
    private String code;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @Positive
    private Double amount;
    @Positive
    private Integer period;

    public LoanApplicationDto() {
    }

    public LoanApplicationDto(String code, String name, String lastName, Double amount, Integer period) {
        this.code = code;
        this.name = name;
        this.lastName = lastName;
        this.amount = amount;
        this.period = period;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}
