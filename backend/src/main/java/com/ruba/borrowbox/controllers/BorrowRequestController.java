package com.ruba.borrowbox.controllers;

import com.ruba.borrowbox.dto.BorrowRequestRequest;
import com.ruba.borrowbox.entity.BorrowRequest;
import com.ruba.borrowbox.services.BorrowRequestService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.List;

@RestController
@RequestMapping("/borrow-requests")
public class BorrowRequestController {
    private final BorrowRequestService borrowRequestService;

    public BorrowRequestController(
            BorrowRequestService borrowRequestService) {
        this.borrowRequestService = borrowRequestService;
    }

    @PostMapping
    public BorrowRequest createRequest(
            @RequestBody BorrowRequestRequest request,
            @AuthenticationPrincipal Jwt jwt) {
        Integer borrowerId = Integer.valueOf(jwt.getSubject());
        return borrowRequestService.createRequest(
                request, borrowerId);
    }

    @GetMapping("/borrower/{borrowerId}")
    public List<BorrowRequest> getRequestsByBorrower(
            @PathVariable Integer borrowerId,
            @AuthenticationPrincipal Jwt jwt) {
        Integer currentUserId = Integer.valueOf(jwt.getSubject());
        if (!currentUserId.equals(borrowerId)) {
            throw new AccessDeniedException(
                    "You can only view your own borrow requests");
        }
        return borrowRequestService.getRequestsByBorrower(
                currentUserId);
    }

    @GetMapping("/owner/{ownerId}")
    public List<BorrowRequest> getRequestsForOwner(
            @PathVariable Integer ownerId,
            @AuthenticationPrincipal Jwt jwt) {
        Integer currentUserId = Integer.valueOf(jwt.getSubject());
        if (!currentUserId.equals(ownerId)) {
            throw new AccessDeniedException(
                    "You can only view requests for your own items");
        }
        return borrowRequestService.getRequestsForOwner(
                currentUserId);
    }
    @PostMapping("/{requestId}/accept")
    public BorrowRequest acceptRequest(
            @PathVariable Integer requestId,
            @AuthenticationPrincipal Jwt jwt) {
        Integer ownerId = Integer.valueOf(jwt.getSubject());
        return borrowRequestService.acceptRequest(
                requestId, ownerId);
    }

    @PostMapping("/{requestId}/reject")
    public BorrowRequest rejectRequest(
            @PathVariable Integer requestId,
            @AuthenticationPrincipal Jwt jwt) {
        Integer ownerId = Integer.valueOf(jwt.getSubject());
        return borrowRequestService.rejectRequest(
                requestId, ownerId);
    }
}