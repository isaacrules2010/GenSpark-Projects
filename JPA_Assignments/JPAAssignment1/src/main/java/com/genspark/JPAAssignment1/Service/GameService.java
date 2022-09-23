package com.genspark.JPAAssignment1.Service;

import com.genspark.JPAAssignment1.Entity.Game;

import java.util.List;

public interface GameService {
    List<Game> getAllGames();
    String showAllGames();
    Game getGameById(int gameId);
    String showGameById(int gameId);
    Game addGame(Game game);
    Game updateGame(Game game);
    String deleteGameById(int gameId);
}
