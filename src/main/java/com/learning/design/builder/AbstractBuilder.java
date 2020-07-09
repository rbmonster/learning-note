package com.learning.design.builder;

import java.util.Date;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: AbstractBuilder
 * @Author: sanwu
 * @Date: 2020/7/9 21:24
 */
public interface AbstractBuilder {

      void buildDay(Date date);

      void addHotel(String hotel);

      void addSpecialEvent(String event);

      void addTicket(int ticketNum);

}
