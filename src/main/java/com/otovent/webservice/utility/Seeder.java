package com.otovent.webservice.utility;

import com.otovent.webservice.entity.Cars;
import com.otovent.webservice.entity.Friends;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.Role;
import com.otovent.webservice.entity.enums.StatusEntity;
import com.otovent.webservice.repository.CarsRepository;
import com.otovent.webservice.repository.FriendRepository;
import com.otovent.webservice.repository.UserRepository;
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

        Friends friendship1 = Friends.builder()
                .status(StatusEntity.ACTIVE)
                .user(user)
                .friend(user2)
                .dateFriend(new Date())
                .build();
        friendRepository.save(friendship1);

        Cars car = Cars.builder()
                .description("Ini DataSeeder")
                .user(user)
                .status(StatusEntity.ACTIVE)
                .name("Mobil Mobilan")
                .build();
        carsRepository.save(car);
    }
}
