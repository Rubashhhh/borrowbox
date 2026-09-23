package com.ruba.borrowbox.controllers;

import com.ruba.borrowbox.entity.Item;
import com.ruba.borrowbox.services.ItemService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.*;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;

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

    @GetMapping("/search")
    public List<Item> findByCategory(@RequestParam String category){
        return itemService.getItemsByCategory(category);
    }
    @GetMapping("/{id}")
    public Item getItemById(@PathVariable int id){
        return itemService.getItemById(id);
    }
    @PostMapping
    public Item addItem(@Valid
                            @RequestBody Item item){
        return itemService.addItem(item);
    }

    @PutMapping ("/{id}")
    public Item updateItem( @PathVariable int id,
                            @RequestBody Item updatedItem){
        return itemService.updateItem(id, updatedItem);
    }

    @DeleteMapping("/{id}")
    public void deleteItem( @PathVariable int id){
        itemService.deleteItem(id);
    }


}