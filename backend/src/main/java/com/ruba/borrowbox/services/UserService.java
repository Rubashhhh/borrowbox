package com.ruba.borrowbox.services;

import com.ruba.borrowbox.entity.User;
import com.ruba.borrowbox.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(int id){
        return userRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException ("User with id " + id + " not found!"));
    }
}