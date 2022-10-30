package com.serevin.discordremoverolesbot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.UserSnowflake;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class DiscordBotLoop {

    private final JDA jda;

    private final List<Long> rolesToSave;

    @Scheduled(fixedRate = 60000)
    public void loop() {

        log.info("Started changing roles");

        Guild guild = jda.getGuildById(907227298246299728L);

        if (guild != null) {

            log.info("Guild found!");

            Member memberSerevin = guild.retrieveMemberById(334630360291999745L).complete();

            if (memberSerevin != null) {

                log.info("User found!");

                List<Role> rolesToSaveList = rolesToSave.stream()
                        .map(guild::getRoleById)
                        .collect(Collectors.toList());

                List<Role> serevinRoles = memberSerevin.getRoles();

                for (Role serevinRole : serevinRoles){

                    if (serevinRole != null){

                        boolean contains = rolesToSaveList.contains(serevinRole);

                        if (!contains){
                            guild.removeRoleFromMember(memberSerevin, serevinRole).complete();
                            log.info("Removed role: {}", serevinRole.getName());
                        }

                    }

                }

                log.info("Roles are changed!");

            }

        }

    }
}
