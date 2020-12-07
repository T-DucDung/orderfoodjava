package net.codejava.orderfoodspring.Auth.controllers;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import jdk.jfr.internal.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import net.codejava.orderfoodspring.Auth.models.User;
import net.codejava.orderfoodspring.Auth.models.ERole;
import net.codejava.orderfoodspring.Auth.models.Role;
import net.codejava.orderfoodspring.Auth.payload.request.UpdateUserRequest;
import net.codejava.orderfoodspring.Auth.payload.response.MessageResponse;
import org.springframework.http.HttpStatus;
import net.codejava.orderfoodspring.Auth.security.services.UserDetailsServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import net.codejava.orderfoodspring.Auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.access.prepost.PreAuthorize;
import net.codejava.orderfoodspring.Auth.repository.RoleRepository;

@RestController
public class UserController {
    @Autowired
    private UserDetailsServiceImpl service;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public List<User> listuser() {
        return service.listAll();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> update(@RequestBody UpdateUserRequest userupdate, @PathVariable Integer id){
        if (userRepository.existsByUsername(userupdate.getUsername())) {
            Set<String> strRoles = userupdate.getRole();
            Set<Role> roles = new HashSet<>();
            if (strRoles == null) {
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            Role adminRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: CAN NOT UPGARADE TO ADMIN_ROLE --> Down to UserRole!!!"));
                            roles.add(adminRole);
                            break;
                        case "mod":
                            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(modRole);
    
                            break;
                        default:
                            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(userRole);
                    }
                });
            }
            System.out.println(strRoles.iterator().next());
            if(strRoles.iterator().next() == "user"){
                System.out.println("hihi");
            }
            userRepository.updateUser(id, userupdate.getUsername(), encoder.encode(userupdate.getPassword()), userupdate.getEmail());
            // userRepository.updateRoleUser(id, role_id);
            return new ResponseEntity<UpdateUserRequest>(userupdate, HttpStatus.OK);
        }else{
            return ResponseEntity
                .badRequest()
                    .body(new MessageResponse("Error: Username is not found!!!"));
        }
    }
    
    @PutMapping("/user/{id}")
    public ResponseEntity<?> deleterole(@RequestBody User user, @PathVariable Integer id){
        if (userRepository.existsByUsername(user.getUsername())) {
            encoder.encode(user.getPassword());
            service.save(user);
            return new ResponseEntity<>("Success: Delete Role!!!", HttpStatus.OK);
        }else{
            return ResponseEntity
                .badRequest()
                    .body(new MessageResponse("Error: Username is not found!!!"));
        }
    }
}