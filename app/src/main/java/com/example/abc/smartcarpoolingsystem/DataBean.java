package com.example.abc.smartcarpoolingsystem;

/**
 * Created by abc on 3/23/2018.
 */

public class DataBean {
    public String id,departureplace,arrivalplace,date,time,whethercar,number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDepartureplace() {
        return departureplace;

    }

    public void setDepartureplace(String departureplace) {
        this.departureplace = departureplace;
    }

    public String getArrivalplace() {
        return arrivalplace;
    }

    public void setArrivalplace(String arrivalplace) {
        this.arrivalplace = arrivalplace;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DataBean(String id, String departureplace, String arrivalplace,
                    String date, String time, String whethercar, String number) {
        this.id = id;
        this.departureplace = departureplace;
        this.arrivalplace = arrivalplace;
        this.date = date;
        this.time = time;
        this.whethercar = whethercar;
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWhethercar() {
        return whethercar;
    }

    public void setWhethercar(String whethercar) {
        this.whethercar = whethercar;
    }

    public DataBean(){

    }
}
