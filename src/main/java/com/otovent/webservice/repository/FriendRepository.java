package com.otovent.webservice.repository;

import com.otovent.webservice.entity.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friends,Long> {
}
