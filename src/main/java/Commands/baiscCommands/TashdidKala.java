package Commands.baiscCommands;

import com.gargoylesoftware.htmlunit.javascript.host.ImageBitmap;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TashdidKala implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessage().getContent().toLowerCase().contains("tashdid") && !event.getMessage().getUserAuthor().get().isYourself()){
            event.getChannel().sendMessage("Hae vai thik bolsos tashdid kalai");
            BufferedImage image = null;
            try {
                //check if the message contains an image
                if (!event.getMessage().getAttachments().isEmpty()) {
                    image = ImageIO.read(event.getMessage().getAttachments().get(0).getUrl());
                } else {
                    image = event.getMessageAuthor().asUser().get().getAvatar().asBufferedImage().get();
                }
                BufferedImage newImage = addTextToImage(image, "Tashdid Kalai");
                event.getChannel().sendMessage(new EmbedBuilder().setImage(newImage));

            } catch (InterruptedException | ExecutionException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public BufferedImage addTextToImage(BufferedImage image, String text) {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        // Create a new image with the same size as the input image
        BufferedImage newImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

        // Get the graphics context of the new image
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(image, 0, 0, null);

        // Set the font and color for the text
        Font font = new Font("Arial", Font.BOLD, 36);
        graphics.setFont(font);
        graphics.setColor(Color.WHITE);

        // Calculate the size of the text
        int textWidth = graphics.getFontMetrics().stringWidth(text);
        int textHeight = graphics.getFontMetrics().getHeight();

        // Calculate the position of the text in the middle of the image
        int x = (imageWidth - textWidth) / 2;
        int y = (imageHeight - textHeight) / 2;

        // Draw the text with black border
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(text, x-1, y-1);
        graphics.drawString(text, x-1, y+1);
        graphics.drawString(text, x+1, y-1);
        graphics.drawString(text, x+1, y+1);
        graphics.setColor(Color.BLACK);
        graphics.drawString(text, x, y);

        // Dispose the graphics context
        graphics.dispose();

        return newImage;
    }

    public static BufferedImage getImageFromUrl(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        BufferedImage image = ImageIO.read(url);
        return image;
    }
}
