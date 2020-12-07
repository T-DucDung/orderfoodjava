package net.codejava.orderfoodspring.Trangthaidonhang;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrangthaidonhangRepository extends JpaRepository<Trangthaidonhang, KeyT>{}