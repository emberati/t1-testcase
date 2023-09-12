package com.emb.test.t1.controller;

import com.emb.test.t1.data.dto.CharactersSortInfo;
import com.emb.test.t1.exception.IllegalFrequencySortingStrategy;
import com.emb.test.t1.service.CharactersService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/characters")
public class CharactersController {

    @Autowired
    private CharactersService service;
    @Autowired
    private ModelMapper mapper;

    @PostMapping("/freq")
    public ResponseEntity<String> characterFrequency(@RequestBody CharactersSortInfo data) {
        try {
            var sorted = service.sortedByFrequencyCharactersFromString(data);
            return new ResponseEntity<>(mapper.map(sorted, ObjectNode.class).toString(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty Input String", e);
        } catch (IllegalFrequencySortingStrategy e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Sorting Order", e);
        }
    }
}
