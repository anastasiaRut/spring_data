package com.it.app.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class EventResponseDto {
    private Long id;
    private String name;
    private BigDecimal cost;
    private Integer places;
    private Long tutorId;
}
