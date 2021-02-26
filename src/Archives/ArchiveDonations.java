package Archives;

import Lists.ListDonations;
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
import sinninglopezcamilojose.Donations;

/**
 * Clase para la lectura y escritura del archivo para las donaciones
 * @author camil
 */
public class ArchiveDonations {
        
    /**
     * Escribe la información de la lista en un archivo de tipo .txt
     * @param myDonations 
     */
    public void write(ListDonations myDonations) {
        File archiveDonations;
        FileWriter write;
        BufferedWriter storage;
        PrintWriter w;
        String str;
        BigDecimal idDonations;
        String msg;
        float money;
        archiveDonations = new File("Donations.txt");
        archiveDonations.delete();
        archiveDonations = new File("Donations.txt");
        try {
            write = new FileWriter(archiveDonations);
            storage = new BufferedWriter(write);
            w = new PrintWriter(storage);
            ListDonations p = new ListDonations();
            p = myDonations;
          
            while ((p!=null)&&(p.myDonation != null)) {   
                w.write(p.myDonation.idDonations.toString());
                w.append("\n"+p.myDonation.msg);
                w.append("\n"+p.myDonation.money);
                w.append("\n");
                p = p.link;
            }

            w.close();
            storage.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchiveDonations.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchiveDonations.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * Lee el archivo de donaciones y lo convierte en una lista
     * @return La lista de donaciones
     */
    public ListDonations read() {
        ListDonations myDonations = new ListDonations();
        File archiveDonations;
        FileReader read;
        BufferedReader storage;
        String str;
        BigDecimal idDonations;
        String msg;
        float money;
        archiveDonations = new File("Donations.txt");
        if(!archiveDonations.exists()){
            try {
                archiveDonations.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ArchiveDonations.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            read = new FileReader(archiveDonations);
            storage = new BufferedReader(read);
            str = storage.readLine();
            while (str != null) {
                try {
                    idDonations = new BigDecimal(str);
                    str = storage.readLine();
                    msg = str;
                    str = storage.readLine();
                    money = Float.parseFloat(str);
                    if (str != null) {
                        Donations d = new Donations(idDonations, msg, money);
                        if (myDonations.myDonation == null) {
                            myDonations.myDonation = d;
                        } else {
                            ListDonations p = new ListDonations();
                            p = myDonations;
                            while (p.link != null) {
                                p = p.link;
                            }
                            ListDonations q = new ListDonations();
                            p.link = q;
                            q.myDonation = d;
                        }
                    }
                    str = storage.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(ArchiveDonations.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            storage.close();
            read.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchiveDonations.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchiveDonations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myDonations;
    }
    
}
