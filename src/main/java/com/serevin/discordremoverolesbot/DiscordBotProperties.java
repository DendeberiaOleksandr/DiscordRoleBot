package com.serevin.discordremoverolesbot;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscordBotProperties {

    @Getter
    @Setter
    @Value(value = "${discord.bot.token}")
    private String token;

}
