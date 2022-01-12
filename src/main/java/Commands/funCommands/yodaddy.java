package Commands.funCommands;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.util.Random;

public class yodaddy implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String[] msg = event.getMessageContent().split(" ");

        Random rand = new Random();
        int upperbound = 65;
        int ran = rand.nextInt(upperbound);

        String joke = switch (ran) {
            case 0 -> "Yo daddy is so fat when he gets in an elevator, it HAS to go down !!";
            case 1 -> "Yo daddy is so old, when he farted dust came out.";
            case 2 -> "Yo daddy is so fat he has a homeless family living under him.";
            case 3 -> "Yo daddy is so bald, I used his head as a mirror!";
            case 4 -> "Yo daddy is so fat he jumped in the air and got stuck";
            case 5 -> "Yo daddy is so poor he can’t afford to pay attention!";
            case 6, 9 -> "Yo daddy is so fat I took a picture of him last Christmas and its still printing!";
            case 7 -> "Yo daddy is so ugly, he makes kids in wheelchairs run away";
            case 8 -> "Yo daddy is so poor, he went to McDonald’s and put a Mcflurry on layaway!";
            case 10 -> "Yo daddy is so ghetto he went to the dollar store to buy your moms wedding ring";
            case 11 -> "Yo daddy is so ugly, when he came from out the wound his mama looked at him and said \"put him back in\" ";
            case 12 -> """
                    Yo daddy is so dumb he thought fruit punch was a gay boxer

                    """;
            case 13 -> "Yo daddy is so stupid he tripped over a cordless phone";
            case 14 -> "Yo daddy is so ugly he gives freddy nightmares";
            case 15 -> "Yo daddy is so fat he can hear bacon cooking in canada";
            case 16 -> "Yo daddy is so fat he went to court and the judge said, \"Order in the court\" and he said, \"Can I get a double cheeseburger, extra large fries and matter fact the whole menu!\"";
            case 17 -> "Yo daddy is so ugly he looked out the window and got arrested for mooning";
            case 18 -> "Yo daddy is so fat when he stepped on the scale it said \"to be continued\"";
            case 19 -> "Yo Daddy is so dumb when I rung the doorbell he went to go check the microwave";
            case 20 -> "Yo daddy is so ugly the gold fish crackers didn’t even smile back at him.";
            case 21 -> "Yo daddy is so ugly, he threw a boomerang and I wouldn’t cone back.";
            case 22 -> "Yo daddy is so dumb he poked his eyes out to go on a blind date";
            case 23 -> "Yo daddy is so ugly he gets arrested for mooning every time he smiles";
            case 24 -> "Yo daddy is so dumb he injects coca-cola to get high";
            case 25 -> "Yo daddy is so dumb he thinks there are polar bears in Finland";
            case 26 -> "Yo daddy is so dumb he sold your tv to pay the license fee!";
            case 27 -> "Yo daddy is so ugly every time he goes out the cops pick him up and return him to the zoo";
            case 28 -> "Yo daddy is so dumb he moved from Tampere to Turku";
            case 29 -> "Yo daddy is so dumb he thinks Finland is part of Russia";
            case 30 -> "Yo daddy is so dumb he thinks Nokia is a Korean car manufacturer";
            case 32 -> "Yo daddy is so dumb he don’t realize ma daddy yo daddy";
            case 33 -> "Yo daddy is so uncool he’s the real reason behind global warming";
            case 34 -> "Yo daddy is so dumb, in a lottery roll over week he spends the whole week rolling over";
            case 35 -> "Yo daddy is so ugly that he scares even blind people";
            case 36 -> """
                    Yo daddy is so ugly he makes hyenas cry

                    """;
            case 37 -> "Yo daddy is So Nasty hes 20 with 7 kids";
            case 38 -> "Yo daddy is so filthy he needs to wipe his feet before he goes outside.";
            case 39 -> "Yo daddy is so NOT yo daddy!";
            case 40 -> "Yo daddy is so old, so old, so old that when he met the Dead Sea was still sick.";
            case 41 -> "Yo daddy is so ugly people cross the street to avoid him but he’s so fat he’s there too";
            case 42 -> "Yo daddy is so greasy he sweats mayo!";
            case 43 -> "Yo daddy is so dumb he thinks ‘Jesus and the twelve disciples’ is a Spanish gospel rock band";
            case 44 -> "Yo daddy is so dumass if you give for him a fish, he eats for a day. If you teach for him to fish, he can always eat. If you give for him a fire, he’s warm for a day. If you light for him on fire, he is warm for the rest of his life.";
            case 45 -> "Yo daddy is so fat when life guards saw him on the beach they called Save the Whale";
            case 46 -> "Yo daddy is so corny, corn grew on his head!";
            case 47 -> "Yo daddy is so greedy he’s the reason people are starving in Africa";
            case 48 -> "Yo daddy is so ordinary that you know iPhone is mainstream when he bought it.";
            case 49 -> "Your daddy is so dumb he supports TPS";
            case 50 -> "Yo daddy is so fat when he went swimming in Africa a female hippo wanted to marry him";
            case 51 -> "Yo daddy is so fat he got stuck in the fire escape during a fire and everyone left inside got fried";
            case 52 -> "Yo daddy is so dirty every time he farts the meteorogical office issues a hurricane warning";
            case 53 -> "Yo daddy is so fat when he jumped off the pier at Long Beach Japan had a tsunami.";
            case 54 -> "Yo daddy is so small -when stepping from carpet edge onto flooring he needs a parachute for landing.";
            case 55 -> "Yo daddy is so dumb he tried to kill a bird by throwing it off a cliff.";
            case 56 -> "Yo daddy is so ugly, He looked out the window and the cops arrested him.";
            case 57 -> "Yo daddy is so stupid! He tried to use a breast pump to get breast milk for the baby!";
            case 58 -> "Yo daddy is so ugly hello kitty even says goodbbye";
            case 59 -> "Yo daddy is so fat that if he doesn’t get his chicken, he’ll throw a tantraum before you can say Mindless Behavior.";
            case 60 -> "Yo daddy is so skinny you make him reach behind furniture instead of the children!";
            case 61 -> "Yo daddy is so stupid he got trapped on an escalator for hours when the power went out!";
            case 62 -> "Yo daddy is so fat when the flight attendant comes around she offers him triple the food! So that means 3 bags of pretzels and 3 cokes!";
            case 63 -> "Yo daddy is so small, someone thought he was a jelly bean so they ate him.";
            case 64 -> "Yo daddy is so stupid! He got locked in a mattress store and slept on the floor!";






            default -> throw new IllegalStateException("Unexpected value: " + ran);
        };
        if (msg[0].equalsIgnoreCase("!yodaddy") && msg.length>1) {
            for (User mentionedUser : event.getMessage().getMentionedUsers()) {
                new MessageBuilder().setEmbeds(new EmbedBuilder()
                        .setAuthor(event.getMessageAuthor())
                        .setTitle("Bro")
                        .setDescription(mentionedUser.getMentionTag() + "\n" + joke)
                        .setThumbnail("https://media.discordapp.net/attachments/924564718780293122/927503931628716042/FB_IMG_16269704257941546.jpg")
                        .setColor(Color.BLACK)).send(event.getChannel());

            }
        }

    }
}
