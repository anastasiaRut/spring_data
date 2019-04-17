package com.it.app.controller;

import com.it.app.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TutorController {
    @Autowired
    TutorService tutorService;
}
