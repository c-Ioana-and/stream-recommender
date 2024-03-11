/*
 *   Clasa corespunzatoare comenzii listen care adauga un stream
 *   in lista de streamuri ascultate de utilizator
 */

public class ListenC implements Command {
    private int idUser;
    private int idStream;
    private static ListenC instance = null;

    private ListenC(int idUser, int idStream) {
        this.idUser = idUser;
        this.idStream = idStream;
    }
    public static ListenC getInstance(int idUser, int idStream) {
        if (instance == null) {
            instance = new ListenC(idUser, idStream);
        }
        instance.idStream = idStream;
        instance.idUser = idUser;
        return instance;
    }
    public void execute() {
        User user = User.getUserById(idUser);
        assert user != null;
        Stream stream = user.getStreamById(idStream);

        if (stream != null) {
            stream.noOfStreams++;
        }
        else {
            stream = FactoryStream.getInstance().getStreamById(idStream);
            stream.noOfStreams++;
            user.addStreamToUser(idStream);
        }
    }
}
