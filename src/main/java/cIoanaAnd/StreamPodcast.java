/*
 * Clasa pentru stream-urile aplicatiei de tipul podcast
 */
package cIoanaAnd;
public class StreamPodcast extends Stream{
    int genPodcast;
    public StreamPodcast(int id, int streamG, long noStreams, int streamerId, long length, long date, String name) {
        this.type = 2;
        this.id = id;
        this.genPodcast = streamG;
        this.noOfStreams = noStreams;
        this.streamerId = streamerId;
        this.length = length;
        this.dateAdded = date;
        this.name = name;
    }

}
