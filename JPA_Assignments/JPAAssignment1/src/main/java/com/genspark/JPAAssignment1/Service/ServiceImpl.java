package com.genspark.JPAAssignment1.Service;

import com.genspark.JPAAssignment1.Dao.GameDao;
import com.genspark.JPAAssignment1.Entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceImpl implements GameService{

    @Autowired
    private GameDao games;

    @Override
    public List<Game> getAllGames() {
        return this.games.findAll();
    }

    @Override
    public String showAllGames() {
        List<Game> g = this.games.findAll();
        String out = "";
        for(Game game : g){
            out+=game.getTitle() +
                    "\n<ul>"+
                    "<li>Hours Played: "+game.getPlayTime()+"</li>"+
                    "<li>Rating: "+game.getRating()+"</li>"+
                    "</ul>\n";
        }
        return out;
    }

    @Override
    public Game getGameById(int gameId) {
        Optional<Game> g = this.games.findById(gameId);
        Game game = null;
        if(g.isPresent()){
            game = g.get();
        }
        else{
            throw new RuntimeException("Game not found for id :: "+gameId);
        }
        return game;
    }

    @Override
    public String showGameById(int gameId) {
        Optional<Game> g = this.games.findById(gameId);
        String out;
        if(g.isPresent()){
            out = g.get().getTitle()+
                    "\n<ul>"+
                    "<li>Hours Played: "+g.get().getPlayTime()+"</li>"+
                    "<li>Rating: "+g.get().getRating()+"</li>"+
                    "</ul>\n";
        }
        else{
            out = "No game found with Id :: "+gameId;
        }
        return out;
    }

    @Override
    public Game addGame(Game game) {
        return this.games.save(game);
    }

    @Override
    public Game updateGame(Game game) {
        return this.games.save(game);
    }

    @Override
    public String deleteGameById(int gameId) {
        this.games.deleteById(gameId);
        return "Deleted Game Successfully";
    }
}
