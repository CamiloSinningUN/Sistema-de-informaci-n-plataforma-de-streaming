package Archives;

import Lists.ListViewers;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import sinninglopezcamilojose.Viewer;

/**
 * Clase para la lectura y escritura del archivo para los viewers
 * @author camil
 */
public class ArchiveViewers {
    
    /**
     * Escribe la información de la lista en un archivo de tipo .txt
     * @param myViewers 
     */
    public void write(ListViewers myViewers) {
        File archiveViewers;
        FileWriter write;
        BufferedWriter storage;
        PrintWriter w;
        String str;
        BigDecimal idViewers;
        String nickName;
        float Hours;
        archiveViewers= new File("Viewers.txt");
        archiveViewers.delete();
        archiveViewers = new File("Viewers.txt");
        try {
            write = new FileWriter(archiveViewers);
            storage = new BufferedWriter(write);
            w = new PrintWriter(storage);
            ListViewers p = new ListViewers();
            p = myViewers;
          
            while ((p!=null)&&(p.myViewer != null)) {   
                w.write(p.myViewer.idViewer.toString());
                w.append("\n"+p.myViewer. nickName);
                w.append("\n"+p.myViewer.Hours);
                w.append("\n");
                p = p.link;
            }

            w.close();
            storage.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchiveViewers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchiveViewers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

     /**
     * Lee el archivo de viewers y lo convierte en una lista
     * @return La lista de viewers
     */
    public ListViewers read() {
        ListViewers myViewers = new ListViewers();
        File archiveViewers;
        FileReader read;
        BufferedReader storage;
        String str;
        BigDecimal idViewers;
        String nickName;
        float Hours;
        archiveViewers = new File("Viewers.txt");
        if(!archiveViewers.exists()){
            try {
                archiveViewers.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ArchiveViewers.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            read = new FileReader(archiveViewers);
            storage = new BufferedReader(read);
            str = storage.readLine();
            while (str != null) {
                try {
                    idViewers = new BigDecimal(str);
                    str = storage.readLine();
                    nickName = str;
                    str = storage.readLine();
                    Hours = Float.parseFloat(str);
                    if (str != null) {
                        Viewer d = new Viewer(idViewers, nickName, Hours);
                        if (myViewers.myViewer == null) {
                            myViewers.myViewer = d;
                        } else {
                            ListViewers p = new ListViewers();
                            p = myViewers;
                            while (p.link != null) {
                                p = p.link;
                            }
                            ListViewers q = new ListViewers();
                            p.link = q;
                            q.myViewer = d;
                        }
                    }
                    str = storage.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(ArchiveViewers .class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            storage.close();
            read.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchiveViewers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchiveViewers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myViewers;
    }
}
