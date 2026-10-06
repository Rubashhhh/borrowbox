package com.ruba.borrowbox.entity;

import jakarta.persistence.*;

@Entity
public class BorrowRequest{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="borrower_id")
    private User borrower;
    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    public BorrowRequest(){}

    @Enumerated(EnumType.STRING)
    public BorrowRequestStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public BorrowRequestStatus getStatus() {
        return status;
    }

    public void setStatus(BorrowRequestStatus status) {
        this.status = status;
    }
}