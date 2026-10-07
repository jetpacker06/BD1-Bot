package com.jetpacker06.bd1.event;

import com.jetpacker06.bd1.BD1;
import com.jetpacker06.bd1.util.entity.entities.*;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MiscEvents extends ListenerAdapter {
    @Override
    public void onGenericEvent(@NotNull GenericEvent event) {
        BD1.recentEvent = event;
        if (event instanceof SlashCommandInteractionEvent) {
            BD1.recentCommandEvent = (SlashCommandInteractionEvent) event;
        } else if (event instanceof MessageReceivedEvent) {
            BD1.recentMessageEvent = (MessageReceivedEvent) event;
        }
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        long guild = event.getGuild().getIdLong();
         if (guild == Guilds.jetpackHub.getIdLong()) {
            if (Guilds.jetpackHub.getMemberCount() == 1_000) {
                Channels.announcements.sendMessage("Congratulations " + event.getMember().getAsMention() + "on being the 1,000th member!").queue();
            }
        } else if (guild == Guilds.jetpackHub.getIdLong()) {
            if (Guilds.jetpackHub.getMemberCount() == 2_000) {
                Channels.announcements.sendMessage("Congratulations " + event.getMember().getAsMention() + "on being the 2,000th member!").queue();
            }
        } else if (guild == Guilds.jetpackHub.getIdLong()) {
            if (Guilds.jetpackHub.getMemberCount() == 5_000) {
                Channels.announcements.sendMessage("Congratulations " + event.getMember().getAsMention() + "on being the 5,000th member!").queue();
            }
        } else if (guild == Guilds.jetpackHub.getIdLong()) {
            if (Guilds.jetpackHub.getMemberCount() == 10_000) {
                Channels.announcements.sendMessage("Congratulations " + event.getMember().getAsMention() + "on being the 10,000th member!").queue();
            }
        }
    }

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {
     //   RoleMenuCommand.onRoleCommandInteraction(event);
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        // if in Showcase channel and has an attachment, send a thumbs up
        if (event.isFromGuild() && event.getChannel().getIdLong() == Channels.showcase.getIdLong() && !event.getMessage().getAttachments().isEmpty()) {
            event.getMessage().addReaction(Emojis.THUMBS_UP).queue();
        }
    }
}