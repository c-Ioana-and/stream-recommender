import java.util.ArrayList;
import java.util.List;

/*
 *   Clasa corespunzatoare comenzii Recommend care recomanda un stream
 *   in functie de lista de stream-uri pe care acesta le-a ascultat
 */

public class Recommend implements RecommendSystem {
    private static Recommend instance = null;

    private Recommend() {
    }

    public static Recommend getInstance() {
        if (instance == null) {
            instance = new Recommend();
        }
        return instance;
    }

    public void doOperation(int idUser, String StreamType) {
        User user = User.getUserById(idUser);
        if (user != null) {
            List<Stream> s = new ArrayList<>(10);

            for (Stream stream : FactoryStream.getInstance().GetList()) {
                // check if stream is not already in user's list
                // and if the user has not already subscribed to the streamer
                Streamer streamer = Streamer.getStreamerById(stream.streamerId);
                if (streamer != null) {
                    if (user.getStreamById(stream.id) == null && user.streamers.contains(streamer)) {
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

            s.sort((o1, o2) -> Long.compare(o2.noOfStreams, o1.noOfStreams));

            // print first 5 streams from list in json format

            int size = Math.min(5, s.size());
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
