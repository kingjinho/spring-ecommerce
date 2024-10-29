package com.kingjinho.ecommerce.domain.item;

import com.kingjinho.ecommerce.domain.Category;
import com.kingjinho.ecommerce.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//
    //service에서 가져와서 계산하고 다시 set 해주는것보다 도메인에게 직접 처리하게 하는것이 일반적일수도있으나,
    //도메인에서 직접 처리하는게 응집력있고 관리하기 좋다. 그래서 비즈니스 로직을 직접 여기 도메인에 추가
    public void addStock(int stockQuantity) {
        this.stockQuantity += stockQuantity;
    }

    public void removeStock(int stockQuantity) {
        int restStock = this.stockQuantity - stockQuantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

}
