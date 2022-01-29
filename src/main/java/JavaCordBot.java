

import Banking.sql.Balanceadd;
import Banking.sql.MoneyDistribute;
import Banking.sql.robbing;
import Commands.APIcommands.*;
import Commands.APIcommands.NekoLife.NekoHelp;
import Commands.APIcommands.NekoLife.NekoLifeMention;
import Commands.APIcommands.NekoLife.NekosDotLifeSFW;
import Commands.APIcommands.NekoLife.nsfwNekoLIfeMentions;
import Commands.APIcommands.booru.boorusearch;
import Commands.admins.Help;
import Commands.admins.dlt;
import Commands.admins.kick;
import Commands.admins.msgdelete;
import Commands.baiscCommands.PingPong;
import Commands.baiscCommands.Rate;
import Commands.domath.DoMath;
import Commands.Reader.Reader;

import Commands.funCommands.*;
import notForPublic.*;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.user.UserStatus;


public class JavaCordBot {


    public static void main(String[] args) {

        String token1 = "OTI0NjA4NDc0NDA5NzQyMzU3.";
        String token2 = "YchCvg.A0s-BnJ5_XmULW2GwyAj6mpY058";

        DiscordApi Bot = new DiscordApiBuilder()
        .setToken(token1+token2)
        .login().join();

        Bot.updateActivity(ActivityType.PLAYING, "Adamjee te chance painai lol");
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
        Bot.addListener(new getMentionTag());
        Bot.addListener(new dlt());
        Bot.addListener(new Help());
        Bot.addListener(new gettingInvite());
        Bot.addListener(new memeAPI());
        Bot.addListener(new NekosDotLifeSFW());
        Bot.addListener(new NekoHelp());
        Bot.addListener(new NekoLifeMention());
        Bot.addListener(new nsfwNekoLIfeMentions());
        Bot.addListener(new kick());
        Bot.addListener(new armpitApi());
        Bot.addListener(new redditanyAPI());
        Bot.addListener(new reactionListenerTest());
        Bot.addListener(new searchimage());
        Bot.addListener(new embedListener());
        Bot.addListener(new getALL());
        Bot.addListener(new boorusearch());
        Bot.addListener(new nhentaitest());

        //banking
        Bot.addListener(new Balanceadd());
        Bot.addListener(new robbing());
        Bot.addListener(new MoneyDistribute());


        System.out.println("Bot is online! Owner:Md_kaif#3392");
    }
}
