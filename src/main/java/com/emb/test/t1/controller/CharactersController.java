package com.emb.test.t1.controller;

import com.emb.test.t1.data.CharactersDataInput;
import com.emb.test.t1.service.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class CharactersController {

    @Autowired
    private CharactersService service;

    @PostMapping("character_frequency")
    public String characterFrequency(@RequestBody CharactersDataInput characters) {
        return null;
    }
}
