package ru.kpfu.itis.cat.service.impl;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.cat.config.property.MessagingProperties;
import ru.kpfu.itis.cat.dto.CatDto;
import ru.kpfu.itis.cat.service.CatService;

@Component
@EnableRabbit
public class MessageService {

    @Autowired
    private CatService catService;
    @Autowired
    private MessagingProperties messagingProperties;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "${messaging.cat-creation.queue}")
    public void process(CatDto dto) {
        CatDto catDto = catService.getCat()
                .orElseThrow(IllegalArgumentException::new);
        catDto.setId(dto.getId());
        System.out.println(messagingProperties.getExchange());
        System.out.println(messagingProperties.getCatReply().getRoutingKey());
        System.out.println(catDto.toString());

        rabbitTemplate.convertAndSend(messagingProperties.getExchange(),
                messagingProperties.getCatReply().getRoutingKey(), catDto);
    }
}
