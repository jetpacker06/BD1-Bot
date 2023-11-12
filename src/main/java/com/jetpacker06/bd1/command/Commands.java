package com.jetpacker06.bd1.command;

import com.jetpacker06.bd1.BD1;
import com.jetpacker06.bd1.command.commands.Command;
import com.jetpacker06.bd1.util.entity.entities.Channels;
import com.jetpacker06.bd1.util.entity.entities.UserIDs;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Set;

public class Commands extends ListenerAdapter {
    public static HashMap<String, Command> commands = new HashMap<>();
    @Override
    public void onGenericEvent(@NotNull GenericEvent event) {
        BD1.recentEvent = event;
        if (event instanceof SlashCommandInteractionEvent) {
            BD1.recentCommandEvent = (SlashCommandInteractionEvent) event;
        }
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String name = event.getName();
        if (!commands.containsKey(name)) return;

        Command command = commands.get(name);
        if (command.forFriendsOnly() & CommandUtil.notInCorrectServer(event)) {
            command.executeForFriendsOnly(event);
        }
        else {
            command.execute(event);
        }
    }

    @Override
    public void onGuildVoiceJoin(@NotNull GuildVoiceJoinEvent event) {
        ensureAllowedInChannel(event.getMember(), event.getChannelJoined(), event.getGuild());
    }

    @Override
    public void onGuildVoiceMove(@NotNull GuildVoiceMoveEvent event) {
        ensureAllowedInChannel(event.getMember(), event.getChannelJoined(), event.getGuild());
    }
    private static void ensureAllowedInChannel(Member member, AudioChannel channel, Guild guild) {
        if (member.getIdLong() == UserIDs.cody & channel == Channels.testNoCody) {
            guild.kickVoiceMember(member).queue();
        }
        if (member.getIdLong() == UserIDs.lawrence & channel == Channels.noLawrence) {
            guild.kickVoiceMember(member).queue();
        }
    }
}