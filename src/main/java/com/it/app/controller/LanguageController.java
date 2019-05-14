package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.LanguageDto;
import com.it.app.model.Language;
import com.it.app.service.LanguageService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The class represents a REST Controller for Language entity
 *
 * @author A. Rutkouskaya
 * @see Language
 */
@RestController
@RequestMapping("/languages")
public class LanguageController {
    private final Mapper mapper;

    private final LanguageService languageService;

    private final LocalizedMessageSource localizedMessageSource;

    public LanguageController(Mapper mapper, LanguageService languageService, LocalizedMessageSource localizedMessageSource) {
        this.mapper = mapper;
        this.languageService = languageService;
        this.localizedMessageSource = localizedMessageSource;
    }

    /**
     * Gets all Languages
     *
     * @return ResponseEntity<List   <   LanguageResponseDto>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<LanguageDto>> getAll() {
        final List<Language> languages = languageService.findAll();
        final List<LanguageDto> languageDtoList = languages.stream()
                .map((Language) -> mapper.map(Language, LanguageDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(languageDtoList, HttpStatus.OK);
    }

    /**
     * Gets one Language
     *
     * @param id -id
     * @return ResponseEntity<LanguageResponseDto>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<LanguageDto> getOne(@PathVariable Long id) {
        final LanguageDto languageDto = mapper.map(languageService.findById(id), LanguageDto.class);
        return new ResponseEntity<>(languageDto, HttpStatus.OK);
    }

    /**
     * Saves Language
     *
     * @param languageDto - languageRDto
     * @return ResponseEntity<LanguageResponseDto>
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<LanguageDto> save(@Valid @RequestBody LanguageDto languageDto) {
        languageDto.setId(null);
        final LanguageDto responseLanguageDto = mapper.map(languageService.save(mapper.map(languageDto, Language.class)), LanguageDto.class);

        return new ResponseEntity<>(responseLanguageDto, HttpStatus.OK);
    }

    /**
     * Updates Language
     *
     * @param languageDto - languageDto
     * @return ResponseEntity<LanguageResponseDto>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<LanguageDto> update(@Valid @RequestBody LanguageDto languageDto, @PathVariable Long id) {
        if (!Objects.equals(id, languageDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.language.unexpectedId", new Object[]{}));
        }
        final LanguageDto responseLanguageDto = mapper.map(languageService.update(mapper.map(languageDto, Language.class)), LanguageDto.class);
        return new ResponseEntity<>(responseLanguageDto, HttpStatus.OK);
    }

    /**
     * Deletes Language
     *
     * @param id - id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        languageService.deleteById(id);
    }
}