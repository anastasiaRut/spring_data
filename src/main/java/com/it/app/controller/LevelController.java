package com.it.app.controller;

import com.it.app.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LevelController {
    @Autowired
    LevelService levelService;

}
