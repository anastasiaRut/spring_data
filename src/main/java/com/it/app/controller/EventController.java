package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.request.EventRequestDto;
import com.it.app.dto.response.EventResponseDto;
import com.it.app.model.Event;
import com.it.app.model.Tutor;
import com.it.app.service.EventService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    private final Mapper mapper;

    private final LocalizedMessageSource localizedMessageSource;

    public EventController(EventService eventService, Mapper mapper, LocalizedMessageSource localizedMessageSource) {
        this.eventService = eventService;
        this.mapper = mapper;
        this.localizedMessageSource = localizedMessageSource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EventResponseDto>> getAll() {
        final List<Event> events = eventService.findAll();
        final List<EventResponseDto> eventDtoList = new ArrayList<>();
        events.stream()
                .forEach((Event) -> eventDtoList.add(getEventDto(Event)));
        return new ResponseEntity<>(eventDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<EventResponseDto> getOne(@PathVariable Long id) {
        final EventResponseDto eventResponseDto = getEventDto(eventService.findById(id));
        return new ResponseEntity<>(eventResponseDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<EventResponseDto> save(@Valid @RequestBody EventRequestDto eventRequestDto) {
        eventRequestDto.setId(null);
        final EventResponseDto eventResponseDto = getEventDto(eventService.save(getEvent(eventRequestDto)));
        return new ResponseEntity<>(eventResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<EventResponseDto> update(@Valid @RequestBody EventRequestDto eventRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, eventRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.event.unexpectedId", new Object[]{}));
        }
        final EventResponseDto eventResponseDto = getEventDto(eventService.update(getEvent(eventRequestDto)));
        return new ResponseEntity<>(eventResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/enroll", method = RequestMethod.PUT)
    public ResponseEntity<EventResponseDto> enroll(@RequestParam Long studentId, @RequestParam Long eventId) {
        final EventResponseDto eventResponseDto = getEventDto(eventService.enrollInEvent(studentId, eventId));
        return new ResponseEntity<>(eventResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        eventService.deleteById(id);
    }

    private EventResponseDto getEventDto(Event event) {
        final EventResponseDto eventResponseDto = mapper.map(event, EventResponseDto.class);
        eventResponseDto.setTutorId(event.getTutor().getId());

        return eventResponseDto;
    }

    private Event getEvent(EventRequestDto eventRequestDto) {
        final Event event = mapper.map(eventRequestDto, Event.class);
        final Tutor tutor = new Tutor();

        tutor.setId(eventRequestDto.getTutorId());
        event.setTutor(tutor);

        return event;
    }
}
