package com.tunehub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.entities.users;
import com.tunehub.repositories.UsersRepository;

@Service
public class UsersServiceImplementation implements UsersService
{
	@Autowired
	UsersRepository repo;
	//Registering the New User
	@Override
	public String addUser(users user) 
	{
		repo.save(user);
		return "User Added SUccessfully!";

	}
	//Finding if the Email Id is already existed or not
	@Override
	public boolean emailExits(String email) {
		if(repo.findByEmail(email)==null) 
		{
		return false;
		}
		else {
			return true;
		}
	}
	//login Validation
	@Override
	public boolean validateUser(String email, String password) {
		
		users user = repo.findByEmail(email);
		String db_password = user.getPassword();
		if(password.equals(db_password))
		{
			System.out.println("User Login Successfully!");
			return true;
		}
		else 
		{
			System.out.println("Enter Valid User Id and Password");
		return false;
		}
	}
	//validation for User Role
	@Override
	public String getRole(String email) {
		users user = repo.findByEmail(email);
		String role = user.getRole();
		return role;
	}
	//getting the user by email
	@Override
	public users getUser(String email) {
		users user = repo.findByEmail(email);
		return user;
	}
	//updaing the users
	@Override
	public void updateUser(users user) {
		repo.save(user);
	}

}
