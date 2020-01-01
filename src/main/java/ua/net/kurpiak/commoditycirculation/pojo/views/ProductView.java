package ua.net.kurpiak.commoditycirculation.pojo.views;

import lombok.Builder;
import lombok.Data;
import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

@Data
@Builder
public class ProductView implements IHasId<Integer> {

    private Integer id;
    private String code;
    private String name;
    private String unit;
    private Double residual;
    private double defaultPurchasePrice;
    private double defaultPurchasePriceUsd;
    private double defaultSalePrice;

}
