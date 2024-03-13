package cIoanaAnd;
/*
 *  Interfata corespunzatoare recomandarilor
 *  Aceasta interfata este implementata de clasele
 *  Recommend si Surprise
 */

public interface RecommendSystem {
    void doOperation(int idUser, String StreamType);
}