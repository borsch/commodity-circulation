package ua.net.kurpiak.commoditycirculation.pojo.views;

import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

public class IncomeView implements IHasId<Integer> {

    private int id;

    private Integer productId;

    private Integer incomeOrderId;

    private double residual;

    private double amount;

    private double incomePrice;

    private double incomePriceUsd;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getIncomeOrderId() {
        return incomeOrderId;
    }

    public void setIncomeOrderId(Integer incomeOrderId) {
        this.incomeOrderId = incomeOrderId;
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

    @Override
    public String toString() {
        return "IncomeView{" + "id=" + id + ", productId=" + productId + ", incomeOrderId=" + incomeOrderId + ", residual=" + residual + ", amount=" + amount
               + ", incomePrice=" + incomePrice + ", incomePriceUsd=" + incomePriceUsd + '}';
    }
}
