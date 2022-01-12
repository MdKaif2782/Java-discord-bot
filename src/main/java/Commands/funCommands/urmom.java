package Commands.funCommands;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.util.Random;

public class urmom implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        Random rand = new Random();
        int upperbound = 5;
        int ran = rand.nextInt(upperbound);
        String[] msg = event.getMessageContent().split(" ");
        if (msg[0].equalsIgnoreCase("!urmom"))
        {
            for (User mentionedUser : event.getMessage().getMentionedUsers()) {
                if (ran==1)
                {
                    new MessageBuilder().setEmbeds(new EmbedBuilder()
                                    .setAuthor(event.getMessageAuthor())
                            .setTitle("Your mom?")
                            .setDescription(mentionedUser.getMentionTag()+"Isn't this her?")
                            .setImage("https://s3.amazonaws.com/s3.timetoast.com/public/uploads/photo/6448682/image/e71cdbfd24391f9a1041508ca48b1a1e")
                            .setColor(Color.YELLOW)).send(event.getChannel());
                }
                else if (ran==2)
                {
                    new MessageBuilder().setEmbeds(new EmbedBuilder()
                                    .setAuthor(event.getMessageAuthor())
                            .setTitle("Your mom?")
                            .setDescription(mentionedUser.getMentionTag()+"I did her last night")
                            .setImage("https://i.redd.it/cz7porxe01131.jpg")
                            .setColor(Color.YELLOW)).send(event.getChannel());
                }
                else if (ran==3)
                {
                    new MessageBuilder().setEmbeds(new EmbedBuilder()
                                    .setAuthor(event.getMessageAuthor())
                            .setTitle("Bro im busy")
                            .setDescription(mentionedUser.getMentionTag()+"Busy becz,")
                            .setImage("https://c.tenor.com/XnZWvAVHfjcAAAAC/me-on-my-way-to-bang-your-mum-your-mum.gif")
                            .setColor(Color.YELLOW)).send(event.getChannel());
                }
                else if (ran==4)
                {
                    new MessageBuilder().setEmbeds(new EmbedBuilder()
                                    .setAuthor(event.getMessageAuthor())
                            .setTitle("Keep Culm")
                            .setDescription(mentionedUser.getMentionTag()+":flushed: :flushed: :flushed: ")
                            .setImage("https://scontent.fdac24-1.fna.fbcdn.net/v/t1.15752-9/270178663_620381725883036_7713831758502054807_n.png?_nc_cat=102&ccb=1-5&_nc_sid=ae9488&_nc_eui2=AeEQrgNrjAg5IemdVS_ipCdKDh2_r1pddisOHb-vWl12KyI7x3AdNPUhiDL33bvj-tmaC_nFrk2DpO2Pllt-AaLm&_nc_ohc=ejEmPbguHwUAX-BtR6B&_nc_ht=scontent.fdac24-1.fna&oh=03_AVI0VgfWFX1_QwG3nVxJEEi4v1Ah2GA4kc_OeHUJORIQ2g&oe=61F6EEDC")
                            .setColor(Color.YELLOW)).send(event.getChannel());
                }
                else
                {
                    new MessageBuilder().setEmbeds(new EmbedBuilder()
                                    .setAuthor(event.getMessageAuthor())
                            .setTitle("Your mom?")
                            .setDescription( mentionedUser.getMentionTag()+ "Im marrying ur mom..SHow this to your dad")
                            .setImage("https://scontent.fdac24-2.fna.fbcdn.net/v/t1.15752-9/270232072_2443177572481562_3055793205202736994_n.png?_nc_cat=101&ccb=1-5&_nc_sid=ae9488&_nc_eui2=AeFhCA4Xyu1IJ1gCQZHAMt6QdbpS-a9cvy91ulL5r1y_L1XUHpTIu2gx3R29i6S-DUUe_ouZSh188VMsH8F8dlxN&_nc_ohc=WsG-tvPs7bgAX-YLTi_&_nc_ht=scontent.fdac24-2.fna&oh=03_AVJ8qM1mPKm8fP-JU1Bx4rUMcajlHkn1OmLLvQ6uCu2jHw&oe=61F91164")
                            .setColor(Color.YELLOW)).send(event.getChannel());
                }
            }


        }
    }
}
