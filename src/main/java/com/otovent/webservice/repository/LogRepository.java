package com.otovent.webservice.repository;

import com.otovent.webservice.entity.logs.LogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<LogUser,Long>{
}
