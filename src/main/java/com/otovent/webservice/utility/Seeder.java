package com.otovent.webservice.utility;

import com.otovent.webservice.entity.*;
import com.otovent.webservice.entity.enums.*;
import com.otovent.webservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class Seeder {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CarsRepository carsRepository;
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

    @PostConstruct
    private void initDataDevelopment(){
        User user = User.builder()
                .email("tes@gmail.com")
                .firstName("tes")
                .lastName("ting")
                .password("tes")
                .username("tes")
                .role(Role.MEMBER)
                .status(StatusEntity.ACTIVE)
                .build();
        userRepository.save(user);

        User user2 = User.builder()
                .email("admin@gmail.com")
                .firstName("admin")
                .lastName("admin")
                .password("admin")
                .username("admin")
                .role(Role.MEMBER)
                .status(StatusEntity.ACTIVE)
                .build();
        userRepository.save(user2);

        User user3 = User.builder()
                .email("budi@gmail.com")
                .firstName("Budi")
                .lastName("Darmayanto")
                .password("budi")
                .username("darma")
                .role(Role.MEMBER)
                .status(StatusEntity.ACTIVE)
                .build();
        userRepository.save(user3);

        User user4 = User.builder()
                .email("adam@gmail.com")
                .firstName("Adam")
                .lastName("Marsono")
                .password("adam")
                .username("marsono")
                .role(Role.MEMBER)
                .status(StatusEntity.ACTIVE)
                .build();
        userRepository.save(user4);

        User user5 = User.builder()
                .email("yunita@gmail.com")
                .firstName("yunita")
                .lastName("simaranti")
                .password("yunitra")
                .username("simaranti")
                .role(Role.MEMBER)
                .status(StatusEntity.ACTIVE)
                .build();
        userRepository.save(user5);

        Friends friendship1 = Friends.builder()
                .status(StatusEntity.ACTIVE)
                .user(user)
                .friend(user2)
                .dateFriend(new Date())
                .friendshipStatus(FriendshipStatus.ACCEPTED)
                .build();
        friendRepository.save(friendship1);

        Posts post = Posts.builder()
                .user(user)
                .status(StatusEntity.ACTIVE)
                .createdDate(new Date())
                .description("Ini Post Testing")
                .build();
        postRepository.save(post);

        Comments comment = Comments.builder()
                .user(user)
                .status(StatusEntity.ACTIVE)
                .post(post)
                .commentDetail("kwoadkaodkwadkwadodwkad")
                .createdDate(new Date())
                .build();
        commentRepository.save(comment);

        Comments comment2 = Comments.builder()
                .user(user)
                .status(StatusEntity.ACTIVE)
                .post(post)
                .commentDetail("HHAHAHAHAHAHAHAHAHHAHAHAHAHA")
                .createdDate(new Date())
                .build();
        commentRepository.save(comment2);

        Notification notification = Notification.builder()
                .notificationDependency(NotificationDependency.COMMENT)
                .user(user)
                .statusNotification(StatusNotification.NEW)
                .date(new Date())
                .idCommentLike(1L)
                .build();
        notificationRepository.save(notification);

        Notification notification2 = Notification.builder()
                .notificationDependency(NotificationDependency.COMMENT)
                .user(user)
                .statusNotification(StatusNotification.NEW)
                .date(new Date())
                .idCommentLike(2L)
                .build();
        notificationRepository.save(notification2);

        Cars car = Cars.builder()
                .description("Ini DataSeeder")
                .user(user)
                .status(StatusEntity.ACTIVE)
                .name("Mobil Mobilan")
                .build();
        carsRepository.save(car);
    }
}
