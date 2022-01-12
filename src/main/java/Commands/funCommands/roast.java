package Commands.funCommands;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.util.Random;

public class roast implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String[] msg = event.getMessageContent().split(" ");

        Random rand = new Random();
        int upperbound = 31;
        int ran = rand.nextInt(upperbound);

        String joke = switch (ran) {
            case 0 -> "You’re cute, like my dog. He also chases his tail for entertainment.";
            case 1 -> "You are like a cloud. When you disappear, it’s a beautiful day.";
            case 2 -> "You have an entire life to be an idiot. Why not take today off?";
            case 3 -> "Your kid is so annoying, he makes his Happy Meal cry.";
            case 4 -> "Your face is just fine, but we’ll have to put a bag over that personality.";
            case 5 -> "I’m not a nerd. I’m just smarter than you.";
            case 6 -> "I may love to shop but I will never buy your bull.";
            case 7 -> "Child, I’ve forgotten more than you ever knew.";
            case 8 -> "I’m an acquired taste. If you don’t like me, acquire some taste.";
            case 9 -> "Bye. Hope to see you never.";
            case 11 -> "Don’t worry, the first 40 years of childhood are always the hardest.";
            case 12 -> "If you’re going to be two-faced, at least make one of them pretty.";
            case 13 -> "The only way my husband would ever get hurt during an activity is if the TV exploded.";
            case 14 -> "If you have a problem with me, write the problem on a piece of paper, fold it, and shove it up to your ass.";
            case 15 -> "Complete this sentence for me: \"I never want to see you ————!\"";
            case 16 -> "I thought of you today. It reminded me to take out the trash.";
            case 17 -> "You bring everyone so much joy when you leave the room.";
            case 18 -> "Did the mental hospital test too many drugs on you today?";
            case 19 -> "OH MY GOD! IT SPEAKS!";
            case 20 -> "Beauty is only skin deep, but ugly goes clean to the bone.";
            case 21 -> "I’d like to help you out. Which way did you come in?";
            case 22 -> "I forgot the world revolves around you. My apologies, how silly of me.";
            case 23 -> "Light travels faster than sound which is why you seemed bright until you spoke.";
            case 24 -> "I’d rather treat my baby’s diaper rash than have lunch with you.";
            case 25 -> "You look so pretty. Not at all gross, today.";
            case 26 -> "I only take you everywhere I go, so I don’t have to kiss you goodbye.";
            case 27 -> "We were happily married for one month, but unfortunately, we’ve been married for 10 years.";
            case 28 -> "When you look in the mirror, say hi to the clown you see in there for me, would you?";
            case 29 -> "Somewhere out there is a tree tirelessly producing oxygen for you. You owe it an apology.";
            case 30 -> "You sound like a problem.";

            default -> throw new IllegalStateException("Unexpected value: " + ran);
        };
        if (msg[0].equalsIgnoreCase("!roast") && msg.length > 1) {
            for (User mentionedUser : event.getMessage().getMentionedUsers()) {
                new MessageBuilder().setEmbeds(new EmbedBuilder()
                        .setAuthor(event.getMessageAuthor())
                        .setTitle("Bro")
                        .setDescription(mentionedUser.getMentionTag() + "\n" + joke)
                        .setThumbnail("https://media.discordapp.net/attachments/924564718780293122/927503931012182046/FB_IMG_1623757817908.jpg?width=473&height=473")
                        .setColor(Color.BLACK)).send(event.getChannel());

            }


        }
    }
}
