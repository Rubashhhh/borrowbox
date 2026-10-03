package com.ruba.borrowbox.controllers;

import com.ruba.borrowbox.entity.BorrowRequest;
import com.ruba.borrowbox.services.BorrowRequestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrow-requests")
public class BorrowRequestController {
    private final BorrowRequestService borrowRequestService;
    public BorrowRequestController(BorrowRequestService borrowRequestService) {
        this.borrowRequestService = borrowRequestService;
    }

    @PostMapping
    public BorrowRequest createRequest(@RequestParam Integer borrowerId,
            @RequestParam Integer itemId) {
        return borrowRequestService.createRequest(borrowerId, itemId);
    }
}