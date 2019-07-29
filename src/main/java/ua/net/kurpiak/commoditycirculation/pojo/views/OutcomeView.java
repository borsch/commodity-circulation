package ua.net.kurpiak.commoditycirculation.pojo.views;

import lombok.Data;
import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

@Data
public class OutcomeView implements IHasId<Integer> {

    private Integer id;
    private Integer productId;
    private double amount;
    private double salePrice;
    private Integer outcomeOrderId;

}
