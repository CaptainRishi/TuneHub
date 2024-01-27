package com.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;

import com.tunehub.entities.Playlist;
import com.tunehub.entities.Song;
import com.tunehub.services.SongService;
import com.tunehub.services.playlistService;

@Controller
public class PlaylistController {
	
	@Autowired
	SongService songService;
	@Autowired
	playlistService PlaylistService;
	
	@GetMapping ("/createPlaylist")
	public String createPlaylist(Model model)
	{
		 List<Song> songList = songService.fetchAllSongs();
		 model.addAttribute("songs", songList);
		return "createPlaylist";
	}
	
	@PostMapping ("/addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist)
	{	//updating playlist table
//		System.out.println(playlist);
		PlaylistService.addPlaylist(playlist);
		
		//updaing song table
		List<Song> songList = playlist.getSongs();
		for(Song s : songList) 
		{
			s.getPlayLists().add(playlist);
			//updaing song objects in db
			songService.updateSong(s);
		}
		return "adminHome";
	}
	@GetMapping("/viewPlaylists")
	public String viewPlaylists(Model model) {
		List <Playlist> allPlaylists = PlaylistService.fetchAllPlaylists();
		model.addAttribute("allPlaylists",allPlaylists);
		return "displayPlaylists";
	}
	
//	@GetMapping("/deletePlaylists")
//	public String deletePlaylists(Model model)
//	{	
//		List <Playlist> allPlaylists = PlaylistService.fetchAllPlaylists();
//		model.addAttribute("allPlaylists",allPlaylists);
////		System.out.println(allPlaylists);
//		return "deletePlaylists";
//	}
	
//	@DeleteMapping("/removePlaylists")
//	public String removePlaylists(@RequestParam(name = "allPlaylists", required = false) List<Long> playlistIds) {
//        if (playlistIds != null && !playlistIds.isEmpty()) {
//        	PlaylistService.removePlaylists(playlistIds);   
//        }
//        return "adminHome";
//	}

}
