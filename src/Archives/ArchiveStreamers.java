package Archives;

import Lists.ListStreamers;
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
import sinninglopezcamilojose.Streamer;

public class ArchiveStreamers {
    public void write(ListStreamers myStreamers) {
        File archiveStreamers;
        FileWriter write;
        BufferedWriter storage;
        PrintWriter w;
        String str;
        BigDecimal idStreamers;
        String userName;
        float followers;
        archiveStreamers= new File("Streamers.txt");
        archiveStreamers.delete();
        archiveStreamers = new File("Streamers.txt");
        try {
            write = new FileWriter(archiveStreamers);
            storage = new BufferedWriter(write);
            w = new PrintWriter(storage);
            ListStreamers p = new ListStreamers();
            p = myStreamers;
          
            while ((p!=null)&&(p.myStreamer != null)) {   
                w.write(p.myStreamer.idStreamer.toString());
                w.append("\n"+p.myStreamer.userName);
                w.append("\n"+p.myStreamer.followers);
                w.append("\n");
                p = p.link;
            }

            w.close();
            storage.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchiveStreamers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchiveStreamers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ListStreamers read() {
        ListStreamers myStreamers = new ListStreamers();
        File archiveStreamers;
        FileReader read;
        BufferedReader storage;
        String str;
        BigDecimal idStreamers;
        String userName;
        float followers;
        archiveStreamers = new File("Streamers.txt");
        if(!archiveStreamers.exists()){
            try {
                archiveStreamers.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ArchiveStreamers.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            read = new FileReader(archiveStreamers);
            storage = new BufferedReader(read);
            str = storage.readLine();
            while (str != null) {
                try {
                    idStreamers = new BigDecimal(str);
                    str = storage.readLine();
                    userName = str;
                    str = storage.readLine();
                    followers = Float.parseFloat(str);
                    if (str != null) {
                        Streamer d = new Streamer(idStreamers, userName, followers);
                        if (myStreamers.myStreamer == null) {
                            myStreamers.myStreamer = d;
                        } else {
                            ListStreamers p = new ListStreamers();
                            p = myStreamers;
                            while (p.link != null) {
                                p = p.link;
                            }
                            ListStreamers q = new ListStreamers();
                            p.link = q;
                            q.myStreamer = d;
                        }
                    }
                    str = storage.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(ArchiveStreamers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            storage.close();
            read.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchiveStreamers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchiveStreamers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myStreamers;
    }
}
