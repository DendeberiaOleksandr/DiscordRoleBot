package com.serevin.discordremoverolesbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DiscordBotConfiguration {

    @Bean
    public JDA jda(DiscordBotProperties discordBotProperties){
        return JDABuilder.createDefault(discordBotProperties.getToken())
                .setActivity(Activity.playing("маму дусика"))
                .build();
    }

    @Bean
    public List<Long> rolesToSave(){
        return List.of(
                907227394862116894L,
                907228551378513981L,
                975768275751419995L
        );
    }

}
