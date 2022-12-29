package com.nidecker.testtask.rest;

import com.nidecker.testtask.entity.Source;
import com.nidecker.testtask.repository.SourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class SourceRestController {

    private final SourceRepository sourceRepository;

    @GetMapping("/sources")
    public List<Source> findAll() {
        return sourceRepository.findAll();
    }
}
