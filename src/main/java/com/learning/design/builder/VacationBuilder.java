package com.learning.design.builder;

import java.util.Date;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: VacationBuilder
 * @Author: sanwu
 * @Date: 2020/7/9 21:28
 */
public class VacationBuilder implements AbstractBuilder {

    public Vacation getVacation() {
        return vacation;
    }

    public void setVacation(Vacation vacation) {
        this.vacation = vacation;
    }

    private Vacation vacation;
    @Override
    public void buildDay(Date date) {
        vacation.setDate(date);
    }

    @Override
    public void addHotel(String hotel) {
        vacation.setHotel(hotel);
    }

    @Override
    public void addSpecialEvent(String event) {
        vacation.setSpecialEvent(event);
    }

    @Override
    public void addTicket(int ticketNum) {
        vacation.setTicketNum(ticketNum);
    }
}
