package net.codejava.orderfoodspring.Doan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.codejava.orderfoodspring.Response.DoanRes;

@Repository
public interface DoanRepository extends JpaRepository<Doan, Integer>{
    
    @Query(value = "Select * from doan order by RAND() limit ?1", nativeQuery = true)
    List<Doan> listsRand(int num);

    @Query(value = "Select * from doan limit ?1", nativeQuery = true)
    List<Doan> listsLimit(int num);

    @Query(value = "Select * from doan where UPPER(doan.tenmon) LIKE UPPER('%' || ?1 || '%')", nativeQuery = true)
    List<Doan> listsSearch(String name);

    @Query(value = "Select * from doan where maloai = ?2 and tenmon LIKE '%' || ?1 || '%' ", nativeQuery = true)
    List<Doan> listsSearchFillter(String name, int idtype);

    @Query(value = "Select * from doan where maloai = ?2 limit ?1", nativeQuery = true)
    List<Doan> listsFillterType(int num, int idtype);

    @Query(value = "Select * from doan where maloai = ?1", nativeQuery = true)
    List<Doan> listsFillterTypeAll(int idtype);

    @Query(value = "select tenmon, gia, anh, trangthai from doan", nativeQuery = true)
    List<DoanRes> listView();

    List<Doan> findByTenmonContaining(String tenmon);
}