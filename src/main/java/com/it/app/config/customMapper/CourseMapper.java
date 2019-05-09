package com.it.app.config.customMapper;

import com.it.app.dto.request.CourseRequestDto;
import com.it.app.dto.response.CourseResponseDto;
import com.it.app.model.Course;
import lombok.Getter;
import org.dozer.DozerConverter;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;

import java.time.LocalDate;

public class CourseMapper {
    @Getter
    private BeanMappingBuilder courseMappingRequestDto = new BeanMappingBuilder() {
        @Override
        protected void configure() {
            mapping(CourseRequestDto.class, Course.class).fields("startDate", "startDate", FieldsMappingOptions.customConverter(CourseConverterImpl.class));

        }
    };

    @Getter
    private BeanMappingBuilder courseMappingResponseDto = new BeanMappingBuilder() {
        @Override
        protected void configure() {
            mapping(CourseResponseDto.class, Course.class).fields("startDate", "startDate", FieldsMappingOptions.customConverter(CourseConverterImpl.class));

        }
    };

    public static class CourseConverterImpl extends DozerConverter<LocalDate, String> {

        public CourseConverterImpl() {
            super(LocalDate.class, String.class);
        }

        @Override
        public String convertTo(LocalDate source, String destination) {
            return String.valueOf(source);
        }

        @Override
        public LocalDate convertFrom(String source, LocalDate destination) {
            return LocalDate.parse(source);
        }
    }
}