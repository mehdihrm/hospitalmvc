package com.mehdi.hospitalmvc.security.service;

import com.mehdi.hospitalmvc.security.entities.AppRole;
import com.mehdi.hospitalmvc.security.entities.AppUser;
import com.mehdi.hospitalmvc.security.repo.AppRoleRepository;
import com.mehdi.hospitalmvc.security.repo.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    AppUserRepository appUserRepository;
    AppRoleRepository appRoleRepository;
    PasswordEncoder passwordEncoder;
    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPassword) {
        if(appUserRepository.findByUsername(username) != null){
            throw new RuntimeException("This user already exists ! ");
        }else
            if(!password.equals(confirmPassword)){
                throw new RuntimeException("Password dont match ! ");
            }
                AppUser user = AppUser.builder()
                        .userId(UUID.randomUUID().toString())
                        .username(username)
                        .email(email)
                        .password(passwordEncoder.encode(password))
                        .build();
                appUserRepository.save(user);


        return user;
    }

    @Override
    public AppRole addNewRole(String role) {
        return appRoleRepository.save(AppRole.builder().role(role).build());
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser user = appUserRepository.findByUsername(username);
        user.getRoles().add(appRoleRepository.findById(role).orElseThrow());
        appUserRepository.save(user);
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser user = appUserRepository.findByUsername(username);
        user.getRoles().remove(appRoleRepository.findById(role).orElseThrow());
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
