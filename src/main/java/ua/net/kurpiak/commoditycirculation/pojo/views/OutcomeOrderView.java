package ua.net.kurpiak.commoditycirculation.pojo.views;

import lombok.Data;
import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

import java.util.List;

@Data
public class OutcomeOrderView implements IHasId<Integer> {

    private String comment;

    private List<OutcomeView> outcomes;

    private String dateCreated;

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public void setId(Integer id) {

    }
}
