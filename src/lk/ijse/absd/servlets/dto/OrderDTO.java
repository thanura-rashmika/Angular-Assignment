package lk.ijse.absd.servlets.dto;

import java.sql.Date;
import java.util.List;

public class OrderDTO {
    private int oid;
    private Date date;
    private double total;
    private int cid;
    private List<OrderDetailDTO> orderDetailDTOS;

    public OrderDTO() {
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getOid() {
        return oid;
    }

    public Date getDate() {
        return date;
    }

    public double getTotal() {
        return total;
    }

    public List<OrderDetailDTO> getOrderDetailDTOS() {
        return orderDetailDTOS;
    }

    public void setOrderDetailDTOS(List<OrderDetailDTO> orderDetailDTOS) {
        this.orderDetailDTOS = orderDetailDTOS;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
