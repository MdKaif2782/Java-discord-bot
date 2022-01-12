import Banking.*;

import Commands.APIcommands.NekoLife.NekoHelp;
import Commands.APIcommands.NekoLife.NekoLifeMention;
import Commands.APIcommands.NekoLife.NekosDotLife;
import Commands.APIcommands.NekoLife.nsfwNekoLIfeMentions;
import Commands.APIcommands.memeAPI;
import Commands.admins.Help;
import Commands.admins.dlt;
import Commands.admins.kick;
import Commands.admins.msgdelete;
import Commands.baiscCommands.PingPong;
import Commands.baiscCommands.Rate;
import Commands.domath.DoMath;
import Commands.Reader.Reader;

import Commands.funCommands.*;
import notForPublic.emblem;
import notForPublic.getMentionTag;
import notForPublic.gettingInvite;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.user.UserStatus;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class JavaCordBot {


    public static void main(String[] args) {
        DiscordApi Bot = new DiscordApiBuilder()
        .setToken(System.getenv("VAR1"))
        .login().join();

        Bot.updateActivity(ActivityType.LISTENING, "helicopter helicopter");
        Bot.updateStatus(UserStatus.IDLE);

        Bot.addListener(new PingPong());
        Bot.addListener(new DoMath());
        Bot.addListener(new Rate());
        Bot.addListener(new Reader());
        Bot.addListener(new emblem());
        Bot.addListener(new nosimp());
        Bot.addListener(new height());
        Bot.addListener(new gay());
        Bot.addListener(new urmom());
        Bot.addListener(new msgdelete());
        Bot.addListener(new yomama());
        Bot.addListener(new yodaddy());
        Bot.addListener(new roast());
        Bot.addListener(new BankHelp());
        Bot.addListener(new getMentionTag());
        Bot.addListener(new dlt());
        Bot.addListener(new Help());
        Bot.addListener(new bankRegister());
        Bot.addListener(new BankBalance());
        Bot.addListener(new BalanceAdd());
        Bot.addListener(new jsonbal());
        Bot.addListener(new leadboard());
        Bot.addListener(new gettingInvite());
        Bot.addListener(new ServerRegister());
        Bot.addListener(new memeAPI());
        Bot.addListener(new NekosDotLife());
        Bot.addListener(new NekoHelp());
        Bot.addListener(new NekoLifeMention());
        Bot.addListener(new nsfwNekoLIfeMentions());
        Bot.addListener(new kick());


        System.out.println("Bot is online! Owner:Md_kaif#3392");
    }
}
