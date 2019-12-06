package ua.net.kurpiak.commoditycirculation.pojo.views;

import lombok.Data;
import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

@Data
public class IncomeView implements IHasId<Integer> {

    private Integer id;
    private Integer productId;
    private Integer incomeOrderId;
    private double residual;
    private double amount;
    private double incomePrice;
    private double incomePriceUsd;

}
