/*
 * Clasa pentru stream-urile aplicatiei de tipul piesa muzicala
 */
package cIoanaAnd;

public class StreamPiesaMuzicala extends Stream{
    int genMuzical;
    public StreamPiesaMuzicala(int id, int streamG, long noStreams, int streamerId, long length, long date, String name) {
        this.type = 1;
        this.id = id;
        this.genMuzical = streamG;
        this.noOfStreams = noStreams;
        this.streamerId = streamerId;
        this.length = length;
        this.dateAdded = date;
        this.name = name;
    }
}
