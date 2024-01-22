package com.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tunehub.entities.Song;
import com.tunehub.services.SongService;

@Controller 
public class SongController {
	@Autowired
	SongService service;
	@PostMapping ("/addsong")
	public String addSong(@ModelAttribute Song song)
	{
		boolean songStatus = service.songExits(song.getName());
		
		if (songStatus==false) {
			service.addSong(song);
		}
		else
		{
			System.out.println("Song is already exits!");
			return "newSong"; 
		}
		return "newSong";
		
		
	}
	@GetMapping("/viewSongs")
	public String viewSongs(Model model)
	{
		List <Song>songList = service.fetchAllSongs();
		model.addAttribute("songs", songList);
		System.out.println(songList);
		return "displaySongs";
	}
	@GetMapping("/playSongs")
	public String playSongs(Model model)
	{
		boolean premiumUser = false;
		if(premiumUser==true)
		{
			List <Song>songList = service.fetchAllSongs();
			model.addAttribute("songs", songList);
			System.out.println(songList);
			return "displaySongs";
		}
		else
		{
			return "makePayment";
		}
	
	}

}
