package com.antoinejonard.road2chall.manager;

import com.antoinejonard.road2chall.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Integer> {
}
