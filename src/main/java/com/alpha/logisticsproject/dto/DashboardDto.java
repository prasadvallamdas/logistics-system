package com.alpha.logisticsproject.dto;

public class DashboardDto {

    private long totalOrders;
    private long totalDrivers;
    private long totalTrucks;

    private long pendingOrders;

    private long availableDrivers;

    private long availableTrucks;

    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public long getTotalDrivers() {
        return totalDrivers;
    }

    public void setTotalDrivers(long totalDrivers) {
        this.totalDrivers = totalDrivers;
    }

    public long getTotalTrucks() {
        return totalTrucks;
    }

    public void setTotalTrucks(long totalTrucks) {
        this.totalTrucks = totalTrucks;
    }

    public long getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(long pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public long getAvailableDrivers() {
        return availableDrivers;
    }

    public void setAvailableDrivers(long availableDrivers) {
        this.availableDrivers = availableDrivers;
    }

    public long getAvailableTrucks() {
        return availableTrucks;
    }

    public void setAvailableTrucks(long availableTrucks) {
        this.availableTrucks = availableTrucks;
    }
}