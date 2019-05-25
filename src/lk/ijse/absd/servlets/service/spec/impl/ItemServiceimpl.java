package lk.ijse.absd.servlets.service.spec.impl;

import lk.ijse.absd.servlets.dto.ItemDTO;
import lk.ijse.absd.servlets.entity.Item;
import lk.ijse.absd.servlets.repository.other.RepoFactory;
import lk.ijse.absd.servlets.repository.spec.ItemRepo;
import lk.ijse.absd.servlets.service.spec.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ItemServiceimpl implements ItemService  {

    private ItemRepo itemRepo;
    private ModelMapper modelMapper;

    public ItemServiceimpl() {
        this.itemRepo=new RepoFactory().getRepo(RepoFactory.RepoTypes.ITEM);
        this.modelMapper=new ModelMapper();
    }

    @Override
    public boolean add(ItemDTO itemDTO) {
        try {
            Item item = modelMapper.map(itemDTO, Item.class);
            return itemRepo.add(item);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean update(ItemDTO itemDTO) {
        try {
            Item item = modelMapper.map(itemDTO, Item.class);
            return itemRepo.update(item);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer s) {
        try {
            return itemRepo.delete(s);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ItemDTO search(Integer s) {
        try {
            Item item = itemRepo.search(s);
            if(item!=null){
                return modelMapper.map(item,ItemDTO.class);
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<ItemDTO> getAll() {
        try {
            List<Item> all = itemRepo.getAll();
            Type listType= new TypeToken<List<ItemDTO>>(){}.getType();
            return modelMapper.map(all,listType);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
