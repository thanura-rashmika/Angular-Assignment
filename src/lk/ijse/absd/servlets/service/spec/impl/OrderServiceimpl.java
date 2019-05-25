package lk.ijse.absd.servlets.service.spec.impl;

import lk.ijse.absd.servlets.db.DBConnection;
import lk.ijse.absd.servlets.dto.OrderDTO;
import lk.ijse.absd.servlets.dto.OrderDetailDTO;
import lk.ijse.absd.servlets.entity.Item;
import lk.ijse.absd.servlets.entity.OrderDetail_PK;
import lk.ijse.absd.servlets.entity.OrderDetails;
import lk.ijse.absd.servlets.entity.Orders;
import lk.ijse.absd.servlets.repository.other.RepoFactory;
import lk.ijse.absd.servlets.repository.spec.ItemRepo;
import lk.ijse.absd.servlets.repository.spec.OrderDetailRepo;
import lk.ijse.absd.servlets.repository.spec.OrderRepo;
import lk.ijse.absd.servlets.service.spec.OrderService;
import lk.ijse.absd.servlets.util.CrudUtil;
import org.modelmapper.ModelMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class OrderServiceimpl implements OrderService {
    private OrderRepo orderRepo;
    private OrderDetailRepo orderDetailRepo;
    private ItemRepo itemRepo;
    private ModelMapper modelMapper;

    public OrderServiceimpl() {
        this.orderRepo=new RepoFactory().getRepo(RepoFactory.RepoTypes.ORDERS);
        this.orderDetailRepo=new RepoFactory().getRepo(RepoFactory.RepoTypes.ORDER_DETAIL);
        this.itemRepo=new RepoFactory().getRepo(RepoFactory.RepoTypes.ITEM);
        this.modelMapper=new ModelMapper();
    }

    @Override
    public boolean add(OrderDTO orderDTO)  {
        try {
            Connection connection = DBConnection.getConnection();
            Objects.requireNonNull(connection).setAutoCommit(false);
            try {
                Orders orders = modelMapper.map(orderDTO, Orders.class);
                boolean add = orderRepo.add(orders);
                if (!add) {
                    connection.rollback();
                    return false;
                }
                int lastOrderId = orderRepo.getLastOrderId();
                for (OrderDetailDTO orderDetailDTO : orderDTO.getOrderDetailDTOS()) {
                    OrderDetails orderDetails = new OrderDetails();
                    orderDetails.setOrderDetail_pk(
                            new OrderDetail_PK(
                                    orderDetailDTO.getCode(),
                                    lastOrderId
                            )
                    );
                    orderDetails.setUnitPrice(orderDetailDTO.getUnitPrice());
                    orderDetails.setQty(orderDetailDTO.getQty());
                    add = orderDetailRepo.add(orderDetails);
                    if (!add) {
                        connection.rollback();
                        return false;
                    }
                    Item item = itemRepo.search(orderDetailDTO.getCode());
                    item.setQty(item.getQty() - orderDetailDTO.getQty());
                    add = itemRepo.update(item);
                    if (!add) {
                        connection.rollback();
                        return false;
                    }
                }

                connection.commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                connection.rollback();
                return false;
            } finally {
                connection.setAutoCommit(true);
                connection.close();
            }
        }catch (Exception s){
            s.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(OrderDTO orderDTO) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public OrderDTO search(Integer integer) {
        return null;
    }

    @Override
    public List<OrderDTO> getAll() {
        return null;
    }
}
