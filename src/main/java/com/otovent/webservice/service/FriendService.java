package com.otovent.webservice.service;

import com.otovent.webservice.entity.Friends;
import com.otovent.webservice.entity.User;

import java.util.List;

public interface FriendService {
    List<Friends> getAllFriendByUser(Long id);
    void addFriend(User requester,User target);
    void deleteFriend(Long id);
}
