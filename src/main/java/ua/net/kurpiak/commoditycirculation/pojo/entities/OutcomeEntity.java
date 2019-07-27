package ua.net.kurpiak.commoditycirculation.pojo.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@ToString(exclude = "order")
@EqualsAndHashCode(exclude = "order")
@Entity
@Table(name = "OUTCOME")
public class OutcomeEntity implements IHasId<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @NotNull(message = "Продукт є обов'язковим полем")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity product;

    @NotNull(message = "Замовлення є обов'язковим полем")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OUTCOME_ORDER_ID")
    private OutcomeOrderEntity order;

    @Column(name = "AMOUNT")
    private double amount;

    @Column(name = "SALE_PRICE")
    private double salePrice;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

}
