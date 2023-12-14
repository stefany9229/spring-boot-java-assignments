package org.adaschool.api.service.user;

import org.adaschool.api.exception.UserNotFoundException;
import org.adaschool.api.repository.product.Product;
import org.adaschool.api.repository.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceMap implements UsersService {

    private final ArrayList<User> listUser;

    public UsersServiceMap(ArrayList<User> listUser) {
        this.listUser = new ArrayList<>();
    }

    @Override
    public User save(User user) {
        listUser.add(user);
        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        Optional<User> userOptional = listUser.stream().filter(user -> user.getId().equals(id)).findFirst();
        if (userOptional.isEmpty()){
            throw new UserNotFoundException(id);
        }

        return userOptional;
    }

    @Override
    public List<User> all() {
        return listUser;
    }

    @Override
    public void deleteById(String id) {
        Optional<User> userOptional = listUser.stream().filter(user -> user.getId().equals(id)).findFirst();
        listUser.remove(userOptional.get());
    }

    @Override
    public User update(User user, String userId) {
        Optional<User> userOptional = findById(userId);
        User user1 = null;
        if (!userOptional.isEmpty()){
            user1 = userOptional.get();
            user1.setName(user.getName());
            user1.setLastName(user.getLastName());
            user1.setEmail(user.getEmail());
        }
        return user1;
    }
}
