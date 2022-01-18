package notForPublic;

import org.javacord.api.entity.message.embed.EmbedImage;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

public class embedListener implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        int height = 2;

        if (event.getMessageAuthor().isYourself()) {

            Optional<EmbedImage> image1 = event.getMessage().getEmbeds().get(0).getImage();

            String imageurl  = String.valueOf(image1.get().getUrl());

                System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
            URL url= null;
            try {
                url = new URL(imageurl);
                BufferedImage image = ImageIO.read(url);

                if (image==null) {
                    event.getChannel().sendMessage("there was not any image");
                }


            }catch (IOException e) {

                e.printStackTrace();
            }


        }
    }
}
