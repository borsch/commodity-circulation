package ua.net.kurpiak.commoditycirculation.pojo.views;

import lombok.Builder;
import lombok.Data;
import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

@Data
@Builder
public class IncomeView implements IHasId<Integer> {

    private Integer id;
    private Integer productId;
    private Integer incomeOrderId;
    private double amount;
    private double incomePrice;
    private double incomePriceUsd;

}
