/*
 *   Clasa corespunzatoare comenzii delete care sterge un stream
 *   din lista de streamuri a streamerului
 */

public class Delete implements Command{
    private int idStreamer;
    private int idStream;
    private static Delete instance = null;
    private Delete(int idStreamer, int idStream) {
        this.idStreamer = idStreamer;
        this.idStream = idStream;
    }
    public static Delete getInstance(int idStreamer, int idStream) {
        if (instance == null) {
            instance = new Delete(idStreamer, idStream);
        }
        instance.idStreamer = idStreamer;
        instance.idStream = idStream;
        return instance;
    }
    public void execute() {
        Streamer streamer = Streamer.getStreamerById(idStreamer);
        if (streamer != null) {
            streamer.removeStreamByID(idStream);
        }
    }
}
