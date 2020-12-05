package net.codejava.orderfoodspring.Donhang;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.orderfoodspring.Response.DonhangRes;

@Service
public class DonhangService {
    
    @Autowired
    private DonhangRepository repo;

    public List<Donhang> listAll(){
        return repo.findAll();
    }

    public void save(Donhang donHang){
        repo.save(donHang);
    }

    public Donhang get(Integer id){
        return repo.findById(id).get();
    }

    public boolean exists(Integer id){
        return repo.existsById(id);
    }
    public List<DonhangRes> getall(){
        return repo.lists();
    }
}
