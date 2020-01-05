package ua.net.kurpiak.commoditycirculation.pojo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

@Data
@ToString(exclude = "order")
@EqualsAndHashCode(exclude = "order")
@Entity
@Table(name = "OUTCOME")
public class OutcomeEntity implements IHasId<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @NotNull(message = "Продукт є обов'язковим полем")
    @ManyToOne(fetch = FetchType.EAGER)
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

    @Column(name = "PROFIT")
    private double profit;

}
