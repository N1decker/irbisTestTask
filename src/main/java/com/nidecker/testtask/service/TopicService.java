package com.nidecker.testtask.service;

import com.nidecker.testtask.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

}

