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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "INCOME")
public class IncomeEntity implements IHasId<Integer> {

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
    @JoinColumn(name = "INCOME_ORDER_ID")
    private IncomeOrderEntity incomeOrder;

    @Column(name = "RESIDUAL")
    private double residual;

    @Column(name = "AMOUNT")
    private double amount;

    @Column(name = "INCOME_PRICE")
    private double incomePrice;

    @Column(name = "INCOME_PRICE_USD")
    private double incomePriceUsd;

    @Column(name = "HAS_MORE")
    private boolean hasMore;

}
