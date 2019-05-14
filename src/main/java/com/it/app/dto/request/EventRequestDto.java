package com.it.app.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

/**
 * Request Data Transfer Object class for Event entity
 */
@Getter
@Setter
public class EventRequestDto {

    private Long id;

    @NotNull(message = "{event.name.notNull}")
    @NotEmpty(message = "{event.name.notEmpty}")
    private String name;

    @NotNull(message = "{event.cost.notNull}")
    @Positive(message = "{event.places.positive}")
    private BigDecimal cost;

    @NotNull(message = "{event.places.notNull}")
    @PositiveOrZero(message = "{event.places.positiveOrZero}")
    private Integer places;

    @NotNull(message = "{event.tutor.notNull}")
    private Long tutorId;
}
