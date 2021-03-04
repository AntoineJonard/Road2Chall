package com.antoinejonard.road2chall.manager;

import com.antoinejonard.road2chall.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {

    List<User> findByName(String name);
}
