package net.codejava.orderfoodspring.Datdoan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatdoanRepository extends JpaRepository<Datdoan, Key>{}