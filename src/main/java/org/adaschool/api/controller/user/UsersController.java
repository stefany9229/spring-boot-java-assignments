package org.adaschool.api.controller.user;

import org.adaschool.api.exception.UserNotFoundException;
import org.adaschool.api.repository.user.User;
import org.adaschool.api.service.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users/")
public class UsersController {

    private final UsersService usersService;

    public UsersController(@Autowired UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = usersService.save(user);
        URI createdUserUri = URI.create("/v1/users/" + savedUser.getId());
        return ResponseEntity.created(createdUserUri).body(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allProducts = usersService.all();
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        Optional<User> findById = usersService.findById(id);
        if (findById.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        return ResponseEntity.ok(findById);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(@RequestBody User updatedUser, @PathVariable("id") String id) {
        Optional<User> userOptional = usersService.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        User existingUser = userOptional.get();
        existingUser.setName(updatedUser.getName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());

        usersService.save(existingUser);
        return ResponseEntity.ok().build();
    }




    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        Optional<User> userOptional = usersService.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(id);
        } else {
            usersService.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
}
