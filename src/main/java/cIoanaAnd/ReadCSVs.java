package cIoanaAnd;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileReader;

/*
 *  Clasa pentru citirea fisierelor CSV
 */

public class ReadCSVs {
    private static ReadCSVs instance;
    private ReadCSVs() {}

    public static ReadCSVs getInstance() {
        if (instance == null) {
            instance = new ReadCSVs();
        }
        return instance;
    }
    
    public void readStreamers (String path) {
        File file = new File("src/main/resources/" + path);
        try {
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            String[] line;

            // citesc linie cu linie, ignorand-o pe prima
            csvReader.readNext();
            while ((line = csvReader.readNext()) != null) {
                int streamerType = Integer.parseInt(line[0]);
                int id = Integer.parseInt(line[1]);
                String name = line[2];

                Streamer streamer = new Streamer(streamerType, id, name);
                Streamer.streamers.add(streamer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readStreams (String path) {
        File file = new File("src/main/resources/" + path);
        try {
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            String[] line;
            FactoryStream factoryStream = FactoryStream.getInstance();

            // citesc linie cu linie, ignorand-o pe prima
            csvReader.readNext();
            while ((line = csvReader.readNext()) != null) {
                int StreamType = Integer.parseInt(line[0]);
                int id = Integer.parseInt(line[1]);
                int streamGenre = Integer.parseInt(line[2]);
                long noOfStreams = Long.parseLong(line[3]);
                int streamerId = Integer.parseInt(line[4]);
                long length = Long.parseLong(line[5]);
                long dateAdded = Long.parseLong(line[6]);
                String name = line[7];

                // in functie de tipul streamului, creez un stream nou
                // folosind clasa FactoryStream
                Stream stream = (Stream) factoryStream.creeazaStream(StreamType, id, streamGenre,
                        noOfStreams, streamerId, length, dateAdded, name);

                // adaug streamul la streamerul corespunzator
                Streamer s = Streamer.getStreamerById(streamerId);
                if (s != null) {
                    s.addStreamToStreamerID(streamerId, stream);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void readUsers (String path) {
        File file = new File("src/main/resources/" + path);
        try {
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            String[] line;

            // citesc linie cu linie, ignorand-o pe prima
            csvReader.readNext();
            while ((line = csvReader.readNext()) != null) {
                int id = Integer.parseInt(line[0]);
                String name = line[1];
                String[] streams = line[2].split(" ");

                User user = new User(id, name);
                for (String stream : streams) {
                    int streamId = Integer.parseInt(stream);
                    user.addStreamToUser(streamId);
                }
                User.users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
