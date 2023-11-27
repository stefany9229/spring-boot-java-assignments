package org.adaschool.api.controller.user;

import jakarta.annotation.security.RolesAllowed;
import org.adaschool.api.data.user.UserEntity;
import org.adaschool.api.data.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static org.adaschool.api.utils.Constants.ADMIN_ROLE;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        loadSampleUsers();
    }

    public void loadSampleUsers() {
        //TODO Implementar este metodo
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable String id) {
        //TODO Implementar este metodo
        return ResponseEntity.ok(null);
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserDto userDto) {
        //TODO Implementar este metodo
        return ResponseEntity.ok(null);
    }

    @RolesAllowed(ADMIN_ROLE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String id) {
        //TODO Implementar este metodo
        return ResponseEntity.ok(null);

    }

}
