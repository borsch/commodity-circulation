package ua.net.kurpiak.commoditycirculation.pojo.entities;

import org.hibernate.validator.constraints.NotEmpty;
import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "PRODUCTS")
public class ProductEntity implements IHasId<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @NotEmpty(message = "Код продукту є обов'язковим")
    @NotNull(message = "Код продукту є обов'язковим")
    @Size(min = 1, max = 25, message = "Код продутку може мати максимум 20 символів")
    @Column(name = "CODE")
    private String code;

    @NotEmpty(message = "Назва продукту є обов'язкова")
    @NotNull(message = "Назва продукту є обов'язкова")
    @Size(min = 1, max = 250, message = "Назва продутку може мати максимум 250 символів")
    @Column(name = "NAME")
    private String name;

    @NotEmpty(message = "Тип одиниці продукта є обов'язковим")
    @NotNull(message = "Тип одиниці продукта є обов'язковим")
    @Size(min = 1, max = 10, message = "Тип одиниці продукта може мати максимум 10 символів")
    @Column(name = "UNIT")
    private String unit;

    @Column(name = "RESIDUAL")
    private double residual;

    @Column(name = "DEFAULT_PURCHASE_PRICE")
    private double defaultPurchasePrice;

    @Column(name = "DEFAULT_PURCHASE_PRICE_USD")
    private double defaultPurchasePriceUsd;

    @Column(name = "DEFAULT_SALE_PRICE")
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductEntity entity = (ProductEntity) o;
        return id == entity.id && Double.compare(entity.residual, residual) == 0
               && Double.compare(entity.defaultPurchasePrice, defaultPurchasePrice) == 0
               && Double.compare(entity.defaultPurchasePriceUsd, defaultPurchasePriceUsd) == 0
               && Double.compare(entity.defaultSalePrice, defaultSalePrice) == 0 && Objects.equals(code, entity.code)
               && Objects.equals(name, entity.name) && Objects.equals(unit, entity.unit);
    }

    @Override
    public int hashCode() {
        return Objects
            .hash(id, code, name, unit, residual, defaultPurchasePrice, defaultPurchasePriceUsd, defaultSalePrice);
    }

    @Override
    public String toString() {
        return "ProductEntity{" + "id=" + id + ", code='" + code + '\'' + ", name='" + name + '\'' + ", unit='" + unit
               + '\'' + ", residual=" + residual + ", defaultPurchasePrice=" + defaultPurchasePrice
               + ", defaultPurchasePriceUsd=" + defaultPurchasePriceUsd + ", defaultSalePrice=" + defaultSalePrice
               + '}';
    }
}
