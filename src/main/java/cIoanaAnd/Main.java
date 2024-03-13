package cIoanaAnd;

import java.io.*;

/*
 * Clasa principala a proiectului
 * Aceasta clasa contine metoda main si este responsabila cu citirea comenzilor
 * si apelarea metodelor corespunzatoare din celelalte clase
 */

public class Main {
    // metoda pentru curatarea listelor statice de streameri, useri
    // si streamuri dupa fiecare test
    public static void checkLeftovers() {
        if (Streamer.streamers != null)
            Streamer.streamers = null;

        if (User.users != null)
            User.users = null;

        if (FactoryStream.getInstance().GetList() != null)
            FactoryStream.getInstance().GetList().clear();
    }

    public static void main(String[] args) {
        if (args == null) {
            System.out.println("Nothing to read here");
            return;
        }

        checkLeftovers();

        ReadCSVs r = ReadCSVs.getInstance();
        r.readStreamers(args[0]);
        r.readStreams(args[1]);
        r.readUsers(args[2]);

        // citire comenzi
        File file = new File("src/main/resources/" + args[3]);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            CommandCenter commandCenter = new CommandCenter();
            FactoryStream streamF = FactoryStream.getInstance();

            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                int ID = Integer.parseInt(words[0]);
                String command = words[1];

                // in functie de comanda, apelez metoda corespunzatoare
                switch (command) {
                    case "ADD":
                        int streamType = Integer.parseInt(words[2]);
                        int streamID = Integer.parseInt(words[3]);
                        int streamGenre = Integer.parseInt(words[4]);
                        long length = Long.parseLong(words[5]);

                        String name = "";
                        for (int i = 6; i < words.length; i++) {
                            if (i == 6) {
                                name = words[i];
                            } else {
                                name += " " + words[i];
                            }
                        }

                        // preiau data curenta in milisecunde
                        long unixTime = System.currentTimeMillis();

                        Stream stream = streamF.creeazaStream(streamType, streamID, streamGenre,
                                0, ID, length, unixTime, name);
                        Add s = Add.getInstance(ID, stream);
                        commandCenter.setCommand(s);
                        commandCenter.executeCommand();
                        break;

                    case "LIST":
                        ListC s1 = ListC.getInstance(ID);
                        commandCenter.setCommand(s1);
                        commandCenter.executeCommand();
                        break;
                    case "DELETE":
                        int streamID1 = Integer.parseInt(words[2]);
                        Delete s2 = Delete.getInstance(ID, streamID1);
                        commandCenter.setCommand(s2);
                        commandCenter.executeCommand();
                        break;
                    case "LISTEN":
                        int StreamID = Integer.parseInt(words[2]);
                        ListenC s3 = ListenC.getInstance(ID, StreamID);
                        commandCenter.setCommand(s3);
                        commandCenter.executeCommand();
                        break;
                    case "RECOMMEND":
                        String type = words[2];
                        RecommendContext recommendContext = new RecommendContext(Recommend.getInstance());
                        recommendContext.executeStrategy(ID, type);
                        break;
                    case "SURPRISE":
                        String type1 = words[2];
                        RecommendContext recommendContext1 = new RecommendContext(Surprise.getInstance());
                        recommendContext1.executeStrategy(ID, type1);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
