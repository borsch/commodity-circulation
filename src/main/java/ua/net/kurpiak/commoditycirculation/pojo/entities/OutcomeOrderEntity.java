package ua.net.kurpiak.commoditycirculation.pojo.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@ToString(exclude = "outcomes")
@EqualsAndHashCode(exclude = "outcomes")
@Entity
@Table(name = "OUTCOME_ORDER")
public class OutcomeOrderEntity implements IHasId<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "COMMENT")
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @Column(name = "TOTAL_PRICE")
    private double totalPrice;

    @Column(name = "TOTAL_PROFIT")
    private double totalProfit;

    @OneToMany(mappedBy = "order")
    private List<OutcomeEntity> outcomes;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
