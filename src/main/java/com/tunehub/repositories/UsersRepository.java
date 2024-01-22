package com.tunehub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunehub.entities.users;

public interface UsersRepository extends JpaRepository<users,Integer >
{
	public users findByEmail(String email);
}
