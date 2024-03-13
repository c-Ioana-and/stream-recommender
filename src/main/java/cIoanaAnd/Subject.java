package cIoanaAnd;
/*
 * Interfata unui Subject din Observer pattern
 * Aceasta interfata este implementata de clasa Streamer
 * care notifica toti observerii (clasa User) cand un stream
 * este sters
 */

public interface Subject {
    void registerObserver(Observer observer);
    void notifyObservers();
}