package ru.shift.budgetplanner.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="purcheses")
public class Purchase {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer cost;

    //private Category category;

    //private User buyer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
