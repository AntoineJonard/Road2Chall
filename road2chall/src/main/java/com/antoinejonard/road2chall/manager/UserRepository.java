package com.antoinejonard.road2chall.manager;

import com.antoinejonard.road2chall.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
