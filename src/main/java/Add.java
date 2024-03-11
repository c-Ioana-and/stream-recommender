/*
 *   Clasa corespunzatoare comenzii Add care adauga un stream
 *   in lista de streamuri a streamerului
 */

public class Add implements Command {
    private int idStreamer;
    private Stream stream;
    private static Add instance = null;

    private Add(int idStreamer, Stream stream) {
        this.idStreamer = idStreamer;
        this.stream = stream;
    }

    public static Add getInstance(int idStreamer, Stream stream) {
        if (instance == null) {
            instance = new Add(idStreamer, stream);
        }
        instance.idStreamer = idStreamer;
        instance.stream = stream;
        return instance;
    }

    public void execute() {
        Streamer streamer = Streamer.getStreamerById(idStreamer);
        if (streamer != null) {
            streamer.addStreamToStreamerID(idStreamer, stream);
        }
    }
}
