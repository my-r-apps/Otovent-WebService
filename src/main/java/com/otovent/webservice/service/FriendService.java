package com.otovent.webservice.service;

import com.otovent.webservice.entity.Friends;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.request.FriendRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FriendService {
    Friends getOne(Long id);
    Page<Friends> getAllFriendByUser(Long id, Pageable pageable);
    Boolean addFriend(FriendRequest friendRequest);
    Boolean deleteFriend(Long id);
}
