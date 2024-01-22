package com.tunehub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunehub.entities.Playlist;

public interface playlistRepository extends JpaRepository<Playlist, Integer>{

}
