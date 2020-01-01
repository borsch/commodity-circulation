package ua.net.kurpiak.commoditycirculation.pojo.views;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

@Data
@Builder
public class IncomeOrderView implements IHasId<Integer> {

    private Integer id;
    private String comment;
    @Singular
    private List<IncomeView> incomes;
    private LocalDate dateCreated;

}
