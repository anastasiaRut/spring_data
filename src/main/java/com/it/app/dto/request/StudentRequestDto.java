package com.it.app.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class StudentRequestDto {
    private Long id;

    @NotNull(message = "{student.name.notNull}")
    @NotEmpty(message = "{student.name.notEmpty}")
    @Size(min = 3, max = 50, message = "{student.name.size}")
    private String name;

    @NotNull(message = "{student.role.notNull}")
    private Long roleId;

    @Pattern(regexp = "^[+]375(29|33|25|44)[0-9]{7}$", message = "{student.phoneNumber.valid}")
    private String phoneNumber;


}
