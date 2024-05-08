package com.mehdi.hospitalmvc;

import com.mehdi.hospitalmvc.entities.Patient;
import com.mehdi.hospitalmvc.repository.PatientRepository;
import com.mehdi.hospitalmvc.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class HospitalmvcApplication {



    public static void main(String[] args) {
        SpringApplication.run(HospitalmvcApplication.class, args);
    }
    @Autowired
    PatientRepository patientRepository;

   // @Bean
    CommandLineRunner start(PatientRepository patientRepository){
        return args -> {
//        patientRepository.save(new Patient(null,"Yassine",new Date(),false,123));
//        patientRepository.save(new Patient(null,"Amine",new Date(),true,123));
//        patientRepository.save(new Patient(null,"Hanane",new Date(),false,123));
            // En utilisant Builder
            Patient patient1 = Patient.builder()
                    .nom("Aziz")
                    .dateNaissance(new Date())
                    .score(56)
                    .malade(true)
                    .build();
        };
    }
      //@Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder = passwordEncoder();
        return args -> {
            UserDetails u1 = jdbcUserDetailsManager.loadUserByUsername("user11");
            UserDetails u2 = jdbcUserDetailsManager.loadUserByUsername("user22");
            UserDetails a2 = jdbcUserDetailsManager.loadUserByUsername("admin2");

            if(u1==null)
                jdbcUserDetailsManager.createUser(User.withUsername("user11").password(passwordEncoder().encode("1234")).roles("USER").build());
            if(u2==null)
                jdbcUserDetailsManager.createUser(User.withUsername("user22").password(passwordEncoder().encode("1234")).roles("USER").build());
            if(a2==null)
                jdbcUserDetailsManager.createUser(User.withUsername("admin2").password(passwordEncoder().encode("1234")).roles("ADMIN","USER").build());

        };
    }
   // @Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
        return args -> {
         accountService.addNewRole("USER");
         accountService.addNewRole("ADMIN");
         accountService.addNewUser("user1","1234","user1@gmail.com","1234");
         accountService.addNewUser("user2","1234","user2@gmail.com","1234");
         accountService.addNewUser("admin","1234","admin@gmail.com","1234");

         accountService.addRoleToUser("user1","USER");
         accountService.addRoleToUser("user2","USER");
         accountService.addRoleToUser("admin","USER");
         accountService.addRoleToUser("admin","ADMIN");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}