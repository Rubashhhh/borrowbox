package com.ruba.borrowbox.services;

import com.ruba.borrowbox.entity.Item;
import com.ruba.borrowbox.repositories.ItemRepository;
import org.springframework.stereotype.Service;
import com.ruba.borrowbox.exceptions.ItemNotFoundException;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems(){
            return itemRepository.findAll();
    }
    public Item getItemById(int id){
        return itemRepository.findById(id)
                .orElseThrow(()->
                        new ItemNotFoundException ("Item with id " + id + " not found!"));
    }
    public Item addItem(Item item){
        return itemRepository.save(item);
    }
}