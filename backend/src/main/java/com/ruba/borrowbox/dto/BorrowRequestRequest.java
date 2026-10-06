package com.ruba.borrowbox.dto;
import java.time.LocalDate;

public class BorrowRequestRequest {
    private Integer borrowerId;
    private Integer itemId;
    private LocalDate startDate;
    private LocalDate endDate;

    public BorrowRequestRequest() {
    }
    public Integer getBorrowerId() {
        return borrowerId;
    }
    public void setBorrowerId(Integer borrowerId) {
        this.borrowerId = borrowerId;
    }

    public Integer getItemId() {
        return itemId;
    }
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}