package lk.ijse.absd.servlets.service.other;

import lk.ijse.absd.servlets.service.spec.impl.CustomerServiceimpl;
import lk.ijse.absd.servlets.service.spec.impl.ItemServiceimpl;
import lk.ijse.absd.servlets.service.spec.impl.OrderServiceimpl;

public class ServiceFactory {
    public enum ServiceTypes{
        CUSTOMER,ITEM,ORDERS,ORDER_DETAIL
    }

    public <T> T getService(ServiceTypes serviceTypes){
        switch (serviceTypes){
            case CUSTOMER:
                return (T) new CustomerServiceimpl();
            case ORDERS:
                return (T) new OrderServiceimpl();
            case ITEM:
                return (T) new ItemServiceimpl();
            default:
                return null;
        }
    }
}
