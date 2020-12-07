package net.codejava.orderfoodspring.Doan;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.codejava.orderfoodspring.Loaidoan.LoaidoanService;
import net.codejava.orderfoodspring.Response.DoanRes;

@RestController
public class DoanController {
    @Autowired
    private DoanService service;

    @Autowired
    private LoaidoanService tService;

    // create
    @PostMapping("/foods")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public void add(@RequestBody Doan food) {
        service.save(food);
    }

    // get all 
    @GetMapping("/foods")
    public List<Doan> list(@RequestParam Optional<Integer> num, @RequestParam Optional<Integer> idtype){
        if (!num.isPresent()) {
            if(!idtype.isPresent()){
                return service.listAll();
            }else{
                boolean check = tService.exists(idtype.get());
                if (check){
                    return service.listsFillerTypeAll(idtype.get());
                }else{
                    return service.listAll();
                }
            }
        }else{
            if(!idtype.isPresent()){
                return service.lists(num.get());
            }else{
                boolean check = tService.exists(idtype.get());
                if (check){
                    return service.listsFillerType(num.get(), idtype.get());
                }else{
                    return service.lists(num.get());
                }
            }
        }
    }

    // get list view
    @GetMapping("/foods/view")
    // @PreAuthorize("hasRole('ADMIN')")
    public List<DoanRes> listView(){
        return service.listView();
    }

    // get by id
    @GetMapping("/foods/{id}")
    public ResponseEntity<Doan> get(@PathVariable Integer id){
        boolean check = service.exists(id);
        if (check){
            Doan food = service.get(id);
            return new ResponseEntity<Doan>(food, HttpStatus.OK);
        }else{
            throw new DoanException("food not found!!!");
        }
    }

    // ex
    @ExceptionHandler(DoanException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Food not found")
    public void handlerNilException(DoanException e){
        System.out.println(e.getMessage());  
    }

    // edit by id
    @PutMapping("/foods/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> update(@RequestBody Doan food, @PathVariable Integer id) {
        boolean check = service.exists(id);
        if (check){
            service.save(food);
            return new ResponseEntity<Doan>(food, HttpStatus.OK);
        }else{
            throw new DoanException("food not found!!!");
        }    
    }

    // del by id
    @DeleteMapping("/foods/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    // get list rand 
    @GetMapping("/foods/rand")
    public List<Doan> rand(@RequestParam(required = true) Optional<Integer> num){
        return service.listsRand(num.get());
    }
    
    @GetMapping("/foods/search")
    public List<Doan> search(@RequestParam(required = true) Optional<String> name, @RequestParam Optional<Integer> idtype){
        if (!idtype.isPresent()){
            System.out.println("hihi");
            return service.listsSearch(name.get());
        }else{
            return service.listsSearchFillter(name.get(), idtype.get());
        }  
    }
}
