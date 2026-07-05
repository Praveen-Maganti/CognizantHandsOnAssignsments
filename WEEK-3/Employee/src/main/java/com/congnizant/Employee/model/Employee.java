package com.congnizant.Employee.model;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @NotNull(message = "Employee id cannot be null")
    private int id;

    @NotBlank(message = "Employee name cannot be blank")
    @Size(min = 1, max = 30, message = "Employee name should be between 1 and 30 characters")
    private String name;

    @NotNull(message = "Salary cannot be null")
    @PositiveOrZero(message = "Salary should be zero or above")
    private double salary;

    @NotNull(message = "Permanent cannot be null")
    private boolean permanent;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @Valid
    private Department department;

    @Valid
    private List<Skill> skillList;
}
