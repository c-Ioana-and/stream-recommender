import java.util.ArrayList;
import java.util.List;

/*
 * Clasa corespunzatoare streamerilor din aplicatie
 * Contine informatii despre streamer si lista de stream-uri
 * si, la nevoie, o referinta la un stream de eliminat atat din cadrul streamer-ului
 * cat si din lista de stream-uri a unui user (Subject in Observer pattern)
 */

public class Streamer implements Subject {
    int streamerType;
    int id;
    String name;
    List<Stream> streams;
    static List<Streamer> streamers;
    private List<Observer> observers = new ArrayList<>();
    Stream toBeRemoved;

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(toBeRemoved);
        }
    }

    public Streamer(int streamerType, int id, String name) {
        this.streamerType = streamerType;
        this.id = id;
        this.name = name;
        streams = new ArrayList<>(100);
        if (streamers == null) {
            streamers = new ArrayList<>(100);
        }
    }

    public static Streamer getStreamerById(int id) {
        for (Streamer streamer : streamers) {
            if (streamer.id == id) {
                return streamer;
            }
        }
        return null;
    }

    public void addStreamToStreamerID(int id, Stream stream) {
        for (Streamer streamer : streamers) {
            if (streamer.id == id) {
                streamer.streams.add(stream);
            }
        }
    }

    public void removeStreamByID(int id) {
        // remove stream from streamer
        for (int i = 0; i < streams.size(); i++) {
            if (streams.get(i).id == id) {
                streams.remove(i);
                notifyObservers();
                return;
            }
        }
    }

    public void afis () {
        // print streams of streamer in json format

        System.out.print("[");
        for (int i = 0; i < streams.size(); i++) {
            System.out.print(streams.get(i).toString());
            if (i != streams.size() - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }
}
