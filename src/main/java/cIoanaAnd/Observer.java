package cIoanaAnd;
/*
 *   Interfata corespunzatoare observatorilor
 *   Observatorul va fi notificat cand se sterge un stream
 *   Observatorii sunt de tipul User, insa se poate extinde
 *   la alte tipuri de observatori
 */

public interface Observer {
    void update(Stream stream);
}