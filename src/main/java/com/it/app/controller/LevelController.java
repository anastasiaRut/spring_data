package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.LevelDto;
import com.it.app.dto.LevelDto;
import com.it.app.model.Level;
import com.it.app.service.LevelService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/levels")
public class LevelController {
    private final Mapper mapper;

    private final LevelService levelService;

    private final LocalizedMessageSource localizedMessageSource;

    public LevelController(Mapper mapper, LevelService levelService, LocalizedMessageSource localizedMessageSource) {
        this.mapper = mapper;
        this.levelService = levelService;
        this.localizedMessageSource = localizedMessageSource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<LevelDto>> getAll() {
        final List<Level> levels = levelService.findAll();
        final List<LevelDto> levelDtoList = levels.stream()
                .map((Level) -> mapper.map(Level, LevelDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(levelDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<LevelDto> getOne(@PathVariable Long id) {
        final LevelDto levelDto = mapper.map(levelService.findById(id), LevelDto.class);
        return new ResponseEntity<>(levelDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<LevelDto> save(@Valid @RequestBody LevelDto levelDto) {
        levelDto.setId(null);
        final LevelDto responseLevelDto = mapper.map(levelService.save(mapper.map(levelDto, Level.class)), LevelDto.class);
        return new ResponseEntity<>(responseLevelDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<LevelDto> update(@Valid @RequestBody LevelDto levelDto, @PathVariable Long id) {
        if (!Objects.equals(id, levelDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.level.unexpectedId", new Object[]{}));
        }
        final LevelDto responseLevelDto = mapper.map(levelService.update(mapper.map(levelDto, Level.class)), LevelDto.class);
        return new ResponseEntity<>(responseLevelDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        levelService.deleteById(id);
    }

}
