package lk.ijse.absd.servlets.service.spec.impl;

import lk.ijse.absd.servlets.dto.CustomerDTO;
import lk.ijse.absd.servlets.entity.Customer;
import lk.ijse.absd.servlets.repository.other.RepoFactory;
import lk.ijse.absd.servlets.repository.spec.CustomerRepo;
import lk.ijse.absd.servlets.service.spec.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceimpl implements CustomerService {
    private RepoFactory repoFactory;
    private CustomerRepo customerRepo;
    private ModelMapper modelMapper;

    public CustomerServiceimpl() {
        this.repoFactory=new RepoFactory();
        this.customerRepo=repoFactory.getRepo(RepoFactory.RepoTypes.CUSTOMER);
        this.modelMapper=new ModelMapper();
    }

    @Override
    public boolean add(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        try {
            return customerRepo.add(customer);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean update(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        try {
            return customerRepo.update(customer);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer cid) {
        try {
            return customerRepo.delete(cid);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public CustomerDTO search(Integer cid) {

        try {
            Customer customer = customerRepo.search(cid);
            if(customer==null){
                return null;
            }
            return modelMapper.map(customer, CustomerDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<CustomerDTO> getAll() {
        try {
            List<Customer> all = customerRepo.getAll();
            Type listType= new TypeToken<List<CustomerDTO>>(){}.getType();
            return modelMapper.map(all,listType);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
