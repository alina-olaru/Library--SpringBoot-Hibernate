package com.alina.mylibrary.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemId implements Serializable {
    private int order;
    private int booksorder;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemId that = (OrderItemId) o;
        return order == that.order &&
                booksorder == that.booksorder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, booksorder);
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getBooksorder() {
        return booksorder;
    }

    public void setBooksorder(int booksorder) {
        this.booksorder = booksorder;
    }
}
