package ru.kpfu.itis.user.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.user.config.property.MessagingProperties;
import ru.kpfu.itis.user.dto.CatDto;
import ru.kpfu.itis.user.dto.UserDto;
import ru.kpfu.itis.user.model.User;
import ru.kpfu.itis.user.repository.UserRepository;
import ru.kpfu.itis.user.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessagingProperties messagingProperties;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(UserDto userDto) {
        User user = User.builder()
                .imgUrl(userDto.getImgUrl())
                .name(userDto.getName())
                .build();
        userRepository.save(user);
//        Long id = user.getId();
//        CatDto dto = CatDto.builder()
//                .id(id.toString())
//                .build();
//        rabbitTemplate.convertAndSend(messagingProperties.getExchange(),
//                messagingProperties.getCatCreation().getRoutingKey(), dto);

    }
//    @RabbitListener(queues = "${messaging.cat-reply.queue}")
//    public void sendImage(CatDto catDto) {
//        String id = catDto.getId();
//        System.out.println(catDto.getUrl());
//        User user = userRepository.getById(Long.parseLong(id));
//        user.setImgUrl(catDto.getUrl());
//        userRepository.save(user);
//
//    }

}
