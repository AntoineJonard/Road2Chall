package com.antoinejonard.road2chall.manager;

import com.antoinejonard.road2chall.model.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer> {
}
