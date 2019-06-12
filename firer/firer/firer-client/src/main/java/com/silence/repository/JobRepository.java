package com.silence.repository;

import com.silence.entity.Job;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by silence on 2018/2/2.
 */
public interface JobRepository extends CrudRepository<Job,String> {
    List<Job> findByStatus(Integer started);
    List<Job> findAll();
}
