package de.marcelhuber.fileinputandoutput;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcel Huber
 */
public class DateiInputOutput {

    private boolean debuggingForFileAndFolders; // wird automatisch mit false initialisiert

    private FileSystem fs = FileSystems.getDefault();
    private String pfadnameAktuellesVerzeichnis = new File("").getAbsolutePath();
    private Path pfadAktuellesVerzeichnis = fs.getPath(pfadnameAktuellesVerzeichnis);
    private String dateiOrdnerName = "csvDateiOrdner";
    private String dateiName = "Testdaten.csv";
    private String csvDateiname;
    private Path pathFolderCSVDatei;
    private Path pathCSVDatei;

    public DateiInputOutput() {
        pathFolderCSVDatei = fs.getPath(pfadAktuellesVerzeichnis
                + "/../"
                + dateiOrdnerName);
        csvDateiname = "/" + dateiName;
        pathCSVDatei = fs.getPath(pathFolderCSVDatei.toString(), csvDateiname);
    }

    private DateiInputOutput(Builder build) {
        this.dateiName = build.dateiName;
        this.dateiOrdnerName = build.dateiOrdnerName;
        pathFolderCSVDatei = fs.getPath(pfadAktuellesVerzeichnis
                + "/../"
                + dateiOrdnerName);
        csvDateiname = "/"+ dateiName;
        pathCSVDatei = fs.getPath(pathFolderCSVDatei.toString(), csvDateiname);
    }

    private String separator = ",";
    // private List<String[]> tabelle = new ArrayList<>();

    public static void main(String[] args) {
        DateiInputOutput dummy = new DateiInputOutput();
        dummy.go();
    }

    public void go() {
        leseDieDatei(pathCSVDatei);
    }

    static public class Builder {

        private String dateiOrdnerName = "XXX";
        private String dateiName = "XXXXXXXXX";

        public Builder dateiOrdnerName(String dateiOrdnerName) {
            this.dateiOrdnerName = dateiOrdnerName;
            return this;
        }

        public Builder dateiName(String dateiName) {
            this.dateiName = dateiName;
            return this;
        }

        public DateiInputOutput build() {
            return new DateiInputOutput(this);
        }
    }

    private void leseDieDatei(Path pfadZurCSVDatei) {
        if (debuggingForFileAndFolders) {
//        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(pfadZurCSVDatei.getParent());) {
            try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(pathFolderCSVDatei);) {
                for (Path dateiName : dirStream) {
                    System.out.println(dateiName);
                }
            } catch (IOException ex) {
                Logger.getLogger(DateiInputOutput.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        File parentFolder = new File(pfadZurCSVDatei.getParent().toString());
        if (debuggingForFileAndFolders) {
            System.out.println("Existiert das Elternverzeichnis?          " + parentFolder.exists());
            System.out.println("Ist das Elternverzeichnis lesbar?         " + parentFolder.canRead());
            System.out.println("Ist das Elternverzeichnis überschreibbar? " + parentFolder.canWrite());
            parentFolder.setReadable(true);
            parentFolder.setWritable(true);
            File file = new File(pfadZurCSVDatei.toString());
            file.setReadable(true);
            file.setWritable(true);
//        System.out.println("XXXXXXXXXX: " + pfadZurCSVDatei.toString());
            System.out.println("Existiert die Datei?                      " + file.exists());
            System.out.println("Ist die Datei lesbar?                     " + file.canRead());
            System.out.println("Ist die Datei überschreibbar?             " + file.canWrite());
        }
        String zeile;
        try (
                FileReader fr = new FileReader(pfadZurCSVDatei.toString());
                BufferedReader br = new BufferedReader(fr);) {
            while ((zeile = br.readLine()) != null) {
                System.out.println(zeile);
                String[] zeileAsArray = zeile.split(separator);
                System.out.println(Arrays.toString(zeileAsArray));
                for (String wort : zeileAsArray) {
                    System.out.print(wort + " | ");
                }
                System.out.println("");
            }
        } catch (IOException ex) {
            Logger.getLogger(DateiInputOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pause(long timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
        } catch (InterruptedException ex) {
            Logger.getLogger(DateiInputOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
