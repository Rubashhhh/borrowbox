package com.ruba.borrowbox.repositories;

import com.ruba.borrowbox.entity.BorrowRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BorrowRequestRepository
        extends JpaRepository<BorrowRequest,Integer>{
    List<BorrowRequest> findByBorrowerId(Integer borrowerId);
    List<BorrowRequest> findByItemOwnerId(Integer ownerId);
}