package com.mehdi.hospitalmvc.security.repo;

import com.mehdi.hospitalmvc.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String>    {
    AppUser findByUsername(String username);
}
