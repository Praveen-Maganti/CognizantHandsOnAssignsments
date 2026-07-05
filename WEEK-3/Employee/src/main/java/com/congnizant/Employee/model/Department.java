package com.congnizant.Employee.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @NotBlank(message = "Department name cannot be blank")
    @Size(min = 1, max = 30, message = "Department name should be between 1 and 30 characters")
    private String name;

    @NotNull(message = "Department id cannot be null")
    private String id;
}
