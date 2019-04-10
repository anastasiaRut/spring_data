package com.it.app.controller;

import com.it.app.model.Language;
import com.it.app.service.LanguageService;
import com.it.app.service.impl.LanguageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LanguageController {
    @Autowired
    LanguageService languageService;

    @RequestMapping(value = "/modelAndView", method = RequestMethod.GET)
    public ModelAndView modelAndView() {
        return new ModelAndView("language", "message", "Hello World");
    }

    @RequestMapping(value = "/addLanguage", method = RequestMethod.GET)
    public String addLanguage(@ModelAttribute("value") String value, ModelMap model) {
        model.addAttribute("message", "Add language:" + value);
        Language language = new Language();
        language.setName(value);
        languageService.addLanguage(language);
        return "language";
    }
}