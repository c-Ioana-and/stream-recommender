import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/*
 * Clasa pentru userii aplicatiei
 * Aceasta clasa contine informatii despre useri
 * si o lista statica de useri
 */

public class User implements Observer {
    int id;
    String name;
    List<Stream> streams;
    List<Streamer> streamers;
    static List<User> users;

    public void update(Stream stream) {
        // remove stream from user
        IntStream.range(0, streams.size()).filter(i -> streams.get(i).id == stream.id).findFirst().ifPresent(i -> streams.remove(i));
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        streams = new ArrayList<>(100);
        streamers = new ArrayList<>(100);
        if (users == null) {
            users = new ArrayList<>(100);
        }
    }

    public void addStreamToUser(int streamId) {
        Stream stream = FactoryStream.getInstance().getStreamById(streamId);
        if (stream != null) {
            streams.add(stream);

            Streamer s = Streamer.getStreamerById(stream.streamerId);
            if (s != null) {
                streamers.add(s);
                s.registerObserver(this);
            }
        }
    }

    public Stream getStreamById(int id) {
        return streams.stream().filter(stream -> stream.id == id).findFirst().orElse(null);
    }

    public static User getUserById(int id) {
        for (User user : users) {
            if (user.id == id) {
                return user;
            }
        }
        return null;
    }

    public void afis () {
        // print streams of streamer in json format
        int i = 0;

        System.out.print("[");

        for (Stream stream : streams) {
            System.out.print(stream);
            if (i != streams.size() - 1) {
                System.out.print(",");
            }
            i++;
        }
        System.out.println("]");
    }


}
