package edu.iu.c322.mist.downloads.controller;

import edu.iu.c322.mist.downloads.model.CustomerProfile;
import edu.iu.c322.mist.downloads.model.Game;
import edu.iu.c322.mist.downloads.model.UserVerify;
import edu.iu.c322.mist.downloads.repository.GameListRepository;
import edu.iu.c322.mist.downloads.repository.UserListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/download")
public class DownloadController {

    private UserListRepository userRepository;
    private GameListRepository gameRepository;

    public DownloadController(UserListRepository userRepository, GameListRepository gameRepository){
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    @GetMapping("/{gameId}")
    public Game download(@PathVariable int gameId, @RequestBody UserVerify verify){
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "game does not exist"));
        CustomerProfile user = userRepository.getCustomerProfileByUsernameEqualsAndPasswordEquals(verify.username(), verify.password());
        if (user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user does not exist");
        }
        if (user.getOwnedGames().contains(game)){
            return game;
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "specified user does not own game");
    }



}
