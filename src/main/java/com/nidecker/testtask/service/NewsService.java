package com.nidecker.testtask.service;

import com.nidecker.testtask.entity.News;
import com.nidecker.testtask.repository.NewsRepository;
import com.nidecker.testtask.repository.SourceRepository;
import com.nidecker.testtask.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsService {

    private final NewsRepository newsRepository;
    private final TopicRepository topicRepository;
    private final SourceRepository sourceRepository;

    public Page<News> findAll(int pageNo, int pageSize) {
        log.info("find all news in page = {} and page size = {}", pageNo, pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return newsRepository.findAll(pageable);
    }

    public Page<News> findAllBySource(int pageNo, int pageSize, String sourceName) {
        log.info("find all news by source like {} in page = {} and page size = {}", sourceName, pageNo, pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return newsRepository.findAllBySourceIn(sourceRepository.findByNameLike("%" + sourceName + "%"), pageable);
    }

    public Page<News> findAllByTopic(int pageNo, int pageSize, String topicName) {
        log.info("find all news by topic like {} in page = {} and page size = {}", topicName, pageNo, pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return newsRepository.findAllByTopicIn(topicRepository.findByNameLike("%" + topicName + "%"), pageable);
    }
}
