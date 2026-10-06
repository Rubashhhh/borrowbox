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
        if (item.getOwner().getId().equals(borrower.getId())) {
            throw new RuntimeException("You cannot request your own item");
        }
        BorrowRequest borrowRequest = new BorrowRequest();
        borrowRequest.setBorrower(borrower);
        borrowRequest.setItem(item);
        borrowRequest.setStatus(BorrowRequestStatus.PENDING);
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

}