package Commands.APIcommands.nhentai;

import net.beardbot.nhentai.NHentai;
import net.beardbot.nhentai.api.Gallery;
import net.beardbot.nhentai.api.GalleryPage;
import net.beardbot.nhentai.api.SearchResult;
import net.beardbot.nhentai.api.params.Language;
import net.beardbot.nhentai.api.params.Query;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class nQuery implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String[] msg = event.getMessageContent().split(" ");
        if (msg[0].equalsIgnoreCase("!nhsearch")){
            String mssg=msg[1]+" ";
            for (int i=2;i< msg.length;i++){
                mssg = mssg+msg[i]+" ";
            }
            System.out.println(mssg);


            String message = mssg;
            var nHentai = new NHentai();

            var result = nHentai.galleries().search(message);



            int num = 0;

            AtomicReference<AtomicInteger> j = new AtomicReference<>(new AtomicInteger());
            AtomicReference<AtomicInteger> pageno = new AtomicReference<>(new AtomicInteger());
            pageno.get().getAndIncrement();
            String tggg= result.getGalleries().get(num).getTags().get(0).getName()+", ";
            for (int i=0;i<result.getGalleries().get(num).getTags().size();i++){
                tggg=tggg+result.getGalleries().get(num).getTags().get(i).getName()+", ";
            }

            String finalTggg = tggg;
            SearchResult finalResult = result;
            SearchResult finalResult1 = result;
            SearchResult finalResult2 = result;
            SearchResult finalResult3 = result;
            new MessageBuilder().setEmbeds(new EmbedBuilder()
                    .setTitle(result.getGalleries().get(num).getEnglishTitle())
                    .setThumbnail(result.getGalleries().get(num).getThumbnail().getDownloadUrl())
                    .addField("Code","**"+result.getGalleries().get(0).getId()+"**")
                    .addField("Tags",tggg)
                    .addField("Number of pages", String.valueOf(result.getGalleries().get(num).getPages().size()))
                    .addField("Favorites", String.valueOf(result.getGalleries().get(num).getNumberOfFavorites()))
                    .addField("Uploaded",result.getGalleries().get(num).getUploadDate().toString())
                    .setColor(Color.BLACK)).send(event.getChannel()).thenAccept(message1 -> {
                message1.addReaction("\u2B05\uFE0F");
                message1.addReaction("\u27A1\uFE0F");




                message1.addReactionAddListener(reactionAddEvent -> {
                    if (reactionAddEvent.getEmoji().equalsEmoji("\u27A1\uFE0F")){
                        int k=j.get().get();
                        String tago= finalResult.getGalleries().get(k).getTags().get(0).getName()+", ";
                        for (int i = 0; i< finalResult.getGalleries().get(k).getTags().size(); i++){
                            tago=tago+ finalResult.getGalleries().get(k).getTags().get(i).getName()+", ";
                        }
                        message1.edit(new EmbedBuilder()
                                .setTitle(finalResult.getGalleries().get(k).getEnglishTitle())
                                .setThumbnail(finalResult.getGalleries().get(k).getThumbnail().getDownloadUrl())
                                .addField("Code","**"+result.getGalleries().get(k).getId()+"**")
                                .addField("Tags", tago)
                                .addField("Number of pages", String.valueOf(finalResult.getGalleries().get(k).getPages().size()))
                                .addField("Favorites", String.valueOf(finalResult.getGalleries().get(k).getNumberOfFavorites()))
                                .addField("Uploaded", finalResult.getGalleries().get(k).getUploadDate().toString())
                                .setColor(Color.BLACK));
                    }
                });

                message1.addReactionRemoveListener(reactionAddEvent -> {
                    if (reactionAddEvent.getEmoji().equalsEmoji("\u27A1\uFE0F")){
                        j.get().getAndIncrement();
                        int k=j.get().get();
                        String tago= finalResult1.getGalleries().get(k).getTags().get(0).getName()+", ";
                        for (int i = 0; i< finalResult1.getGalleries().get(k).getTags().size(); i++){
                            tago=tago+ finalResult1.getGalleries().get(k).getTags().get(i).getName()+", ";
                        }
                        message1.edit(new EmbedBuilder()
                                .setTitle(finalResult1.getGalleries().get(k).getEnglishTitle())
                                .setThumbnail(finalResult1.getGalleries().get(k).getThumbnail().getDownloadUrl())
                                .addField("Code","**"+result.getGalleries().get(k).getId()+"**")
                                .addField("Tags", tago)
                                .addField("Number of pages", String.valueOf(finalResult1.getGalleries().get(k).getPages().size()))
                                .addField("Favorites", String.valueOf(finalResult1.getGalleries().get(k).getNumberOfFavorites()))
                                .addField("Uploaded", finalResult1.getGalleries().get(k).getUploadDate().toString())
                                .setColor(Color.BLACK));
                    }
                });
                message1.addReactionAddListener(reactionAddEvent -> {
                    if (reactionAddEvent.getEmoji().equalsEmoji("\u2B05\uFE0F")){
                        j.get().getAndDecrement();
                        int k=j.get().get();
                        String tago= finalResult2.getGalleries().get(k).getTags().get(0).getName()+", ";
                        for (int i = 0; i< finalResult2.getGalleries().get(k).getTags().size(); i++){
                            tago=tago+ finalResult2.getGalleries().get(k).getTags().get(i).getName()+", ";
                        }
                        message1.edit(new EmbedBuilder()
                                .setTitle(finalResult2.getGalleries().get(k).getEnglishTitle())
                                .setThumbnail(finalResult2.getGalleries().get(k).getThumbnail().getDownloadUrl())
                                .addField("Code","**"+result.getGalleries().get(k).getId()+"**")
                                .addField("Tags", tago)
                                .addField("Number of pages", String.valueOf(finalResult2.getGalleries().get(k).getPages().size()))
                                .addField("Favorites", String.valueOf(finalResult2.getGalleries().get(k).getNumberOfFavorites()))
                                .addField("Uploaded", finalResult2.getGalleries().get(k).getUploadDate().toString())
                                .setColor(Color.BLACK));
                    }
                });
                message1.addReactionRemoveListener(reactionAddEvent -> {
                    if (reactionAddEvent.getEmoji().equalsEmoji("\u2B05\uFE0F")){
                        j.get().getAndDecrement();
                        int k=j.get().get();
                        String tago= finalResult3.getGalleries().get(k).getTags().get(0).getName()+", ";
                        for (int i = 0; i< finalResult3.getGalleries().get(k).getTags().size(); i++){
                            tago=tago+ finalResult3.getGalleries().get(k).getTags().get(i).getName()+", ";
                        }
                        message1.edit(new EmbedBuilder()
                                .setTitle(finalResult3.getGalleries().get(k).getEnglishTitle())
                                .setThumbnail(finalResult3.getGalleries().get(k).getThumbnail().getDownloadUrl())
                                .addField("Code","**"+result.getGalleries().get(k).getId()+"**")
                                .addField("Tags", tago)
                                .addField("Number of pages", String.valueOf(finalResult3.getGalleries().get(k).getPages().size()))
                                .addField("Favorites", String.valueOf(finalResult3.getGalleries().get(k).getNumberOfFavorites()))
                                .addField("Uploaded", finalResult3.getGalleries().get(k).getUploadDate().toString())
                                .setColor(Color.BLACK));
                    }
                });
            });


        }
    }
}