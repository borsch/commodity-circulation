package ua.net.kurpiak.commoditycirculation.pojo.views;

import java.util.List;

import lombok.Data;
import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

@Data
public class IncomeOrderView implements IHasId<Integer> {

    private Integer id;
    private String comment;
    private List<IncomeView> incomes;
    private String dateCreated;

}
