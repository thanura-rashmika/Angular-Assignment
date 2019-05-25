package lk.ijse.absd.servlets.entity;

public class OrderDetails {
    private OrderDetail_PK orderDetail_pk;
    private double unitPrice;
    private double qty;

    public OrderDetails() {
    }

    public OrderDetail_PK getOrderDetail_pk() {
        return orderDetail_pk;
    }

    public void setOrderDetail_pk(OrderDetail_PK orderDetail_pk) {
        this.orderDetail_pk = orderDetail_pk;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }
}
