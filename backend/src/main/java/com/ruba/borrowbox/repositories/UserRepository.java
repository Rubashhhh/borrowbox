package com.ruba.borrowbox.repositories;

import com.ruba.borrowbox.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User,Integer>{

}