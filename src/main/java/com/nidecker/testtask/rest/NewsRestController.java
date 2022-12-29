package com.nidecker.testtask.rest;

import com.nidecker.testtask.entity.News;
import com.nidecker.testtask.service.NewsService;
import com.nidecker.testtask.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NewsRestController {

    private final NewsService newsService;

    @GetMapping("/news")
    public Page<News> findAll(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        return newsService.findAll(pageNo, pageSize);
    }

    @GetMapping("/newsBySource")
    public Page<News> findAllBySource(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "source") String source) {
        return newsService.findAllBySource(pageNo, pageSize, source);
    }

    @GetMapping("/newsByTopic")
    public Page<News> findAllByTopic(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "topic") String topic) {
        return newsService.findAllByTopic(pageNo, pageSize, topic);
    }
}
