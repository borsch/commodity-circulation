package ua.net.kurpiak.commoditycirculation.pojo.views;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OutcomeView implements IHasId<Integer> {

    private Integer id;
    private Integer productId;
    private double amount;
    private double salePrice;
    private Integer outcomeOrderId;

}
