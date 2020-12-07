package net.codejava.orderfoodspring.Loaidoan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoaidoanController {

    @Autowired
    private LoaidoanService service;

    @PostMapping("/types")
    public void add(@RequestBody Loaidoan loaidoan) {
        service.save(loaidoan);
    }

    @GetMapping("/types")
    public List<Loaidoan> list() {
        return service.listAll();
    }

    @GetMapping("/types/{maloai}")
    public ResponseEntity<Loaidoan> get(@PathVariable Integer maloai) {
        boolean check = service.exists(maloai);
        if (check) {
            Loaidoan lda = service.get(maloai);
            return new ResponseEntity<Loaidoan>(lda, HttpStatus.OK);
        } else {
            return new ResponseEntity<Loaidoan>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/types/{maloai}")
    public ResponseEntity<?> update(@RequestBody Loaidoan lda, @PathVariable Integer maloai) {
        boolean check = service.exists(maloai);
        if (check) {
            service.save(lda);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/types/{maloai}")
    public void delete(@PathVariable Integer maloai) {
        service.delete(maloai);
    }
}
