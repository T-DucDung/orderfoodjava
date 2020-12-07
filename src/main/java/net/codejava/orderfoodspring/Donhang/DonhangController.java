package net.codejava.orderfoodspring.Donhang;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import net.codejava.orderfoodspring.Auth.models.User;
import net.codejava.orderfoodspring.Auth.security.jwt.JwtUtils;
import net.codejava.orderfoodspring.Auth.security.services.UserDetailsServiceImpl;
import net.codejava.orderfoodspring.Datdoan.Datdoan;
import net.codejava.orderfoodspring.Datdoan.DatdoanService;
import net.codejava.orderfoodspring.Datdoan.Key;
import net.codejava.orderfoodspring.Doan.DoanService;
import net.codejava.orderfoodspring.Request.DatdoanReq;
// import net.codejava.orderfoodspring.Datdoan.Datdoan;
// import net.codejava.orderfoodspring.Doan.Doan;
import net.codejava.orderfoodspring.Request.DonhangReq;
import net.codejava.orderfoodspring.Response.DonhangRes;
import net.codejava.orderfoodspring.Trangthaidonhang.KeyT;
import net.codejava.orderfoodspring.Trangthaidonhang.Trangthaidonhang;
// import net.codejava.orderfoodspring.Trangthaidonhang.Trangthaidonhang;
import net.codejava.orderfoodspring.Trangthaidonhang.TrangthaidonhangService;

// import javassist.NotFoundException;

@RestController
public class DonhangController {
    
    @Autowired
    private DonhangService service;

    @Autowired
    private DatdoanService serviceDat;

    @Autowired
    private DoanService serviceDoa;

    @Autowired
    private TrangthaidonhangService serviceTt;

    @Autowired
    private UserDetailsServiceImpl serviceU;

    @Value("${bezkoder.app.jwtSecret}")
	private String jwtSecret;

    @PostMapping("/orders")
    public void add(@RequestBody DonhangReq res, @RequestHeader Map<String, String> headers) {
        String[] arrOfStr = headers.get("authorization").split(" "); 
        // System.out.println(arrOfStr[1]);
        // System.out.println(Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(arrOfStr[1]).getBody().get("sub", String.class));
        User u = serviceU.getbyname(Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(arrOfStr[1]).getBody().get("sub", String.class)).get();
        // System.out.println(u.toString());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Long time = timestamp.getTime();
        Donhang donhang = new Donhang(u.getId(),res.getTenkhach(),res.getSdt(),0L,res.getGhichu(),res.getDiachinhan(),0L,time);
        Donhang donhangIns = service.save(donhang);

        Integer ma = donhangIns.getMadon();

        Long tong = 0L;
        serviceTt.save(new Trangthaidonhang(new KeyT(8,ma),"", time));
        for (DatdoanReq item : res.getDoan()) {
            Long temp = serviceDoa.gia(item.getMamon()) * item.getsoluong();
            Datdoan datdoan = new Datdoan(
                new Key(ma, item.getMamon()),
                item.getsoluong(),
                temp
            );
            tong += temp;
            serviceDat.save(datdoan);
        }
        service.setTongtien(tong, ma);
    }

    @GetMapping("/orders")
    @PreAuthorize("hasRole('USER')")
    public List<DonhangRes> lists(@RequestHeader Map<String, String> headers){
        String[] arrOfStr = headers.get("authorization").split(" "); 
        System.out.println(Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(arrOfStr[1]).getBody().get("sub", String.class));
        return service.getall();
    }

    // @GetMapping("/orders/{id}")
    // public ResponseEntity<?> get(@PathVariable Integer id){
    //     boolean check = service.exists(id);
    //     if (check){
    //         Donhang or = service.get(id);
    //         return new ResponseEntity<Donhang>(or, HttpStatus.OK);
    //     }else{
    //         return null;
    //     }
    // }

    @PutMapping("/orders/{id}")
    public ResponseEntity<?> update(@RequestBody Donhang donhang, @PathVariable Integer id) {
        boolean check = service.exists(id);
        if (check){
            service.save(donhang);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }  
    }
}
