package com.tunehub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.entities.Song;
import com.tunehub.repositories.SongRepository;

@Service
public class SongServiceImplementation implements SongService{
	
	@Autowired
	SongRepository repo;
	
	@Override
	public void addSong(Song song) {
		repo.save(song);
		System.out.println("Song Successfully Added!");
	}

	@Override
	public List<Song> fetchAllSongs() {
		List<Song> songList = repo.findAll();
		return songList;
	}

	@Override
	public boolean songExits(String name) {
		Song song = repo.findByName(name);
		if (song== null)
		{
		return false;
		}
		else 
		{
			return true;
		}
	}

	@Override
	public void updateSong(Song song) {
		repo.save(song);
		System.out.println("Song Successfully Updated!");
		
	}

}
