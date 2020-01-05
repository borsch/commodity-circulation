package ua.net.kurpiak.commoditycirculation.pojo.views;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OutcomeOrderView implements IHasId<Integer> {

    private Integer id;
    private String comment;
    @Singular
    private List<OutcomeView> outcomes;
    private LocalDateTime dateCreated;

}
