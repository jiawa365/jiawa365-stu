package com.silence.repository;

import com.silence.entity.Module;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * Created by silence on 2018/2/2.
 */
public interface ModuleRepository extends CrudRepository<Module,String>{
    public List<Module> findAll();
}
