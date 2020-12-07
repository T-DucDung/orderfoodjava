package net.codejava.orderfoodspring.Trangthaidonhang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrangthaidonhangService {
    @Autowired
    private TrangthaidonhangRepository repo;

    public void save(Trangthaidonhang trangthaidonhang){
        repo.save(trangthaidonhang);
    }
}
