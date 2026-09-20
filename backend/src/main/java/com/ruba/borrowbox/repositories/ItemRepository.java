package com.ruba.borrowbox.repositories;

import com.ruba.borrowbox.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository <Item, Integer>{

}
