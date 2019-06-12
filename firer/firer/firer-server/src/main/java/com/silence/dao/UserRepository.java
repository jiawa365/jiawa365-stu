package com.silence.dao;

import com.silence.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by silence on 2018/2/28.
 */
public interface UserRepository extends CrudRepository<User,String> {
    User findByEmailOrPhone(String email,String phone);

    User findByValiCode(String valiCode);

    User findByUserName(String userName);
}
