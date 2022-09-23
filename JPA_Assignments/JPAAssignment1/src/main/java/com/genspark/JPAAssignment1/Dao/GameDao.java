package com.genspark.JPAAssignment1.Dao;

import com.genspark.JPAAssignment1.Entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameDao extends JpaRepository<Game, Integer> {
}
