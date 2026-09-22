package com.ruba.borrowbox.controllers;

import com.ruba.borrowbox.entity.Item;
import com.ruba.borrowbox.services.ItemService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/items")
public class ItemController{
    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService=itemService;
    }

    @GetMapping
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }
    @GetMapping("/{id}")
    public Item getItemById(@PathVariable int id){
        return itemService.getItemById(id);
    }
    @PostMapping
    public Item addItem(@RequestBody Item item){
        return itemService.addItem(item);
    }
}