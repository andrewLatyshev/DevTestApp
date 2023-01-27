package com.test.developertest.models;


import com.test.developertest.service.DateAdapter;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@XmlRootElement(name = "purchase")
@XmlType(propOrder = {"id", "name", "lastname", "age", "count", "amount", "purchasedate", "purchaseitem"})
@Table(name = "purchases")
@NamedQueries({@NamedQuery(name = "showAllPurchases", query = "select p from Purchase p"),
               @NamedQuery(name = "deletePurchase", query = "delete from Purchase where id = :id"),
               })
@NamedNativeQueries({@NamedNativeQuery(name = "createPurchase", query = "insert into purchases (name, lastname, age,  count, amount, purchasedate, purchaseitem) "
                             + "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7)"),
              @NamedNativeQuery(name = "reportForWeek", query = "select * "
                             + "from purchases where current_date - purchasedate < 7 "),
              @NamedNativeQuery(name = "goodOfMonth", query = "select purchaseitem, sum(count) as purchase_count  from purchases "
                             + "where current_date - purchases.purchasedate < 30 "
                             + "group by purchaseitem "
                             + "order by purchase_count "
                             + "limit 1"),
              @NamedNativeQuery(name = "topBuyer", query = "select name, lastname, count(count) as top_buyer from purchases "
                             + "where current_date - purchasedate < 180 "
                             + "group by name, lastname "
                             + "order by top_buyer desc "
                             + "limit 1"),
              @NamedNativeQuery(name = "bestFor18", query = "select purchaseitem, count(count) as top_18 from purchases "
                             + "where purchases.age = 18 "
                             + "group by purchaseitem "
                             + "order by top_18 "
                             + "desc limit 1")
})
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "age")
    private Byte age;

    @JoinColumn(name = "purchaseitem", referencedColumnName = "item_name")
    @OneToOne
    private Product purchaseItem;

    @Column(name = "count")
    private Long count;

    @Column(name = "amount")
    private Float amount;

    @XmlElement(name = "purchasedate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    @Column(name = "purchasedate")
    private LocalDate purchaseDate;

    public Purchase() {
    }

    public Purchase(Long id, String name, String lastname, Byte age, Product purchaseItem, Long count, Float amount, LocalDate purchaseDate) {
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

    public Byte getAge() {
        return age;
    }

    public Product getPurchaseItem() {
        return purchaseItem;
    }

    public Long getCount() {
        return count;
    }

    public Float getAmount() {
        return amount;
    }

    public LocalDate getPurchaseDate() {
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
