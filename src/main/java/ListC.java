/*
 *   Clasa corespunzatoare comenzii list care afiseaza streamurile
 *   din lista de streamuri a streamerului
 */

public class ListC implements Command {
    // singleton
    private static ListC instance = null;
    private int idStreamer;
    private ListC(int idStreamer) {
        this.idStreamer = idStreamer;
    }
    public static ListC getInstance(int idStreamer) {
        if (instance == null) {
            instance = new ListC(idStreamer);
        }
        instance.idStreamer = idStreamer;
        return instance;
    }

    public void execute() {
        Streamer streamer = Streamer.getStreamerById(idStreamer);
        if (streamer != null) {
            streamer.afis();
        }
        else {
            User user = User.getUserById(idStreamer);
            if (user != null) {
                user.afis();
            }
        }
    }
}
