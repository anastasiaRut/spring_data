package com.it.app.controller;

import com.it.app.service.ScheduleMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ScheduleMakerController {
    @Autowired
    ScheduleMakerService scheduleMakerService;
}
