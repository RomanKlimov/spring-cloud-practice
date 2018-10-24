package ru.kpfu.itis.web.service.impl;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.web.config.property.MessagingProperties;
import ru.kpfu.itis.web.dto.CatDto;
import ru.kpfu.itis.web.dto.UserDto;

@EnableRabbit
@Service
public class MessageService {
//
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessagingProperties messagingProperties;

//    public void getImage(String userId) {
//        System.out.println();
//        CatDto dto = CatDto.builder()
//                .id(userId)
//                .build();
//        rabbitTemplate.convertAndSend(messagingProperties.getExchange(),
//                messagingProperties.getCatCreation().getRoutingKey(), dto);
//    }

//    @RabbitListener(queues = "${messaging.cat-reply.queue}")
//    public void sendImage(CatDto catDto) {
//        String id = catDto.getId();
//        System.out.println(catDto.getUrl());
//        messagingTemplate.convertAndSend("/topic/image/" + id + "/reply", catDto.getUrl());
//    }

    public void registerUser(UserDto userDto) {
        System.out.println(messagingProperties.getExchange());
        System.out.println(messagingProperties.getUser().getRoutingKey());
        rabbitTemplate.convertAndSend(messagingProperties.getExchange(),
                messagingProperties.getUser().getRoutingKey(), userDto);
    }
}
