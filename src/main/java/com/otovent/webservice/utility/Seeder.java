package com.otovent.webservice.utility;

import com.otovent.webservice.entity.Cars;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.Role;
import com.otovent.webservice.entity.enums.StatusEntity;
import com.otovent.webservice.repository.CarsRepository;
import com.otovent.webservice.repository.UserRepository;
import com.otovent.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Seeder {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CarsRepository carsRepository;

    @PostConstruct
    private void initDataDevelopment(){
        User user = User.builder()
                .email("tes@gmail.com")
                .firstName("tes")
                .lastName("ting")
                .password("tes")
                .username("tes")
                .role(Role.MEMBER)
                .build();
        userRepository.save(user);

        Cars car = Cars.builder()
                .description("Ini DataSeeder")
                .user(user)
                .status(StatusEntity.ABLE)
                .name("Mobil Mobilan")
                .build();
        carsRepository.save(car);
    }
}
