package ua.net.kurpiak.commoditycirculation.pojo.entities;

import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "INCOME")
public class IncomeEntity implements IHasId<Integer> {

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

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public IncomeOrderEntity getIncomeOrder() {
        return incomeOrder;
    }

    public void setIncomeOrder(IncomeOrderEntity incomeOrder) {
        this.incomeOrder = incomeOrder;
    }

    public double getResidual() {
        return residual;
    }

    public void setResidual(double residual) {
        this.residual = residual;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getIncomePrice() {
        return incomePrice;
    }

    public void setIncomePrice(double incomePrice) {
        this.incomePrice = incomePrice;
    }

    public double getIncomePriceUsd() {
        return incomePriceUsd;
    }

    public void setIncomePriceUsd(double incomePriceUsd) {
        this.incomePriceUsd = incomePriceUsd;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IncomeEntity entity = (IncomeEntity) o;
        return id == entity.id && Double.compare(entity.residual, residual) == 0
               && Double.compare(entity.amount, amount) == 0 && Double.compare(entity.incomePrice, incomePrice) == 0
               && Double.compare(entity.incomePriceUsd, incomePriceUsd) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, residual, amount, incomePrice, incomePriceUsd);
    }

    @Override
    public String toString() {
        return "IncomeEntity{" + "id=" + id + ", residual=" + residual + ", amount=" + amount + ", incomePrice="
               + incomePrice + ", incomePriceUsd=" + incomePriceUsd + ", hasMore=" + hasMore + '}';
    }
}
