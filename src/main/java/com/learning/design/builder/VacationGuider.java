package com.learning.design.builder;

import java.util.Date;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: VacationGuider
 * @Author: sanwu
 * @Date: 2020/7/9 21:30
 */
public class VacationGuider {
    private VacationBuilder vacationBuilder;

    public VacationGuider(VacationBuilder vacationBuilder) {
        this.vacationBuilder = vacationBuilder;
    }

    public VacationBuilder getVacationBuilder() {
        return vacationBuilder;
    }

    public void setVacationBuilder(VacationBuilder vacationBuilder) {
        this.vacationBuilder = vacationBuilder;
    }

    public void createVacation() {
        Vacation vacation = new Vacation();
        vacationBuilder.setVacation(vacation);
    }

    public void addVacationPlan() {
        vacationBuilder.addHotel("lnn");
        vacationBuilder.addSpecialEvent("happy running");
        vacationBuilder.addTicket(3);
        vacationBuilder.buildDay(new Date());
    }

    public Vacation getVacation() {
       return vacationBuilder.getVacation();
    }
}
