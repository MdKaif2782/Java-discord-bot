

import Banking.sql.postgress.AcCreate;
import Banking.sql.postgress.BalAdd;
import Banking.sql.postgress.CheckBal;
import Commands.APIcommands.*;
import Commands.APIcommands.NekoLife.NekoHelp;
import Commands.APIcommands.NekoLife.NekoLifeMention;
import Commands.APIcommands.NekoLife.NekosDotLifeSFW;
import Commands.APIcommands.NekoLife.nsfwNekoLIfeMentions;
import Commands.APIcommands.booru.boorusearch;
import Commands.APIcommands.nhentai.ResponseTest;
import Commands.APIcommands.nhentai.nQuery;
import Commands.admins.Help;
import Commands.admins.dlt;
import Commands.admins.kick;
import Commands.admins.msgdelete;
import Commands.baiscCommands.PingPong;
import Commands.baiscCommands.Rate;
import Commands.domath.DoMath;
import Commands.Reader.Reader;

import Commands.funCommands.*;
import Commands.interactions.slashPing;
import Commands.interactions.slashPlayWithButtons;
import notForPublic.*;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.user.UserStatus;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandOption;
import org.javacord.api.listener.interaction.SlashCommandCreateListener;


public class JavaCordBot {


    public static void main(String[] args) {

        String token1 = "OTI0NjA4NDc0NDA5NzQyMzU3.";
        String token2 = "YchCvg.A0s-BnJ5_XmULW2GwyAj6mpY058";

        DiscordApi Bot = new DiscordApiBuilder()
        .setToken(token1+token2)
//                .setToken("OTI0NTc4NzcwNzg1MDEzODAw.YcgnFA.3M_UyiMaeQQIRV7JCnIciqQS6rs")
        .login().join();

        Bot.updateActivity(ActivityType.PLAYING, "us");
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
        Bot.addListener(new ResponseTest());
        Bot.addListener(new aatTola());
        Bot.addListener(new nQuery());

        //banking
//        Bot.addListener(new Balanceadd());
//        Bot.addListener(new robbing());
//        Bot.addListener(new MoneyDistribute());

        //postgres bankking
        Bot.addListener(new AcCreate());
        Bot.addListener(new CheckBal());
        Bot.addListener(new BalAdd());


        //Slash Commands
        SlashCommand.with("ping", "Replies with pong!")
                .createGlobal(Bot)
                .join();
        Bot.addListener(new slashPing());

        SlashCommand.with("play_with_buttons", "play with buttons haha")
                .createGlobal(Bot)
                .join();
        Bot.addListener(new slashPlayWithButtons());

        SlashCommand.with("nhentai","Read doujins here!")
                .addOption(SlashCommandOption.createDecimalOption("code","provide the doujin code",true))
                .createGlobal(Bot)
                .join();


        System.out.println("Bot is online! Owner:Md_kaif#3392");
    }
}
