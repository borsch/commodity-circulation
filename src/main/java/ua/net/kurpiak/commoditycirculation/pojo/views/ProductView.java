package ua.net.kurpiak.commoditycirculation.pojo.views;

import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

public class ProductView implements IHasId<Integer> {

    private int id;
    private String code;
    private String name;
    private String unit;
    private double residual;
    private double defaultPurchasePrice;
    private double defaultPurchasePriceUsd;
    private double defaultSalePrice;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getResidual() {
        return residual;
    }

    public void setResidual(double residual) {
        this.residual = residual;
    }

    public double getDefaultPurchasePrice() {
        return defaultPurchasePrice;
    }

    public void setDefaultPurchasePrice(double defaultPurchasePrice) {
        this.defaultPurchasePrice = defaultPurchasePrice;
    }

    public double getDefaultPurchasePriceUsd() {
        return defaultPurchasePriceUsd;
    }

    public void setDefaultPurchasePriceUsd(double defaultPurchasePriceUsd) {
        this.defaultPurchasePriceUsd = defaultPurchasePriceUsd;
    }

    public double getDefaultSalePrice() {
        return defaultSalePrice;
    }

    public void setDefaultSalePrice(double defaultSalePrice) {
        this.defaultSalePrice = defaultSalePrice;
    }

    @Override
    public String toString() {
        return "ProductView{" + "id=" + id + ", code='" + code + '\'' + ", name='" + name + '\'' + ", unit='" + unit
               + '\'' + ", residual=" + residual + ", defaultPurchasePrice=" + defaultPurchasePrice
               + ", defaultPurchasePriceUsd=" + defaultPurchasePriceUsd + ", defaultSalePrice=" + defaultSalePrice
               + '}';
    }
}
