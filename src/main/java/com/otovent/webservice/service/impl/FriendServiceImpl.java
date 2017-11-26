package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.Friends;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.FriendshipStatus;
import com.otovent.webservice.entity.enums.NotificationDependency;
import com.otovent.webservice.entity.enums.StatusEntity;
import com.otovent.webservice.entity.enums.StatusNotification;
import com.otovent.webservice.entity.request.FriendRequest;
import com.otovent.webservice.entity.request.NotificationRequest;
import com.otovent.webservice.repository.FriendRepository;
import com.otovent.webservice.service.FriendService;
import com.otovent.webservice.service.NotificationService;
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
    @Autowired
    NotificationService notificationService;

    @Override
    public Friends getOne(Long id) {
        return friendRepository.findOne(id);
    }

    @Override
    public Page<Friends> getAllFriendByUser(Long id, Pageable pageable) {
        User userRequested = userService.getDetailOneUser(id);
        return friendRepository.findAllByUserAndStatus(userRequested,pageable,StatusEntity.ACTIVE);
    }

    @Override
    public Boolean addFriend(FriendRequest friendRequest) {
        User userRequested = userService.getDetailOneUser(friendRequest.getUser());
        User userTarget = userService.getDetailOneUser(friendRequest.getFriend());
        Friends existingFriendship = friendRepository.findByUserAndFriendAndStatus(userRequested,userTarget,StatusEntity.ACTIVE);
        if (existingFriendship != null) {
            existingFriendship.setStatus(StatusEntity.ACTIVE);
            existingFriendship.setDateFriend(new Date());
            existingFriendship.setFriendshipStatus(FriendshipStatus.TO_CONFIRM);
            friendRepository.save(existingFriendship);
            Long idFriendship = friendRepository.findByUserAndFriendAndStatus(userRequested,userTarget,StatusEntity.ACTIVE).getId();
            pushNotifFriendship(idFriendship,userTarget.getId());
            return Boolean.TRUE;
        } else {
            Friends friend = Friends.builder()
                    .dateFriend(new Date())
                    .friend(userTarget)
                    .user(userRequested)
                    .friendshipStatus(FriendshipStatus.TO_CONFIRM)
                    .status(StatusEntity.ACTIVE)
                    .build();
            friendRepository.save(friend);
            Long idFriendship = friendRepository.findByUserAndFriendAndStatus(userRequested,userTarget,StatusEntity.ACTIVE).getId();
            pushNotifFriendship(idFriendship,userTarget.getId());
            return Boolean.TRUE;
        }
    }

    private void pushNotifFriendship(Long idFriendship, Long idFriend){
        NotificationRequest notificationRequest =
                NotificationRequest.builder()
                        .user(idFriend)
                        .idPostEvent(idFriendship)
                        .statusNotification(StatusNotification.NEW)
                        .notificationDependency(NotificationDependency.FRIEND_REQUEST)
                        .date(new Date())
                        .build();
        notificationService.addNotification(notificationRequest);
    }

    @Override
    public Boolean deleteFriend(Long id) {
        Friends friendship = friendRepository.findOne(id);
        friendship.setStatus(StatusEntity.ACTIVE );
        friendship.setFriendshipStatus(FriendshipStatus.TO_CONFIRM);
        friendRepository.save(friendship);
        return Boolean.TRUE;
    }

    @Override
    public Boolean confirmRequest(Long id) {
        Friends friendship = friendRepository.findOne(id);
        friendship.setFriendshipStatus(FriendshipStatus.ACCEPTED);
        friendRepository.save(friendship);
        return Boolean.TRUE;
    }

    @Override
    public Boolean rejectRequest(Long id) {
        Friends friendship = friendRepository.findOne(id);
        friendship.setStatus(StatusEntity.DELETED);
        friendship.setFriendshipStatus(FriendshipStatus.REJECTED);
        friendRepository.save(friendship);
        return Boolean.TRUE;
    }

    @Override
    public Friends getOneByFriend(Long idUser,Long idFriend) {
        User user = userService.getDetailOneUser(idUser);
        User friend = userService.getDetailOneUser(idFriend);
        return friendRepository.findByUserAndFriendAndStatus(user,friend,StatusEntity.ACTIVE);
    }
}
