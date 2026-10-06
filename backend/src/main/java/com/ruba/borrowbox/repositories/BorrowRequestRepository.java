package com.ruba.borrowbox.repositories;

import com.ruba.borrowbox.entity.BorrowRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.ruba.borrowbox.entity.BorrowRequestStatus;
import java.time.LocalDate;

public interface BorrowRequestRepository
        extends JpaRepository<BorrowRequest,Integer>{
    List<BorrowRequest> findByBorrowerId(Integer borrowerId);
    List<BorrowRequest> findByItemOwnerId(Integer ownerId);

    List<BorrowRequest> findByItemIdAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Integer itemId, BorrowRequestStatus status, LocalDate endDate, LocalDate startDate
    );

    boolean existsByBorrowerIdAndItemIdAndStatusAndStartDateAndEndDate(Integer borrowerId, Integer itemId, BorrowRequestStatus status, LocalDate startDate, LocalDate endDate);
}