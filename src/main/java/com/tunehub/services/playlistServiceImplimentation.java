package com.tunehub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.entities.Playlist;
import com.tunehub.repositories.playlistRepository;

@Service
public class playlistServiceImplimentation implements playlistService{

	@Autowired
	playlistRepository repo;
	@Override
	public void addPlaylist(Playlist playlist) {
		System.out.println(playlist);
		repo.save(playlist);		
	}
	
	@Override
	public List<Playlist> fetchAllPlaylists() {
		return repo.findAll();
	}

//	@Override
//	public void removePlaylists(List<Long> playlistIds) {
//        for (Long playlistId : playlistIds) {
//        	int id = playlistId.intValue();
//            repo.deleteById(id);
//        }
//    }
	
	

}
