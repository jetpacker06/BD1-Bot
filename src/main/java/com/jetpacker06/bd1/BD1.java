package com.jetpacker06.bd1;

import com.jetpacker06.bd1.command.SlashCommandRegistry;
import com.jetpacker06.bd1.event.MiscEvents;
import com.jetpacker06.bd1.event.SlashCommandEvents;
import com.jetpacker06.bd1.terminal.Terminal;
import com.jetpacker06.bd1.util.entity.entities.Channels;
import com.jetpacker06.bd1.util.entity.entities.Guilds;
import com.jetpacker06.bd1.util.entity.entities.Roles;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class BD1 {
    public static GenericEvent recentEvent;
    public static SlashCommandInteractionEvent recentCommandEvent;
    public static MessageReceivedEvent recentMessageEvent;

    public static final String BOT_KEY = System.getenv("BD1KEY");
    public static JDA jda;

    public static void print(Object message) {
        System.out.println(message);
    }

    public static void main(String[] args) throws InterruptedException {
        print("Booting JDA!");
        jda = JDABuilder.createDefault(BOT_KEY)
        .setActivity(Activity.watching("Watching closely."))
        .enableIntents(GatewayIntent.MESSAGE_CONTENT)
        .enableIntents(GatewayIntent.GUILD_MEMBERS)
        .setStatus(OnlineStatus.ONLINE)
        .addEventListeners(new MiscEvents(), new SlashCommandEvents())
        .build()
        .awaitReady();

        print("JDA Booted!");

        initSnowflakes();

        SlashCommandRegistry.registerSlashCommands();
        Terminal.startTerminal();
    }

    public static void initSnowflakes() {
        Guilds.jetpackHub = jda.getGuildById(871409050808643594L);
        Guilds.testServer = jda.getGuildById(945662624224382998L);

        Channels.jgeneral = jda.getTextChannelById(1076725461054402570L);

        Channels.announcements = jda.getTextChannelById(961816766923821076L);
        Channels.botcmds_jetpackhub = jda.getTextChannelById(1557256539486814238L);
        Channels.showcase = jda.getTextChannelById(1211090000268234853L);
    }
}