package com.ruba.borrowbox.controllers;

import com.ruba.borrowbox.entity.BorrowRequest;
import com.ruba.borrowbox.services.BorrowRequestService;
import org.springframework.web.bind.annotation.*;
import com.ruba.borrowbox.dto.BorrowRequestRequest;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/borrow-requests")
public class BorrowRequestController {

    private final BorrowRequestService borrowRequestService;
    public BorrowRequestController(BorrowRequestService borrowRequestService) {
        this.borrowRequestService = borrowRequestService;
    }

    @PostMapping
    public BorrowRequest createRequest(@RequestBody BorrowRequestRequest request) {
        return borrowRequestService.createRequest(request);
    }

    @GetMapping
    public List<BorrowRequest> getAllRequests() {
        return borrowRequestService.getAllRequests();
    }

    @GetMapping("/borrower/{borrowerId}")
    public List<BorrowRequest> getRequestsByBorrower(
            @PathVariable Integer borrowerId) {
        return borrowRequestService.getRequestsByBorrower(borrowerId);
    }

    @GetMapping("/owner/{ownerId}")
    public List<BorrowRequest> getRequestsForOwner(
            @PathVariable Integer ownerId) {
        return borrowRequestService.getRequestsForOwner(ownerId);
    }
}