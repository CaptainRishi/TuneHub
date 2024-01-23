package com.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tunehub.entities.Song;
import com.tunehub.entities.users;
import com.tunehub.services.SongService;
import com.tunehub.services.UsersService;
import jakarta.servlet.http.HttpSession;


@Controller
public class UsersController 
{
	@Autowired
	UsersService service;
	@Autowired
	SongService songservice;
	
	//Registration Controller
	@PostMapping("/registration")
	public String adduser(@ModelAttribute users user)
	{	
		boolean userStatus = service.emailExits(user.getEmail());
		if (userStatus == false) 
		{
		service.addUser(user);
		System.out.println("User is Added SuccessFully!");
		}   
		else
		{
			System.out.println("Users already exits");
		}
		return "home";
		}
	
	//login validation controller
	@PostMapping ("/validate")
	public String validate(@RequestParam ("email") String email,
			@RequestParam ("password") String password,
			HttpSession session, Model model)
	{	
		if (service.validateUser(email, password) == true)
		{
			String role = service.getRole(email);
			
			session.setAttribute("email", email);
			
			if(role.equals("admin"))
			{
				return "adminHome";
			}
			else {
				users user = service.getUser(email);
				boolean userStatus = user.isPremium();
				List <Song>songList = songservice.fetchAllSongs();
				model.addAttribute("songs", songList);
				model.addAttribute("isPremium",userStatus);
				return "customerHome";
			}
		}else
		{
			return"login";
		}
	}
	
//	@GetMapping("paymentStatus")
//	public String paymentStatus(@RequestParam String email) {
//		boolean paymentStatus = false;
//		
//		if(paymentStatus == true)
//		{
//			users user =service.getUser(email);
//			user.setPremium(true);
//			service.updateUser(user);
//		}
//		
//		return "login";
//	}
	
	@GetMapping("/logout")
	public String logout(HttpSession sesson) {
		sesson.invalidate();
		return "login";
	}
	
	
}
