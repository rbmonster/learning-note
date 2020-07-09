package com.learning.design.builder;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Vacation
 * @Author: sanwu
 * @Date: 2020/7/9 21:25
 */
public class Vacation {

    private Date date;
    private String hotel;
    private int ticketNum;
    private String specialEvent;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    public String getSpecialEvent() {
        return specialEvent;
    }

    public void setSpecialEvent(String specialEvent) {
        this.specialEvent = specialEvent;
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "date=" + date +
                ", hotel='" + hotel + '\'' +
                ", ticketNum=" + ticketNum +
                ", specialEvent='" + specialEvent + '\'' +
                '}';
    }
}
