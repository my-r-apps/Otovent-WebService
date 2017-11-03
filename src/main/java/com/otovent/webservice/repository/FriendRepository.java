package com.otovent.webservice.repository;

import com.otovent.webservice.entity.Friends;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.StatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friends,Long> {
    Page<Friends> findAllByUserAndStatus(User user, Pageable pageable, StatusEntity status);
    Friends findByUserAndFriend(User user, User friend, StatusEntity status);
}
