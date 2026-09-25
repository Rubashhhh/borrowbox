package com.ruba.borrowbox.services;

import com.ruba.borrowbox.entity.Item;
import com.ruba.borrowbox.repositories.ItemRepository;
import org.springframework.stereotype.Service;
import com.ruba.borrowbox.exceptions.ItemNotFoundException;
import com.ruba.borrowbox.repositories.UserRepository;
import com.ruba.borrowbox.entity.User;
import com.ruba.borrowbox.dto.ItemRequest;


import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public ItemService(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository=userRepository;
    }

    public List<Item> getAllItems(){
            return itemRepository.findAll();
    }
    public Item getItemById(int id){
        return itemRepository.findById(id)
                .orElseThrow(()->
                        new ItemNotFoundException ("Item with id " + id + " not found!"));
    }
    public Item addItem(ItemRequest request){
        User owner=userRepository.findById(request.getOwnerId())
                .orElseThrow(()->
                        new RuntimeException("User with id " + request.getOwnerId() + " not found!"));
        Item item=new Item();

        item.setName(request.getName());
        item.setCategory(request.getCategory());
        item.setDescription(request.getDescription());
        item.setPricePerDay(request.getPricePerDay());
        item.setConditions(request.getConditions());
        item.setAvailable(request.isAvailable());
        item.setOwner(owner);

        return itemRepository.save(item);
    }

    public Item updateItem(Integer id, Item updatedItem){
        Item existingItem=itemRepository.findById(id)
                .orElseThrow(()->
                        new ItemNotFoundException ("Item with id " + id + " not found!"));
        existingItem.setName(updatedItem.getName());
        existingItem.setCategory(updatedItem.getCategory());
        existingItem.setDescription(updatedItem.getDescription());
        existingItem.setPricePerDay(updatedItem.getPricePerDay());
        existingItem.setConditions(updatedItem.getConditions());
        existingItem.setAvailable(updatedItem.isAvailable());

        return itemRepository.save(existingItem);
    }

    public void deleteItem(int id){
        Item existingItem=itemRepository.findById(id)
                .orElseThrow(()->
                        new ItemNotFoundException ("Item with id " + id + " not found!"));
        itemRepository.delete(existingItem);
    }

    public List<Item> getItemsByCategory(String category){
        return itemRepository.findByCategory(category);
    }
}