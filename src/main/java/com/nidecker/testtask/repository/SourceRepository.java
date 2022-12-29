package com.nidecker.testtask.repository;

import com.nidecker.testtask.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SourceRepository extends JpaRepository<Source, Long> {

    List<Source> findByNameLike(String name);
}
