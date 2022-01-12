package Commands.funCommands;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.util.Random;

public class yomama implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String[] msg = event.getMessageContent().split(" ");


        Random rand = new Random();
        int upperbound = 56;
        int ran = rand.nextInt(upperbound);
        String joke = switch (ran) {
            case 0 -> "Yo mama's so lazy, she stuck her nose out the window and let the wind blow it.";
            case 1 -> "Yo mama's so fat, when she fell I didn't laugh, but the sidewalk cracked up.";
            case 2 -> "Yo mama's so fat, when she skips a meal, the stock market drops";
            case 3 -> "Yo mama's so fat, it took me two buses and a train to get to her good side.";
            case 4 -> "Yo mama's so fat, when she goes camping, the bears hide their food.";
            case 5 -> "Yo mama's so fat, if she buys a fur coat, a whole species will become extinct.";
            case 6 -> "Yo mama's so fat, she stepped on a scale and it said: To be continued.";
            case 7 -> "Yo mama's so fat, I swerved to miss her in my car and ran out of gas.";
            case 8 -> "Yo mama's so fat, when she wears high heels, she strikes oil.";
            case 9 -> "Yo mama's so fat, she was overthrown by a small militia group, and now she's known as the Republic of Yo Mama.";
            case 10 -> "Yo mama's so fat, when she sits around the house, she SITS AROUND the house.";
            case 11 -> "Yo mama's so fat, her car has stretch marks.";
            case 12 -> "Yo mama's so fat, she can't even jump to a conclusion.";
            case 13 -> "Yo mama's so fat, her blood type is Ragu.";
            case 14 -> "Yo mama's so fat, if she was a Star Wars character, her name would be Admiral Snackbar";
            case 15 -> "Yo mama's so fat, she brought a spoon to the Super Bowl.";
            case 16 -> "Yo mama's so stupid, she stared at a cup of orange juice for 12 hours because it said concentrate.";
            case 17 -> "Yo mama's so stupid when they said it was chilly outside, she grabbed a bowl.";
            case 18 -> "Yo mama's so stupid, she put lipstick on her forehead to make up her mind.";
            case 19 -> "Yo mama's so stupid, when they said, \"Order in the court,\" she asked for fries and a shake.";
            case 20 -> "Yo mama's so stupid, she thought a quarterback was a refund.";
            case 21 -> "Yo mama's so stupid, she thought a quarterback was a refund.";
            case 22 -> "Yo mama's so stupid, she got hit by a parked car.";
            case 23 -> "Yo mama's so stupid, when I told her that she lost her mind, she went looking for it";
            case 24 -> "Yo mama's so stupid when thieves broke into her house and stole the TV, she chased after them shouting \"Wait, you forgot the remote!\"";
            case 25 -> "Yo mama's so stupid, she went to the dentist to get a Bluetooth.";
            case 26 -> "Yo mama's so stupid, she took a ruler to bed to see how long she slept.";
            case 27 -> "Yo mama's so stupid, she got locked in the grocery store and starved to death.";
            case 28 -> "Yo mama's so stupid, when I said, \"Drinks on the house,\" she got a ladder.";
            case 29 -> "Yo mama's so stupid, it takes her two hours to watch 60 Minutes.";
            case 30 -> "Yo mama's so stupid, she put airbags on her computer in case it crashed.";
            case 31 -> "Yo mama's so ugly, she threw a boomerang and it refused to come back.";
            case 32 -> "Yo mama's so old, her social security number is one.";
            case 34 -> "Yo mama's so ugly, she made a blind kid cry.";
            case 35 -> "Yo mama's so ugly, her portraits hang themselves.";
            case 36 -> "Yo mama's so old, she walked out of a museum and the alarm went off.";
            case 37 -> "Yo mama's teeth are so yellow when she smiles at traffic, it slows down.";
            case 38 -> "Yo mama's armpits are so hairy, it looks like she's got Buckwheat in a headlock";
            case 39 -> "Yo mama's so ugly, when she was little, she had to trick-or-treat by phone.";
            case 40 -> "Yo mama's so ugly, her birth certificate is an apology letter.";
            case 41 -> "Yo mama's so ugly, she looked out the window and was arrested for mooning.";
            case 42 -> "Yo mama's so poor, the ducks throw bread at her.";
            case 43 -> "Yo mama's so poor, she chases the garbage truck with a grocery list.";
            case 44 -> "Yo mama's cooking so nasty, she flys got together and fixed the hole in the window screen.";
            case 45 -> "Yo mama's so depressing, blues singers come to visit her when they've got writer's block.";
            case 46 -> "Yo mama's so short, you can see her feet on her driver's license.";
            case 47 -> "Yo mama's so poor, she can't even afford to pay attention.";
            case 48 -> "Yo mama so big, her belt size is \"equator.\"";
            case 49 -> "Yo mama's so classless, she's a Marxist utopia.";
            case 50 -> "Yo mama so short, she went to see Santa and he told her to get back to work.";
            case 51 -> "Yo mama so scary, the government moved Halloween to her birthday.";
            case 52 -> "Yo mama's so nasty, they used to call them jumpolines 'til yo mama bounced on one.";
            case 53 -> "Yo mama's teeth so yellow, I can't believe it's not butter.";
            case 54 -> "Yo mama's so poor, Nigerian princes wire her money.";
            case 55 -> "Yo mama so dumb, she went to the eye doctor to get an iPhone.";

            default -> "I segs yo mama";
        };

        if (msg[0].equalsIgnoreCase("!yomama") && msg.length>1) {
            for (User mentionedUser : event.getMessage().getMentionedUsers()) {
                new MessageBuilder().setEmbeds(new EmbedBuilder()
                        .setAuthor(event.getMessageAuthor())
                        .setTitle("Bro")
                        .setDescription(mentionedUser.getMentionTag() + "\n" + joke)
                        .setThumbnail("https://media.discordapp.net/attachments/924564718780293122/927503540346314752/FB_IMG_1629995880063.jpg?width=473&height=473")
                        .setColor(Color.MAGENTA)).send(event.getChannel());

            }

        }



    }
}
