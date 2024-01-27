package com.tunehub.services;

import java.util.List;

import com.tunehub.entities.Playlist;

public interface playlistService {

	public void addPlaylist(Playlist playlist);
	
	public  List<Playlist> fetchAllPlaylists();

//	public  void removePlaylists(List<Long> playlistIds);
	


}
