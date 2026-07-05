package com.congnizant.Employee.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    @NotNull(message = "Skill id cannot be null")
    private int id;

    @NotBlank(message = "Skill name cannot be blank")
    @Size(min = 1, max = 30, message = "Skill name should be between 1 and 30 characters")
    private String name;
}
