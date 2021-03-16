package com.antoinejonard.road2chall.manager;

import com.antoinejonard.road2chall.model.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Integer> {

    List<Team> findByCode(String code);

}
