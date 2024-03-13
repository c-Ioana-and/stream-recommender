package cIoanaAnd;
/*
 * Clasa pentru stream-urile aplicatiei de tipul audiobook
 */

public class StreamAudiobook extends Stream{
    int genAudiobook;
    public StreamAudiobook(int id, int streamG, long noStreams, int streamerId, long length, long date, String name) {
        this.type = 3;
        this.id = id;
        this.genAudiobook = streamG;
        this.noOfStreams = noStreams;
        this.streamerId = streamerId;
        this.length = length;
        this.dateAdded = date;
        this.name = name;
    }
}
