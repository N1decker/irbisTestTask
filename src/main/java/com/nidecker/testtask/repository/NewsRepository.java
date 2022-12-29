package com.nidecker.testtask.repository;

import com.nidecker.testtask.entity.News;
import com.nidecker.testtask.entity.Source;
import com.nidecker.testtask.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findAllBySourceId(Long sourceId);

    List<News> findAllByTopicId(Long topic_id);

    Page<News> findAllBySourceIn(Collection<Source> source, Pageable pageable);

    Page<News> findAllByTopicIn(Collection<Topic> topic, Pageable pageable);
}
