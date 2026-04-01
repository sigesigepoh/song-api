package com.enriquez.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/enriquez/songs")
public class SongController {

    @Autowired
    private SongRepository SongRepository;


    @GetMapping
    public List<Song> getAllSongs() {
        return SongRepository.findAll();
    }


    @PostMapping
    public Song createSong(@RequestBody Song song) {
        return SongRepository.save(song);
    }

    @PutMapping("/{id}")
    public Song updateSong(@PathVariable Long id, @RequestBody Song songDetails) {
        Song song = SongRepository.findById(id).orElseThrow();
        song.setTitle(songDetails.getTitle());
        song.setArtist(songDetails.getArtist());
        song.setAlbum(songDetails.getAlbum());
        song.setGenre(songDetails.getGenre());
        song.setUrl(songDetails.getUrl());
        return SongRepository.save(song);
    }

    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable Long id) {
        SongRepository.deleteById(id);
    }
}