package com.alina.mylibrary.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class OrderItem  implements Serializable
{
    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_ORDER_ORDERITEM_ID"))
    private BookOrder order;


    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_BOOK_ORDERITEM_ID"))
    private Book booksorder;


    @NotNull
    @Column
    @ColumnDefault("0")
    private int quantity=0;
    //todo de vazut de ce in baza poate fi null

    public OrderItem() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return quantity == orderItem.quantity &&
                Objects.equals(order, orderItem.order) &&
                Objects.equals(booksorder, orderItem.booksorder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, booksorder, quantity);
    }

    public BookOrder getOrder() {
        return order;
    }

    public void setOrder(BookOrder order) {
        this.order = order;
    }

    public Book getBooksorder() {
        return booksorder;
    }

    public void setBooksorder(Book booksorder) {
        this.booksorder = booksorder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
