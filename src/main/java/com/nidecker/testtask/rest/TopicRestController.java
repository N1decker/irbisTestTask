package com.nidecker.testtask.rest;

import com.nidecker.testtask.entity.Topic;
import com.nidecker.testtask.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TopicRestController {

    private final TopicRepository topicRepository;

    @GetMapping("/topics")
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }
}
