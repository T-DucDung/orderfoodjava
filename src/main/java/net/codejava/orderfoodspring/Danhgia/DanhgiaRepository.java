package net.codejava.orderfoodspring.Danhgia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.codejava.orderfoodspring.Response.DanhgiaRes;

@Repository
public interface DanhgiaRepository extends JpaRepository<Danhgia, Integer> {

    @Query(value = "select users.username as ten, danhgia.noidung, danhgia.ngaytao from danhgia join users on users.id = danhgia.id where danhgia.mamon = ?1 order by danhgia.ngaytao DESC  ", nativeQuery = true)
    List<DanhgiaRes> lists(int mamon);
}
