package lk.ijse.absd.servlets.entity;

import java.util.Objects;

public class OrderDetail_PK {
    private int code;
    private int oid;

    public OrderDetail_PK() {

    }

    public OrderDetail_PK(int code, int oid) {
        this.code = code;
        this.oid = oid;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int  code) {
        this.code = code;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetail_PK)) return false;
        OrderDetail_PK that = (OrderDetail_PK) o;
        return getCode() == that.getCode() &&
                getOid() == that.getOid();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCode(), getOid());
    }
}
