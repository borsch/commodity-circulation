package ua.net.kurpiak.commoditycirculation.pojo.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

@Data
@ToString(exclude = "incomes")
@EqualsAndHashCode(exclude = "incomes")
@Entity
@Table(name = "INCOME_ORDER")
public class IncomeOrderEntity implements IHasId<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "DATE_CREATED")
    private LocalDate dateCreated;

    @OneToMany(mappedBy = "incomeOrder", fetch = FetchType.EAGER)
    private List<IncomeEntity> incomes;

}
