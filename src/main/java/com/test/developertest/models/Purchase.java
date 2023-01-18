package com.test.developertest.models;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "age")
    private Long age;

    @Column(name = "purchaseitem")
    private String purchaseItem;

    @Column(name = "count")
    private Long count;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "purchasedate")
    private String purchaseDate;

    public Purchase() {
    }

    public Purchase(Long id, String name, String lastname, Long age, String purchaseItem, Long count, Float amount, String purchaseDate) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.purchaseItem = purchaseItem;
        this.count = count;
        this.amount = amount;
        this.purchaseDate = purchaseDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public Long getAge() {
        return age;
    }

    public String getPurchaseItem() {
        return purchaseItem;
    }

    public Long getCount() {
        return count;
    }

    public Float getAmount() {
        return amount;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(id, purchase.id) && Objects.equals(name, purchase.name) && Objects.equals(lastname, purchase.lastname) && Objects.equals(age, purchase.age) && Objects.equals(purchaseItem, purchase.purchaseItem) && Objects.equals(count, purchase.count) && Objects.equals(amount, purchase.amount) && Objects.equals(purchaseDate, purchase.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, age, purchaseItem, count, amount, purchaseDate);
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", purchaseItem='" + purchaseItem + '\'' +
                ", count=" + count +
                ", amount=" + amount +
                ", purchaseDate='" + purchaseDate + '\'' +
                '}';
    }
}
