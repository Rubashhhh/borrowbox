package com.ruba.borrowbox.services;

import com.ruba.borrowbox.entity.BorrowRequest;
import com.ruba.borrowbox.entity.Item;
import com.ruba.borrowbox.entity.User;
import com.ruba.borrowbox.exceptions.ItemNotFoundException;
import com.ruba.borrowbox.repositories.BorrowRequestRepository;
import com.ruba.borrowbox.repositories.ItemRepository;
import com.ruba.borrowbox.repositories.UserRepository;
import org.springframework.stereotype.Service;
import com.ruba.borrowbox.dto.BorrowRequestRequest;
import com.ruba.borrowbox.entity.BorrowRequestStatus;
import java.util.List;
import java.time.LocalDate;

@Service
public class BorrowRequestService{
    private final BorrowRequestRepository borrowRequestRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public BorrowRequestService(
            BorrowRequestRepository borrowRequestRepository,
            UserRepository userRepository,
            ItemRepository itemRepository){
        this.borrowRequestRepository = borrowRequestRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    //to add request
    public BorrowRequest createRequest(BorrowRequestRequest request) {
        User borrower = userRepository.findById(request.getBorrowerId())
                .orElseThrow(() ->
                        new RuntimeException("User with id " + request.getBorrowerId() + " not found"));
        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() ->
                        new ItemNotFoundException("Item with id " + request.getItemId() + " not found"));
        if (!item.isAvailable()) {
            throw new RuntimeException("This item is currently unavailable");
        }
        if (item.getOwner().getId().equals(borrower.getId())) {
            throw new RuntimeException("You cannot request your own item");
        }
        if (request.getStartDate() == null || request.getEndDate() == null) {
            throw new RuntimeException("Start date and end date are required");
        }
        if (request.getStartDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Start date cannot be in the past");
        }
        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new RuntimeException("End date cannot be before start date");
        }
        List<BorrowRequest> overlappingRequests = borrowRequestRepository
                        .findByItemIdAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                                request.getItemId(),
                                BorrowRequestStatus.ACCEPTED,
                                request.getEndDate(),
                                request.getStartDate());
        if (!overlappingRequests.isEmpty()) {
            throw new RuntimeException("Item is already booked for the requested dates");
        }
        boolean alreadyRequested = borrowRequestRepository
                        .existsByBorrowerIdAndItemIdAndStatusAndStartDateAndEndDate(
                                request.getBorrowerId(),
                                request.getItemId(),
                                BorrowRequestStatus.PENDING,
                                request.getStartDate(),
                                request.getEndDate());

        if (alreadyRequested) {
            throw new RuntimeException(
                    "You already have a pending request for this item and these dates");
        }
        BorrowRequest borrowRequest = new BorrowRequest();
        borrowRequest.setBorrower(borrower);
        borrowRequest.setItem(item);
        borrowRequest.setStatus(BorrowRequestStatus.PENDING);
        borrowRequest.setStartDate(request.getStartDate());
        borrowRequest.setEndDate(request.getEndDate());

        return borrowRequestRepository.save(borrowRequest);
    }

    public List<BorrowRequest> getAllRequests() {
        return borrowRequestRepository.findAll();
    }

    //WHO has requested
    //find by borrower's/requester's id
    public List<BorrowRequest> getRequestsByBorrower(Integer borrowerId) {
        return borrowRequestRepository.findByBorrowerId(borrowerId);
    }

    //FROM WHOM has been requested
    //find by owner's id
    public List<BorrowRequest> getRequestsForOwner(Integer ownerId){
        return borrowRequestRepository.findByItemOwnerId(ownerId);
    }

    public BorrowRequest acceptRequest(Integer requestId) {
        BorrowRequest request = borrowRequestRepository.findById(requestId)
                .orElseThrow(() ->
                        new RuntimeException("Borrow request with id " + requestId + " not found"));
        if (request.getStatus() != BorrowRequestStatus.PENDING) {
            throw new RuntimeException("Only pending requests can be accepted");
        }
        request.setStatus(BorrowRequestStatus.ACCEPTED);
        return borrowRequestRepository.save(request);
    }

    public BorrowRequest rejectRequest(Integer requestId) {
        BorrowRequest request = borrowRequestRepository.findById(requestId)
                .orElseThrow(() ->
                        new RuntimeException("Borrow request with id " + requestId + " not found"));
        if (request.getStatus() != BorrowRequestStatus.PENDING) {
            throw new RuntimeException("Only pending requests can be rejected");
        }
        request.setStatus(BorrowRequestStatus.REJECTED);
        return borrowRequestRepository.save(request);
    }
}