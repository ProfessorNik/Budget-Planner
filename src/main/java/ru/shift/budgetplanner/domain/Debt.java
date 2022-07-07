package ru.shift.budgetplanner.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "debts")
public class Debt {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer cost;

    //private Category category;

    //private User buyer;

    //private User debtor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
