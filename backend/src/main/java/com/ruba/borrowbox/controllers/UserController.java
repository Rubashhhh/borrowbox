package com.ruba.borrowbox.controllers;

import com.ruba.borrowbox.entity.User;
import com.ruba.borrowbox.services.UserService;
import org.springframework.web.bind.annotation.*;
import com.ruba.borrowbox.entity.Item;
import com.ruba.borrowbox.services.ItemService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController{
    private final UserService userService;
    private final ItemService itemService;
    public UserController(UserService userService, ItemService itemService){
        this.userService=userService;
        this.itemService=itemService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @GetMapping("/{userId}/items")
    public List<Item> getUserItems(@PathVariable Integer userId){
        return itemService.getItemsByUser(userId); //this method is not in userService but in itemService
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }
}