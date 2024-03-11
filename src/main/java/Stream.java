import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;

/*
 * Clasa pentru stream-urile aplicatiei
 * Aceasta clasa contine informatii despre stream-uri
 * si o lista statica de stream-uri
 */

public abstract class Stream {
    int id, streamerId;
    int type;
    long noOfStreams;
    long length, dateAdded;
    String name;

    public String toString () {
        String output = "{";

        Streamer streamer = Streamer.getStreamerById(streamerId);

        output += "\"id\":\"" + id + "\",";
        output += "\"name\":\"" + name + "\",";

        assert streamer != null;
        output += "\"streamerName\":\"" + streamer.name + "\",";
        output += "\"noOfListenings\":\"" + noOfStreams + "\",";

        Duration duration = Duration.ofSeconds(length);
        long hours = duration.toHours();
        int minutes = (int) ((duration.getSeconds() % (60 * 60)) / 60);
        int seconds = (int) (duration.getSeconds() % 60);
        if (hours > 0) {
            if (hours < 10)
                output += "\"length\":\"0" + hours + ":";
            else output += "\"length\":\"" + hours + ":";
            if (minutes < 10)
                output += "0" + minutes + ":";
            else output += minutes + ":";
            if (seconds < 10)
                output += "0" + seconds + "\",";
            else output += seconds + "\",";
        } else {
            if (minutes < 10)
                output += "\"length\":\"0" + minutes + ":";
            else output += "\"length\":\"" + minutes + ":";
            if (seconds < 10)
                output += "0" + seconds + "\",";
            else output += seconds + "\",";
        }

        Date date1 = new Date(dateAdded * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));

        String date = sdf.format(date1.getTime());

        output += "\"dateAdded\":\"" + date + "\"}";

        return output;
    }


}
