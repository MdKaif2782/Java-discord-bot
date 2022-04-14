package Commands.APIcommands.nhentai;

import net.beardbot.nhentai.NHentai;
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

            ArrayList<String> Title = new ArrayList<>();
            ArrayList<String> Codes = new ArrayList<>();
            ArrayList<String> ThumbnailURL = new ArrayList<>();
            ArrayList<String> NumOfPages = new ArrayList<>();
            ArrayList<String> Favs = new ArrayList<>();
            ArrayList<String> Uploaded = new ArrayList<>();

            int pageNo = 1;
            for (int i=0; i<result.getGalleriesOnPage(pageNo).size();i++) {
                Title.add(result.getGalleriesOnPage(pageNo).get(i).getEnglishTitle());
                Codes.add(String.valueOf(result.getGalleriesOnPage(pageNo).get(i).getId()));
                ThumbnailURL.add(result.getGalleriesOnPage(pageNo).get(i).getThumbnail().getDownloadUrl());
                NumOfPages.add(String.valueOf(result.getGalleriesOnPage(pageNo).get(i).getPages().size()));
                Favs.add(String.valueOf(result.getGalleriesOnPage(pageNo).get(i).getNumberOfFavorites()));
                Uploaded.add(result.getGalleriesOnPage(pageNo).get(i).getUploadDate().toString());

                System.out.println(result.getGalleriesOnPage(pageNo).get(i).getEnglishTitle() + "\n" +
                        result.getGalleries().get(i).getId() + "\n");

            }
            System.out.println(result.getGalleriesOnPage(pageNo).size()+" results found");
            if (result.getGalleriesOnPage(pageNo).isEmpty()){
                System.out.println("no result found");
            }

            int num = 0;
            AtomicReference<AtomicInteger> j = new AtomicReference<>(new AtomicInteger());
            String tggg= result.getGalleriesOnPage(pageNo).get(num).getTags().get(0).getName()+", ";
            for (int i=0;i<result.getGalleriesOnPage(pageNo).get(num).getTags().size();i++){
                tggg=tggg+result.getGalleriesOnPage(pageNo).get(num).getTags().get(i).getName()+", ";
            }
            String finalTggg = tggg;
            SearchResult finalResult = result;
            SearchResult finalResult1 = result;
            SearchResult finalResult2 = result;
            SearchResult finalResult3 = result;
            new MessageBuilder().setEmbeds(new EmbedBuilder()
                    .setTitle(Title.get(num))
                    .setThumbnail(ThumbnailURL.get(num))
                    .addField("Code","**"+Codes.get(num)+"**")
                    .addField("Tags",tggg)
                    .addField("Number of pages", NumOfPages.get(num))
                    .addField("Favorites", Favs.get(num))
                    .addField("Uploaded",Uploaded.get(num))
                    .setColor(Color.BLACK)).send(event.getChannel()).thenAccept(message1 -> {
                message1.addReaction("\u2B05\uFE0F");
                message1.addReaction("\u27A1\uFE0F");



                message1.addReactionAddListener(reactionAddEvent -> {
                    if (reactionAddEvent.getEmoji().equalsEmoji("\u27A1\uFE0F")){
                        j.get().getAndIncrement();
                        int k=j.get().get();
                        String tago= finalResult.getGalleries().get(k).getTags().get(0).getName()+", ";
                        for (int i = 0; i< finalResult.getGalleries().get(k).getTags().size(); i++){
                            tago=tago+ finalResult.getGalleries().get(k).getTags().get(i).getName()+", ";
                        }
                        message1.edit(new EmbedBuilder()
                                .setTitle(Title.get(k))
                                .setThumbnail(ThumbnailURL.get(k))
                                .addField("Code","**"+Codes.get(k)+"**")
                                .addField("Tags",tago)
                                .addField("Number of pages", NumOfPages.get(k))
                                .addField("Favorites", Favs.get(k))
                                .addField("Uploaded",Uploaded.get(k))
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
                                .setTitle(Title.get(k))
                                .setThumbnail(ThumbnailURL.get(k))
                                .addField("Code","**"+Codes.get(k)+"**")
                                .addField("Tags",tago)
                                .addField("Number of pages", NumOfPages.get(k))
                                .addField("Favorites", Favs.get(k))
                                .addField("Uploaded",Uploaded.get(k))
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
                                .setTitle(Title.get(k))
                                .setThumbnail(ThumbnailURL.get(k))
                                .addField("Code","**"+Codes.get(k)+"**")
                                .addField("Tags",tago)
                                .addField("Number of pages", NumOfPages.get(k))
                                .addField("Favorites", Favs.get(k))
                                .addField("Uploaded",Uploaded.get(k))
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
                                .setTitle(Title.get(k))
                                .setThumbnail(ThumbnailURL.get(k))
                                .addField("Code","**"+Codes.get(k)+"**")
                                .addField("Tags",tago)
                                .addField("Number of pages", NumOfPages.get(k))
                                .addField("Favorites", Favs.get(k))
                                .addField("Uploaded",Uploaded.get(k))
                                .setColor(Color.BLACK));
                    }
                });
            });


        }
    }
}