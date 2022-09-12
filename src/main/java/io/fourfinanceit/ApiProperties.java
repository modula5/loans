package io.fourfinanceit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiProperties {

    @Value("${api.loan.minAmount}")
    private int minAmount;
    @Value("${api.loan.maxAmount}")
    private int maxAmount;
    @Value("${api.loan.minPeriod}")
    private int minPeriod;
    @Value("${api.loan.maxPeriod}")
    private int maxPeriod;
    @Value("${api.loan.firstForbiddenHour}")
    private int firstForbiddenHour;
    @Value("${api.loan.lastForbiddenHour}")
    private int lastForbiddenHour;
    @Value("${api.loan.maxApplicationPerDay}")
    private int maxApplicationPerDay;

    public int getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(int minAmount) {
        this.minAmount = minAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public int getMinPeriod() {
        return minPeriod;
    }

    public void setMinPeriod(int minPeriod) {
        this.minPeriod = minPeriod;
    }

    public int getMaxPeriod() {
        return maxPeriod;
    }

    public void setMaxPeriod(int maxPeriod) {
        this.maxPeriod = maxPeriod;
    }


    public int getFirstForbiddenHour() {
        return firstForbiddenHour;
    }

    public void setFirstForbiddenHour(int firstForbiddenHour) {
        this.firstForbiddenHour = firstForbiddenHour;
    }

    public int getLastForbiddenHour() {
        return lastForbiddenHour;
    }

    public void setLastForbiddenHour(int lastForbiddenHour) {
        this.lastForbiddenHour = lastForbiddenHour;
    }

    public int getMaxApplicationPerDay() {
        return maxApplicationPerDay;
    }

    public void setMaxApplicationPerDay(int maxApplicationPerDay) {
        this.maxApplicationPerDay = maxApplicationPerDay;
    }


}
