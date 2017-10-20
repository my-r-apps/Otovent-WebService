package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.Friends;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.service.FriendService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService{
    @Override
    public List<Friends> getAllFriendByUser(Long id) {
        return null;
    }

    @Override
    public void addFriend(User requester, User target) {

    }

    @Override
    public void deleteFriend(Long id) {

    }
}
