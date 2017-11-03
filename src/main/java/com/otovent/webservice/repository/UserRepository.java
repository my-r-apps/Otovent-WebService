package com.otovent.webservice.repository;

import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findOneByUsernameAndPasswordAndStatus(String username, String password, StatusEntity status);
    User findOneByEmailAndPasswordAndStatus(String email,String password, StatusEntity status);
}
