package ua.net.kurpiak.commoditycirculation.pojo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
