package ua.net.kurpiak.commoditycirculation.pojo.entities;

import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "INCOME_ORDER")
public class IncomeOrderEntity implements IHasId<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "COMMENT")
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @OneToMany(mappedBy = "incomeOrder")
    private List<IncomeEntity> incomes;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<IncomeEntity> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<IncomeEntity> incomes) {
        this.incomes = incomes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IncomeOrderEntity entity = (IncomeOrderEntity) o;
        return id == entity.id && Objects.equals(comment, entity.comment) && Objects
            .equals(dateCreated, entity.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comment, dateCreated);
    }

    @Override
    public String toString() {
        return "IncomeOrderEntity{" + "id=" + id + ", comment='" + comment + '\'' + ", dateCreated=" + dateCreated
               + '}';
    }
}
