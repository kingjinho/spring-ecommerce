package com.kingjinho.ecommerce.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) //EnumType.ORDINAL 이면 숫자로 들어가니 뭔지 모름, 그리고 중간에 삽입되면 문제가 날 확률이 높음
    private DeliveryStatus status;
}
