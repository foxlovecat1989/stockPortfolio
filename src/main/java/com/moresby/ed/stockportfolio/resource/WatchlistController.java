package com.moresby.ed.stockportfolio.resource;

import com.moresby.ed.stockportfolio.domain.Watchlist;
import com.moresby.ed.stockportfolio.service.WatchlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/watchlists")
@RequiredArgsConstructor
public class WatchlistController {

    private final WatchlistService watchlistService;

    @GetMapping(path = "/findAll", produces = "application/json")
    public Iterable<Watchlist> findAll(){
        return watchlistService.findAll();
    }

    @GetMapping(path = "/{watchlistId}", produces = "application/json")
    public ResponseEntity<Watchlist> findOneById(@PathVariable Long watchlistId){
        Optional<Watchlist> optWatch = watchlistService.findOneById(watchlistId);
        if (optWatch.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(optWatch.get(), HttpStatus.OK);
    }

    @GetMapping(path = "/findAll/{userId}", produces = "application/json")
    public Iterable<Watchlist> findAllByUserId(@PathVariable("userId") Long userId){
        return watchlistService.findAllByUserId(userId);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Watchlist createWatch(@RequestBody Watchlist watchlist){
        return watchlistService.createWatch(watchlist);
    }

    @PatchMapping(consumes = "application/json")
    public Watchlist updateWatch(@RequestBody Watchlist watchlist){
        return  watchlistService.updateWatch(watchlist);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        try{
            watchlistService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
    }
}