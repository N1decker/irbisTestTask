package com.nidecker.testtask.service;

import com.nidecker.testtask.entity.News;
import com.nidecker.testtask.repository.NewsRepository;
import com.nidecker.testtask.repository.SourceRepository;
import com.nidecker.testtask.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final TopicRepository topicRepository;
    private final SourceRepository sourceRepository;

    public Page<News> findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return newsRepository.findAll(pageable);
    }

    public Page<News> findAllBySource(int pageNo, int pageSize, String sourceName) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return newsRepository.findAllBySourceIn(sourceRepository.findByNameLike("%" + sourceName + "%"), pageable);
    }

    public Page<News> findAllByTopic(int pageNo, int pageSize, String topicName) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return newsRepository.findAllByTopicIn(topicRepository.findByNameLike("%" + topicName + "%"), pageable);
    }
}
