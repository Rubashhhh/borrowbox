package com.ruba.borrowbox.repositories;

import com.ruba.borrowbox.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ruba.borrowbox.entity.Item;
import java.util.List;

public interface UserRepository extends JpaRepository <User,Integer>{

}