package com.mehdi.hospitalmvc.security.repo;

import com.mehdi.hospitalmvc.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,String> {

}
