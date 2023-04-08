package edu.iu.c322.mist.downloads.repository;

import edu.iu.c322.mist.downloads.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameListRepository extends JpaRepository<Game, Integer> {

}
