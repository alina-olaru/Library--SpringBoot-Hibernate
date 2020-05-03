package com.alina.mylibrary.model.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@IdClass(OrderItemId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem  implements Serializable
{
    @JsonIgnoreProperties(ignoreUnknown=true, value = {"items"}, allowSetters = true)
    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_ORDER_ORDERITEM_ID"))
    private BookOrder order;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"items"}, allowSetters = true)
    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_BOOK_ORDERITEM_ID"))
    private Book booksorder;


    @NotNull
    @Column
    @ColumnDefault("0")
    private int quantity=0;
    //todo de vazut de ce in baza poate fi null

}
