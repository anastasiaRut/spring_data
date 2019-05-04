package com.it.app.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentResponseDto {
    private Long id;
    private String name;
    private Long roleId;
    private String phoneNumber;

}
