package Banking.sql;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Random;

public class robbing implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String[] msg = event.getMessageContent().split(" ");
        long victimUserID = event.getMessage().getMentionedUsers().get(0).getId();
        long robbberID = event.getMessageAuthor().getId();
        long ServerID = event.getServer().get().getId();


        if (msg[0].equalsIgnoreCase("!rob") && msg.length<3) {

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(timestamp);
            long now = timestamp.getTime();

            Connection conn = null;

            try {
                conn = DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3467490?" +
                        "user=sql3467490&password=a7leh3zlaA");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Statement stmt= null;
            try {
                stmt = conn.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ResultSet resultSet = null;
            try {
                resultSet = stmt.executeQuery("select * from MainTable"+" where UserID="+robbberID+" AND ServerID="+ServerID);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            long difference = 0;
            int counter=0;
            int countlimit=4;
            long maxtime=20*60;
            while (true)
            {
                try {
                    if (!resultSet.next()) break;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                BigDecimal previous = null;
                try {
                    previous = resultSet.getBigDecimal("Timeinmili");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println(previous);

                long prev = Long.parseLong(String.valueOf(previous));
                difference = (now-prev)/1000;
                try {
                    counter = resultSet.getInt("RobCount");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(System.currentTimeMillis());
            System.out.println(difference/60+ "min "+difference%60 +"sec");
            System.out.println("robcount is =" + counter);

            if (difference>=maxtime) {
                if (counter < countlimit) {
                    ////rob codes
                    //Example:
                    System.out.println("you robbed");





                    Random random = new Random();
                    int ran = random.nextInt(100);

                    if (!event.getMessage().getMentionedUsers().get(0).isBot()) {
                        if (ran < 65) {

                            try {
                                Connection conne = DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3467490?" +
                                        "user=sql3467490&password=a7leh3zlaA");
                                Statement sst = conne.createStatement();
                                ResultSet result = sst.executeQuery("select * from MainTable " + " where ServerID=" + ServerID + " AND UserID=" + victimUserID);

                                String moneyofvictim = "0";
                                while (result.next()) {
                                    moneyofvictim = result.getString("Money");
                                }
                                long moneyV = Long.parseLong(moneyofvictim);

                                if (moneyV > 100) {
                                    long newMoneyofVictim = (long) (moneyV - moneyV * 0.10);
                                    long addedbalancetorobber = (long) (moneyV * 0.10);

                                    PreparedStatement statement = conne.prepareStatement("UPDATE MainTable " +
                                            " SET Money=Money-" + addedbalancetorobber + ", TotalMoney=TotalMoney-" + addedbalancetorobber +
                                            " WHERE ServerID=" + ServerID + " AND UserID=" + victimUserID);
                                    statement.executeUpdate();

                                    PreparedStatement statement2 = conne.prepareStatement("UPDATE MainTable " +
                                            " SET Money=Money+" + addedbalancetorobber + ", BlackMoney=BlackMoney+" + addedbalancetorobber +
                                            " WHERE ServerID=" + ServerID + " AND UserID=" + robbberID);
                                    statement2.executeUpdate();


                           //         event.getChannel().sendMessage("You robbed " + addedbalancetorobber + "$ from" + event.getMessage().getMentionedUsers().get(0).getMentionTag());

                                    new MessageBuilder().setEmbeds(new EmbedBuilder()
                                            .setTitle("BMA Report")
                                            .setDescription(event.getMessageAuthor().getDisplayName() + " ROBBED **$"+addedbalancetorobber+"** from "+event.getMessage().getMentionedUsers().get(0).getMentionTag() +"\n" +
                                                    "\n**+$" + addedbalancetorobber + " **added to user's BlackMoney Wallet")
                                                    .setThumbnail("https://i.ibb.co/y6k7fmH/dance-party.gif")
                                            .setColor(Color.BLACK)).send(event.getChannel());



                                } else {
                                    new MessageBuilder().setEmbeds(new EmbedBuilder()
                                            .setDescription("**"  + " You cant rob poors! ** :no_entry_sign:")
                                            .setColor(Color.RED)).send(event.getChannel());
                                }

                                conne.close();


                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        } else {

                            try {
                                Connection coni = DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3467490?" +
                                        "user=sql3467490&password=a7leh3zlaA");
                                Statement smmmmt = coni.createStatement();
                                ResultSet resultset = smmmmt.executeQuery("select * from MainTable " + " where ServerID=" + ServerID + " AND UserID=" + robbberID);
                                String robbermoney = "0";
                                while (resultset.next()) {
                                    robbermoney = resultset.getString("Money");
                                }
                                long robberM = Long.parseLong(robbermoney);
                                long policefine = (long) (robberM * 0.12);

                                PreparedStatement statement = coni.prepareStatement("UPDATE MainTable " +
                                        " SET Money=Money-" + policefine + ", TotalMoney=TotalMoney-" + policefine +
                                        " WHERE ServerID=" + ServerID + " AND UserID=" + robbberID);
                                statement.executeUpdate();

                                PreparedStatement statement2 = coni.prepareStatement("UPDATE MainTable " +
                                        " SET Money=Money+" + policefine + ", TotalMoney=TotalMoney+" + policefine +
                                        " WHERE ServerID=" + ServerID + " AND UserID=924608474409742357");
                                statement2.executeUpdate();

                                coni.close();

                                new MessageBuilder().setEmbeds(new EmbedBuilder()
                                                .setThumbnail("https://i.ibb.co/1rSFYkK/ssssss.png")
                                                .setTitle("CNN NEWS")
                                        .setDescription("**"+event.getMessageAuthor().getName()  + " was caught while robbing ** " +
                                                event.getMessage().getMentionedUsers().get(0).getMentionTag()+"" +
                                                "\n\n**$"+policefine+"** was cut from his wallet as he bribed the Police to escape :bangbang: " )
                                        .setColor(Color.RED)).send(event.getChannel());

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        }

                    } else {
                        new MessageBuilder().setEmbeds(new EmbedBuilder()
                                .setDescription("**"  + " You cant rob bots! ** :no_entry_sign:")
                                .setColor(Color.RED)).send(event.getChannel());
                        try {
                            conn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }



                    //add count++
                    PreparedStatement statement = null;
                    try {
                        statement = conn.prepareStatement("UPDATE MainTable" +
                                " SET RobCount=RobCount+1"+
                                " where UserID="+robbberID+" AND ServerID="+ServerID );
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        statement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    if (counter==countlimit-1){
                        PreparedStatement statement1 = null;
                        try {
                            statement1 = conn.prepareStatement("UPDATE MainTable" +
                                    " SET RobCount=0, Timeinmili="+now+
                                    " where UserID="+robbberID+" AND ServerID="+ServerID );
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            statement1.executeUpdate();
				            conn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                        //setCurrent time as long to user
                        //set counter to 0
                    }
                }
            } else {
                System.out.println("You finished your session\nWait "+ (maxtime-difference) +" mins to continue");


                new MessageBuilder().setEmbeds(new EmbedBuilder()
                                .setTitle("COOLDOWN TIME")
                                .setThumbnail("https://c.tenor.com/GaOy-0e7c7kAAAAM/times-up-time-to-stop.gif")
                        .setDescription("**You finished your session\n **Wait "+ (maxtime-difference)/60 +" min "+(maxtime-difference)%60+" sec " +"to continue**\n\n\n" +
                                ":alarm_clock: :alarm_clock: :alarm_clock: :alarm_clock: :alarm_clock: ")
                        .setColor(Color.RED)).send(event.getChannel());
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }
}
