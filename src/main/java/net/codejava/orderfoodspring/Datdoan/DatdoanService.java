package net.codejava.orderfoodspring.Datdoan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatdoanService {
    @Autowired
    private DatdoanRepository repo;

    public void save(Datdoan datdoan) {
        repo.save(datdoan);
    } 
}
