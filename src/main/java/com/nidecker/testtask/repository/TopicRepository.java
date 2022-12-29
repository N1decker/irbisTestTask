package com.nidecker.testtask.repository;

import com.nidecker.testtask.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findByNameLike(String name);
}
