/*
 * Clasa corespunzatoare comenziilor de recomandare
 * Aceasta clasa este un singleton
 * si retine tipul de recomandare dorit
 */

public class RecommendContext {
    private final RecommendSystem strategy;

    public RecommendContext(RecommendSystem strategy){
        this.strategy = strategy;
    }

    public void executeStrategy(int idUser, String StreamType){
        strategy.doOperation(idUser, StreamType);
    }
}