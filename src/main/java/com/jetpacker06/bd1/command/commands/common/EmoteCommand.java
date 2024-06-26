package com.jetpacker06.bd1.command.commands.common;

import com.jetpacker06.bd1.command.commands.Command;
import com.jetpacker06.bd1.util.Util;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class EmoteCommand extends Command {

    @Override
    public String getName() {
        return "emote";
    }
    @Override
    public String getDescription() {
        return "Do an emote.";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.replyEmbeds(Util.createImageEmbed(Util.randomFromArray(emotes_list))).queue();
    }

    public static String[] emotes_list = {
            "https://c.tenor.com/awnbsntmYkoAAAAd/fortnite-dance-fortnite-emote.gif",
            "https://c.tenor.com/vjDSipHhsjAAAAAd/fortnite-snake-eyes.gif",
            "https://thumbs.gfycat.com/AmazingViciousElephantbeetle-size_restricted.gif",
            "https://i.gifer.com/fy2C.gif",
            "https://www.icegif.com/wp-content/uploads/fortnite-icegif-14.gif",
            "https://media4.giphy.com/media/SG5paY6WxH6Ki2lWys/200.gif",
            "https://cdn.mobilesyrup.com/wp-content/uploads/2020/02/fortnite-rickroll-emote.gif",
            "https://tenor.com/view/fortnite-snakepit-bhangra-boogie-fortnite-dance-fortnite-emote-gif-23704159"
    };
}
