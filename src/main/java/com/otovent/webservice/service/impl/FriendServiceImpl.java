package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.Friends;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.StatusEntity;
import com.otovent.webservice.entity.request.FriendRequest;
import com.otovent.webservice.repository.FriendRepository;
import com.otovent.webservice.service.FriendService;
import com.otovent.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService{
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    UserService userService;

    @Override
    public Friends getOne(Long id) {
        return friendRepository.getOne(id);
    }

    @Override
    public Page<Friends> getAllFriendByUser(Long id, Pageable pageable) {
        User userRequested = userService.getDetailOneUser(id);
        return friendRepository.findAllByUser(userRequested,pageable);
    }

    @Override
    public Boolean addFriend(FriendRequest friendRequest) {
        User userRequested = userService.getDetailOneUser(friendRequest.getUser());
        User userTarget = userService.getDetailOneUser(friendRequest.getFriend());
        Friends existingFriendship = friendRepository.findByUserAndFriend(userRequested,userTarget);
        if (existingFriendship != null) {
            existingFriendship.setStatus(StatusEntity.ACTIVE);
            existingFriendship.setDateFriend(new Date());
            friendRepository.save(existingFriendship);
            return Boolean.TRUE;
        } else {
            Friends friend = Friends.builder()
                    .dateFriend(new Date())
                    .friend(userTarget)
                    .user(userRequested)
                    .status(StatusEntity.ACTIVE)
                    .build();
            friendRepository.save(friend);
            return Boolean.TRUE;
        }
    }

    @Override
    public Boolean deleteFriend(Long id) {
        Friends friendship = friendRepository.findOne(id);
        friendship.setStatus(StatusEntity.DELETED);
        friendRepository.save(friendship);
        return Boolean.TRUE;
    }
}
