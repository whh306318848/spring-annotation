package com.atguigu.bean;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/28 18:37
 * @description: TODO
 */
public class Color {

    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Color{" +
                "car=" + car +
                '}';
    }
}
