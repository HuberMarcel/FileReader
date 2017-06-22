package de.marcelhuber.fileinputandoutput;

/**
 *
 * @author Marcel Huber
 */
public class DateiInputOutputDemo {

    public static void main(String[] args) {
//        new DateiInputOutputDemo().go();
        new DateiInputOutputDemo().goFrageAntwort();
    }

    private void go() {
        new DateiInputOutput().go();
        System.out.println("");
        System.out.println("");
        System.out.print("Alternativ: ".toUpperCase());
        System.out.println("go() mit " + "Builder!".toUpperCase());
        System.out.println("");
        System.out.println("");
        new DateiInputOutput.Builder()
                .dateiOrdnerName("csvDateiOrdner")
                .dateiName("Testdaten.csv")
                .build()
                .go();
    }

    private void goFrageAntwort() {
        DateiInputOutput dummyFrage = new DateiInputOutput.Builder()
                .dateiName("Frage01.txt")
                .dateiOrdnerName("csvDateiOrdner/FragenSammlung").build();
        dummyFrage.go();
        System.out.println("");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("");
        DateiInputOutput dummyAntwort = new DateiInputOutput.Builder()
                .dateiOrdnerName("csvDateiOrdner/AntwortenSammlung")
                .dateiName("Antwort01.txt").build();
        dummyAntwort.go();

    }
}
