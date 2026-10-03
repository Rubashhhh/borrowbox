package com.ruba.borrowbox.repositories;

import com.ruba.borrowbox.entity.BorrowRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRequestRepository
        extends JpaRepository<BorrowRequest,Integer>{}