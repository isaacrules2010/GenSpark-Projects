package com.genspark.JPAAssignment1.Controller;

import com.genspark.JPAAssignment1.Entity.Game;
import com.genspark.JPAAssignment1.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public String homeScreen(){
        return "<HTML><H1>Welcome to My Game List</H1></HTML>\n" +
                "Demonstrates the storage, editing, and management of a list of games that shows: \n" +
                "<ul><li>Game Title</li><li>Playtime in hours</li><li>My personal rating of the game</li></ul>";
    }

    @GetMapping("/games")
    public List<Game> getGames(){
        return this.gameService.getAllGames();
    }

    @GetMapping("/games/display")
    public String showGames(){
        return this.gameService.showAllGames();
    }

    @GetMapping("/games/{gameId}")
    public Game getGameById(@PathVariable String gameId){
        return this.gameService.getGameById(Integer.parseInt(gameId));
    }
    @GetMapping("/games/{gameId}/display")
    public String showGameById(@PathVariable String gameId){
        return this.gameService.showGameById(Integer.parseInt(gameId));
    }

    @PostMapping("/games")
    public Game addGame(@RequestBody Game game){
        return this.gameService.addGame(game);
    }

    @PutMapping("/games")
    public Game updateGame(@RequestBody Game game){
        return this.gameService.updateGame(game);
    }
    @DeleteMapping("/games/{gameId}")
    public String deleteGame(@PathVariable String gameId){
        return this.gameService.deleteGameById(Integer.parseInt(gameId));
    }
}
