package com.ruba.borrowbox.services;

import com.ruba.borrowbox.entity.BorrowRequest;
import com.ruba.borrowbox.entity.Item;
import com.ruba.borrowbox.entity.User;
import com.ruba.borrowbox.exceptions.ItemNotFoundException;
import com.ruba.borrowbox.repositories.BorrowRequestRepository;
import com.ruba.borrowbox.repositories.ItemRepository;
import com.ruba.borrowbox.repositories.UserRepository;
import org.springframework.stereotype.Service;

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

    //to add
    public BorrowRequest createRequest(Integer borrowerId, Integer itemId) {
        User borrower = userRepository.findById(borrowerId)
                .orElseThrow(() ->
                        new RuntimeException("User with id " + borrowerId + " not found"));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() ->
                        new ItemNotFoundException("Item with id " + itemId + " not found"));
        if (item.getOwner().getId().equals(borrower.getId())) {
            throw new RuntimeException("You cannot request your own item");
        }
        BorrowRequest request = new BorrowRequest();
        request.setBorrower(borrower);
        request.setItem(item);
        request.setStatus("PENDING");
        return borrowRequestRepository.save(request);
    }
}