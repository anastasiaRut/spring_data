package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.request.TutorRequestDto;
import com.it.app.dto.response.TutorResponseDto;
import com.it.app.dto.response.TutorResponseDto;
import com.it.app.model.Language;
import com.it.app.model.Level;
import com.it.app.model.Tutor;
import com.it.app.service.TutorService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/tutors")
public class TutorController {
    private final Mapper mapper;

    private final TutorService tutorService;

    private final LocalizedMessageSource localizedMessageSource;

    public TutorController(Mapper mapper, TutorService tutorService, LocalizedMessageSource localizedMessageSource) {
        this.mapper = mapper;
        this.tutorService = tutorService;
        this.localizedMessageSource = localizedMessageSource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TutorResponseDto>> getAll() {
        final List<Tutor> tutors = tutorService.findAll();
        final List<TutorResponseDto> tutorDtoList = new ArrayList<>();
        tutors.stream()
                .forEach((Tutor) -> tutorDtoList.add(getTutorDto(Tutor)));
        return new ResponseEntity<>(tutorDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TutorResponseDto> getOne(@PathVariable Long id) {
        final TutorResponseDto tutorResponseDto = getTutorDto(tutorService.findById(id));
        return new ResponseEntity<>(tutorResponseDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TutorResponseDto> save(@Valid @RequestBody TutorRequestDto tutorRequestDto) {
        tutorRequestDto.setId(null);
        final TutorResponseDto tutorResponseDto = getTutorDto(tutorService.save(getTutor(tutorRequestDto)));
        return new ResponseEntity<>(tutorResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TutorResponseDto> update(@Valid @RequestBody TutorRequestDto tutorRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, tutorRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.tutor.unexpectedId", new Object[]{}));
        }
        final TutorResponseDto tutorResponseDto = getTutorDto(tutorService.update(getTutor(tutorRequestDto)));
        return new ResponseEntity<>(tutorResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        tutorService.deleteById(id);
    }

    private Tutor getTutor(TutorRequestDto tutorRequestDto) {
        final Tutor tutor = mapper.map(tutorRequestDto, Tutor.class);
        final Language language = new Language();
        final Set<Level> levels = new HashSet<>();
        Level level;
        final Set<Long> levelsId = tutorRequestDto.getLevelsId();
        language.setId(tutorRequestDto.getLanguageId());
        tutor.setLanguage(language);
        for (Long id : levelsId) {
            level = new Level();
            level.setId(id);
            levels.add(level);
        }
        tutor.setLevels(levels);
        return tutor;
    }

    private TutorResponseDto getTutorDto(Tutor tutor) {
        final TutorResponseDto tutorResponseDto = mapper.map(tutor, TutorResponseDto.class);
        tutorResponseDto.setLanguageId(tutor.getLanguage().getId());
        final Set<Level> levels = tutor.getLevels();
        final Set<Long> levelsId = new HashSet<>();
        for (Level level : levels) {
            levelsId.add(level.getId());
        }
        tutorResponseDto.setLevelsId(levelsId);
        return tutorResponseDto;
    }
}
