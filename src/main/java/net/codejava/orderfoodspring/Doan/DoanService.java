package net.codejava.orderfoodspring.Doan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.orderfoodspring.Response.DoanRes;

@Service
public class DoanService {
    
    @Autowired
    private DoanRepository repo;

    public List<Doan> listAll(){
        return repo.findAll();
    }

    public void save(Doan food){
        repo.save(food);
    }

    public Doan get(Integer id){
        return repo.findById(id).get();
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }

    public boolean exists(Integer id){
        return repo.existsById(id);
    }

    public List<Doan> lists(int num){
        return repo.listsLimit(num);
    }

    public List<Doan> listsRand(int num){
        return repo.listsRand(num);
    }

    public List<Doan> listsSearch(String name){
        System.out.println(name);
        return repo.findByTenmonContaining(name);
    }

    public List<Doan> listsSearchFillter(String name, int idtype){
        System.out.println("name : "+name+" idtype : "+idtype);
        return repo.listsSearchFillter(name, idtype);
    }

    public List<Doan> listsFillerType(int num, int idtype){
        return repo.listsFillterType(num, idtype);
    }

    public List<Doan> listsFillerTypeAll(int idtype){
        return repo.listsFillterTypeAll(idtype);
    }
    
    public List<DoanRes> listView(){
        return repo.listView();
    }

    public Long gia(Integer mamon){
        return repo.findById(mamon).get().getGia();
    }
}
