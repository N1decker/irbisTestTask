package com.nidecker.testtask.service;

import com.nidecker.testtask.repository.SourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SourceService {

    private final SourceRepository sourceRepository;

}

