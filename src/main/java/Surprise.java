import java.util.ArrayList;
import java.util.List;

/*
 * Aceasta clasa este folosita pentru a recomanda utilizatorului un stream
 * in functie de lista de stream-uri pe care acesta le-a ascultat
 */

public class Surprise implements RecommendSystem {
    //singleton
    private static Surprise instance = null;

    private Surprise() {}

    public static Surprise getInstance() {
        if (instance == null) {
            instance = new Surprise();
        }
        return instance;
    }


    public void doOperation(int idUser, String StreamType) {
        User user = User.getUserById(idUser);
        if (user != null) {
            List<Stream> s = new ArrayList<>(10);

            for (Stream stream : FactoryStream.getInstance().GetList()) {
                if (s.size() == 5) {
                    break;
                }
                // check if stream is not already in user's list
                // and if the user has not already subscribed to the streamer
                Streamer streamer = Streamer.getStreamerById(stream.streamerId);
                if (streamer != null) {
                    if (!user.streamers.contains(streamer)) {
                        if (stream.type == 1 && "SONG".equals(StreamType)) {
                            s.add(stream);
                        } else if (stream.type == 2 && "PODCAST".equals(StreamType)) {
                            s.add(stream);
                        } else if (stream.type == 3 && "AUDIOBOOK".equals(StreamType)) {
                            s.add(stream);
                        }
                    }
                }
            }

            s.sort((o1, o2) -> {
                if (o1.dateAdded > o2.dateAdded) {
                    return -1;
                } else if (o1.dateAdded < o2.dateAdded) {
                    return 1;
                } else {
                    if (o1.noOfStreams > o2.noOfStreams) {
                        return -1;
                    } else if (o1.noOfStreams < o2.noOfStreams) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });

            // print first 3 streams from list in json format
            int size = Math.min(3, s.size());
            System.out.print("[");
            for (int i = 0; i < size; i++) {
                System.out.print(s.get(i).toString());
                if (i != size - 1) {
                    System.out.print(",");
                }
            }
            System.out.println("]");
        }
    }
}
