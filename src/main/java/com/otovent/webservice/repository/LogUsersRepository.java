package com.otovent.webservice.repository;

import com.otovent.webservice.entity.logs.LogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LogUsersRepository extends JpaRepository<LogUser,Long>{
    List<LogUser> findAllByTimeOrderByTimeDesc(Date time);
}
