package cIoanaAnd;

import java.util.ArrayList;
import java.util.List;

/*
 * Clasa pentru crearea unui stream nou
 * in functie de tipul acestuia
 */

public class FactoryStream {
    private static FactoryStream instance = null;
    private static List<Stream> streams;
    private FactoryStream() {}
    public static FactoryStream getInstance() {
        if (instance == null) {
            instance = new FactoryStream();
        }
        return instance;
    }

    public List<Stream> GetList() {
        return streams;
    }

    public Stream creeazaStream(int tip, int id, int streamG, long noStreams, int streamerId, long length, long date, String name) {
        if (streams == null) {
            streams = new ArrayList<>(100);
        }

        Stream stream;
        switch (tip) {
            case 1:
                stream = new StreamPiesaMuzicala(id, streamG, noStreams, streamerId, length, date, name);
                break;
            case 2:
                stream = new StreamPodcast(id, streamG, noStreams, streamerId, length, date, name);
                break;
            case 3:
                stream =  new StreamAudiobook(id, streamG, noStreams, streamerId, length, date, name);
                break;
            default:
                return null;
        }
        streams.add(stream);
        return stream;
    }

    public Stream getStreamById(int id) {
        for (Stream stream : streams) {
            if (stream.id == id) {
                return stream;
            }
        }
        return null;
    }
}
