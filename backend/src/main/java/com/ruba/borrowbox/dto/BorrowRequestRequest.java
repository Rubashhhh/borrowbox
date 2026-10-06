package com.ruba.borrowbox.dto;

public class BorrowRequestRequest {
    private Integer borrowerId;
    private Integer itemId;

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
}