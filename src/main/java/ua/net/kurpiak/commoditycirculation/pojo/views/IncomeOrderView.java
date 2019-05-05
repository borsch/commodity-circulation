package ua.net.kurpiak.commoditycirculation.pojo.views;

import ua.net.kurpiak.commoditycirculation.pojo.helpers.IHasId;

import java.util.List;

public class IncomeOrderView implements IHasId<Integer> {

    private int id;

    private String comment;

    private List<IncomeView> incomes;

    private String dateCreated;

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

    public List<IncomeView> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<IncomeView> incomes) {
        this.incomes = incomes;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "IncomeOrderView{" + "id=" + id + ", comment='" + comment + '\'' + ", incomes=" + incomes + ", dateCreated=" + dateCreated + '}';
    }
}
