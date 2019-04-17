package com.it.app.controller;

import com.it.app.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EventController {
    @Autowired
    EventService eventService;
}
