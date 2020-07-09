package com.learning.design.builder;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: CustomerClient
 * @Author: sanwu
 * @Date: 2020/7/9 21:34
 */
public class CustomerClient {

    public static void main(String[] args) {
        VacationGuider vacationGuider = new VacationGuider(new VacationBuilder());
        vacationGuider.createVacation();
        vacationGuider.addVacationPlan();
        System.out.println(vacationGuider.getVacation());
    }
}
