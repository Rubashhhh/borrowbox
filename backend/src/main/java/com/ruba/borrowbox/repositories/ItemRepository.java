package com.ruba.borrowbox.repositories;

import com.ruba.borrowbox.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemRepository extends JpaRepository <Item, Integer>{
    List<Item> findByCategory(String category);
}
