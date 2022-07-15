package ru.shift.budgetplanner.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="purcheses")
public class Purchase {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;

    private Long addedTime;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Long getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Long addedTime) {
        this.addedTime = addedTime;
    }
}
