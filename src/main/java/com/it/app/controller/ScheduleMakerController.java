package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.request.ScheduleMakerRequestDto;
import com.it.app.dto.response.ScheduleMakerResponseDto;
import com.it.app.model.Language;
import com.it.app.model.Role;
import com.it.app.model.ScheduleMaker;
import com.it.app.service.ScheduleMakerService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The class represents a REST Controller for ScheduleMaker entity
 *
 * @author A. Rutkouskaya
 * @see ScheduleMaker
 */
@RestController
@RequestMapping("/scheduleMakers")
public class ScheduleMakerController {

    private final ScheduleMakerService scheduleMakerService;

    private final Mapper mapper;

    private final LocalizedMessageSource localizedMessageSource;

    public ScheduleMakerController(ScheduleMakerService scheduleMakerService, Mapper mapper, LocalizedMessageSource localizedMessageSource) {
        this.scheduleMakerService = scheduleMakerService;
        this.mapper = mapper;
        this.localizedMessageSource = localizedMessageSource;
    }

    /**
     * Gets all ScheduleMakers
     *
     * @return ResponseEntity<List < ScheduleMakerResponseDto>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ScheduleMakerResponseDto>> getAll() {
        final List<ScheduleMaker> scheduleMakers = scheduleMakerService.findAll();
        final List<ScheduleMakerResponseDto> scheduleMakerDtoList = new ArrayList<>();
        scheduleMakers.stream()
                .forEach((ScheduleMaker) -> scheduleMakerDtoList.add(getScheduleMakerDto(ScheduleMaker)));

        return new ResponseEntity<>(scheduleMakerDtoList, HttpStatus.OK);
    }

    /**
     * Gets one ScheduleMaker
     *
     * @param id -id
     * @return ResponseEntity<ScheduleMakerResponseDto>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ScheduleMakerResponseDto> getOne(@PathVariable Long id) {
        final ScheduleMakerResponseDto scheduleMakerResponseDto = getScheduleMakerDto(scheduleMakerService.findById(id));
        return new ResponseEntity<>(scheduleMakerResponseDto, HttpStatus.OK);
    }

    /**
     * Saves ScheduleMaker
     *
     * @param scheduleMakerRequestDto - scheduleMakerRequestDto
     * @return ResponseEntity<ScheduleMakerResponseDto>
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ScheduleMakerResponseDto> save(@Valid @RequestBody ScheduleMakerRequestDto scheduleMakerRequestDto) {
        scheduleMakerRequestDto.setId(null);
        final ScheduleMakerResponseDto scheduleMakerResponseDto = getScheduleMakerDto(scheduleMakerService.save(getScheduleMaker(scheduleMakerRequestDto)));
        return new ResponseEntity<>(scheduleMakerResponseDto, HttpStatus.OK);
    }

    /**
     * Updates ScheduleMaker
     *
     * @param scheduleMakerRequestDto - scheduleMakerRequestDto
     * @return ResponseEntity<ScheduleMakerResponseDto>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ScheduleMakerResponseDto> update(@Valid @RequestBody ScheduleMakerRequestDto scheduleMakerRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, scheduleMakerRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.scheduleMaker.unexpectedId", new Object[]{}));
        }
        final ScheduleMakerResponseDto scheduleMakerResponseDto = getScheduleMakerDto(scheduleMakerService.update(getScheduleMaker(scheduleMakerRequestDto)));
        return new ResponseEntity<>(scheduleMakerResponseDto, HttpStatus.OK);
    }

    /**
     * Deletes ScheduleMaker
     *
     * @param id - id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        scheduleMakerService.deleteById(id);
    }

    private ScheduleMaker getScheduleMaker(ScheduleMakerRequestDto scheduleMakerRequestDto) {
        final ScheduleMaker scheduleMaker = mapper.map(scheduleMakerRequestDto, ScheduleMaker.class);
        final Language language = new Language();
        language.setId(scheduleMakerRequestDto.getLanguageId());
        scheduleMaker.setLanguage(language);
        final Role role = new Role();
        role.setId(scheduleMakerRequestDto.getRoleId());
        scheduleMaker.setRole(role);

        return scheduleMaker;
    }

    private ScheduleMakerResponseDto getScheduleMakerDto(ScheduleMaker scheduleMaker) {
        final ScheduleMakerResponseDto scheduleMakerResponseDto = mapper.map(scheduleMaker, ScheduleMakerResponseDto.class);
        scheduleMakerResponseDto.setRoleId(scheduleMaker.getRole().getId());
        scheduleMakerResponseDto.setLanguageId(scheduleMaker.getLanguage().getId());
        return scheduleMakerResponseDto;
    }
}
