package Commands.funCommands;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;

public class height implements MessageCreateListener {



    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        int k = 160;
        String msg[] = event.getMessageContent().split(" ");
        int h = Integer.parseInt(msg[1]);

        if (msg[0].equalsIgnoreCase("!height"))
        {
            if (k<h) {

                event.getMessage().addReaction("\uD83D\uDC4E");

                new MessageBuilder().setEmbeds(new EmbedBuilder()
                        .setTitle("Damn bro!")
                        .setDescription("hghghghghghghghghghgh")
                        .setImage("https://scontent.fdac24-1.fna.fbcdn.net/v/t1.15752-9/269925842_452932329695210_5193976933164356589_n.jpg?_nc_cat=102&ccb=1-5&_nc_sid=ae9488&_nc_eui2=AeFsbaBw81rP5pJ6KnHh9uezKKUHDHAmAqQopQcMcCYCpD7wXCoNVtRHlkGp7aXh_GtOuw8d1q7EbC0tNyRaPeIb&_nc_ohc=q_RgFxskD7oAX_RNrTE&tn=uDpV95_VoGl8YhJl&_nc_ht=scontent.fdac24-1.fna&oh=03_AVK5dReGWoBB9YgVIkmNslK-Q59CWQZhDU_UqA5I8LLiCw&oe=61F8F0E6")
                        .setColor(Color.BLACK)).send(event.getChannel());
            }
            else if(k>h) {
                new MessageBuilder().setEmbeds(new EmbedBuilder()
                        .setTitle("HGHGHGHGHGHGHGHGHG")
                        .setDescription("lol you are shorter than kaif")
                        .setImage("https://scontent.fdac24-2.fna.fbcdn.net/v/t1.15752-9/270202640_3169337589980619_4120372652134710033_n.jpg?_nc_cat=105&ccb=1-5&_nc_sid=ae9488&_nc_eui2=AeE5vWwJgoJoMktV7u8xAFO2FpslHz47DVQWmyUfPjsNVHm2BUxVcWGpxJSRLWSJsj9b_hEmxIcDnEPYfnX6pnO-&_nc_ohc=1lKoe6kXrrEAX9pxB5a&_nc_ht=scontent.fdac24-2.fna&oh=03_AVI0t60hmSP5MCSYqRfL8Uk0CAt69p4LiaB4Lz5P2Ktrog&oe=61F8888B")
                        .setColor(Color.BLACK)).send(event.getChannel());
            }
            else {
                event.getChannel().sendMessage("dang..your height is same as kaif");
            }
        }


    }
}
