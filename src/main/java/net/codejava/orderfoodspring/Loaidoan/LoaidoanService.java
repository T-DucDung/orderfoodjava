package net.codejava.orderfoodspring.Loaidoan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoaidoanService {

    @Autowired
    private LoaidoanRepository repo;

    public List<Loaidoan> listAll(){
        return repo.findAll();
    }

    public void save(Loaidoan loaidoan) {
        repo.save(loaidoan);
    }

    public Loaidoan get(int id) {
        return repo.findById(id).get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public boolean exists(Integer id){
        return repo.existsById(id);
    }
}