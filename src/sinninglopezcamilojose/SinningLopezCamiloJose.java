package sinninglopezcamilojose;

import Archives.ArchiveDonations;
import Archives.ArchiveStreamers;
import Archives.ArchiveViewers;
import Lists.ListDonations;
import Lists.ListStreamers;
import Lists.ListViewers;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Clase principal
 * @author camil
 */
public class SinningLopezCamiloJose {

    public static ListDonations myDonations = new ListDonations();
    public static ArchiveDonations ad = new ArchiveDonations();
    public static ListStreamers myStreamers = new ListStreamers();
    public static ArchiveStreamers as = new ArchiveStreamers();
    public static ListViewers myViewers = new ListViewers();
    public static ArchiveViewers av = new ArchiveViewers();

    public static boolean saved = false;
    
    public static void main(String[] args) {

        FE f = new FE();
        f.setVisible(true);
        Scanner sc = new Scanner(System.in);

        boolean running = true;
        boolean saved = true;
        boolean generated = false;
        System.out.println("(Type help to get the commands)");
        do {
            System.out.println("Insert a command:");
            System.out.print(">");
            String command = sc.nextLine();

            // !Validated
            // <editor-fold defaultstate="open" desc="commands">
            if (command.equals("close")) {
                if (!saved) {
                    System.out.println("No write since last change. Are you sure? Y/N");
                    command = sc.next();
                    if (command.equals("Y")) {
                        running = false;
                    }
                } else {
                    running = false;
                }
            } else if (command.contains("help")) {
                help();
            } else if (command.equals("generate")) {
                myDonations = ad.read();
                myStreamers = as.read();
                myViewers = av.read();
                generated = true;
            } 
            if (generated) {
                if (command.contains("add")) {
                    if (command.contains("addD")) {
                        String[] temp = command.split("\\(");
                        String[] temp2 = temp[1].split("\\)");
                        String[] temp3 = temp2[0].split(",");
                        BigDecimal id = new BigDecimal(temp[0]);
                        String msg = temp[1];
                        float money = Float.parseFloat(temp[2]);
                        addDonation(id, msg, money);
                    } else if (command.contains("addS")) {
                        String[] temp = command.split("\\(");
                        String[] temp2 = temp[1].split("\\)");
                        String[] temp3 = temp2[0].split(",");
                        BigDecimal id = new BigDecimal(temp[0]);
                        String username = temp[1];
                        float followers = Float.parseFloat(temp[2]);
                        addStreamer(id, username, followers);
                    } else if (command.contains("addV")) {
                        String[] temp = command.split("\\(");
                        String[] temp2 = temp[1].split("\\)");
                        String[] temp3 = temp2[0].split(",");
                        BigDecimal id = new BigDecimal(temp[0]);
                        String nickname = temp[1];
                        float hours = Float.parseFloat(temp[2]);
                        addViewer(id, nickname, hours);
                    }
                    saved = false;
                } else if (command.contains("show")) {
                    if (command.equals("showD")) {
                        System.out.println(showDonations());
                    } else if (command.equals("showS")) {
                        System.out.println(showStreamers());
                    } else if (command.equals("showV")) {
                        System.out.println(showViewers());
                    }
                } else if (command.contains("sort")) {
                    if (command.equals("sortD")) {
                        sortDonations();
                        System.out.println(showDonations());
                    } else if (command.equals("sortS")) {
                        sortStreamers();
                        System.out.println(showStreamers());
                    } else if (command.equals("sortV")) {
                        sortViewers();
                        System.out.println(showViewers());
                    }
                    saved = false;
                } else if (command.equals("save")) {
                    saveDonations();
                    saveStreamers();
                    saveViewers();
                    saved = true;
                    System.out.println("Saved");
                } else if (command.contains("calc")) {
                    if (command.equals("calcD")) {
                        System.out.println(calcDonations());
                    } else if (command.equals("calcS")) {
                        System.out.println(calcStreamers());
                    } else if (command.equals("calcV")) {
                        System.out.println(calcViewers());
                    }
                } else if (command.contains("search")) {
                    String[] temp = command.split("\\(");
                    String[] temp2 = temp[1].split("\\)");
                    
                    BigDecimal id = new BigDecimal(temp2[0]);
                    if (command.contains("searchD")) {
                        System.out.println(searchDonation(id));
                    } else if (command.contains("searchS")) {
                        System.out.println(searchStreamer(id));
                    } else if (command.contains("searchV")) {
                        System.out.println(searchViewer(id));
                    }
                }
            }else{
                System.out.println("Write the command \"generate\" to continue");
            }
                // </editor-fold>
        } while (running);

    }
    
    /**
     * Termina la ejecución del programa
     */
    public static void close() {
        System.exit(0);
    }
    
    /**
     * Muestra los comandos que posee el programa
     */
    public static void help() {
        //todos los comandos
        System.out.println("to close the program: close");
        System.out.println("to read the records: generate");
        System.out.println("to add a donation: addD(id,message,money)");
        System.out.println("to add a streamer: addS(id,username,followers)");
        System.out.println("to add a viewer: addV(id,nickname,hours)");
        System.out.println("to show donations: showD");
        System.out.println("to show streamers: showS");
        System.out.println("to show viewers: showV");
        System.out.println("to sort donations: sortD");
        System.out.println("to sort streamers: sortS");
        System.out.println("to sort viewers: sortV");
        System.out.println("to save changes: save");
        System.out.println("to show max, min, and average of donations: calcD");
        System.out.println("to show max, min, and average of streamer: calcS");
        System.out.println("to show max, min, and average of viewer: calcV");
        System.out.println("to search a donation: searchD(id)");
        System.out.println("to search a streamer: searchS(id)");
        System.out.println("to search a viewer: searchV(id)");
    }

    //<editor-fold defaultstate="open" desc="Viewer">
    
    /**
     * Escribe en el archivo los cambios hechos en la lista Viewers
     */
    public static void saveViewers() {
        av.write(myViewers);
    }
    
    /**
     * Ordena de menor a mayor la lista de Viewers segun el numero de horas vistas
     */
    public static void sortViewers() {
        ListViewers p = new ListViewers();
        ListViewers q = new ListViewers();
        p = myViewers;
        
        while ((p.link != null) && (p.link.myViewer != null)) {
            q = myViewers;
            while ((q.link != null) && (q.link.myViewer != null)) {
                if (q.myViewer.Hours > q.link.myViewer.Hours) {
                    //Se intercambian q y q.link
                    ListViewers temp = new ListViewers();
                    temp.myViewer = q.myViewer;
                    q.myViewer = q.link.myViewer;
                    q.link.myViewer = temp.myViewer;
                }
                q = q.link;
            }
            p = p.link;
        }
    }

    /**
     * Encuentra el máximo, mínimo y el promedio de las horas vistas por los viewers
     * @return los resultados con formato
     */
    public static String calcViewers() {
        //Inicialización de variables
        ListViewers min = new ListViewers();
        float minHours;
        ListViewers max = new ListViewers();
        float maxHours;

        float promHours;
        float total = 0;
        int nViewers = 0;

        ListViewers p = new ListViewers();
        p = myViewers;
        minHours = p.myViewer.Hours;
        maxHours = p.myViewer.Hours;
        min.myViewer = p.myViewer;
        max.myViewer = p.myViewer;
        //Se recorre la lista
        while ((p != null) && (p.myViewer != null)) {
            //Mayor
            if (p.myViewer.Hours > maxHours) {
                maxHours = p.myViewer.Hours;
                max.myViewer = p.myViewer;
            }
            //Menor
            if (p.myViewer.Hours < minHours) {
                minHours = p.myViewer.Hours;
                min.myViewer = p.myViewer;
            }

            total = total + p.myViewer.Hours;
            nViewers++;
            p = p.link;
        }
        promHours = total / nViewers;

        String result = "The viewer with the maximum hours is: "
                + "\n--------------------------------------"
                + "\nId: " + max.myViewer.idViewer
                + "\nNickname: " + max.myViewer.nickName
                + "\nHours: " + max.myViewer.Hours
                + "\n--------------------------------------"
                + "\nThe viewer with the minimum hours is: "
                + "\n--------------------------------------"
                + "\nId: " + min.myViewer.idViewer
                + "\nNickname: " + min.myViewer.nickName
                + "\nHours: " + min.myViewer.Hours
                + "\n--------------------------------------"
                + "\nAverage hours: " + promHours;
        return result;
    }
    
    /**
     * Busca un viewer dada su id
     * @param idViewer
     * @return La correspondiente información del viewer buscado
     */
    public static String searchViewer(BigDecimal idViewer) {
        ListViewers p = new ListViewers();
        p = myViewers;
        //Se recorre la lista hasta encontrar el id
        while ((p.myViewer.idViewer != idViewer) && (p != null)) {
            p = p.link;
        }
        
        //Se valida y se entrega el resultado
        String result;
        if (p.myViewer.idViewer == idViewer) {
            result = "The viewer was found"
                    + "\n--------------------------"
                    + "\nNickname: " + p.myViewer.nickName
                    + "\nhours: " + p.myViewer.Hours
                    + "\n--------------------------";
        } else {
            result = "The viewer don´t exist";
        }
        return result;
    }

    /**
     * Agrega un elemento a la lista de viewers
     * @param id
     * @param nickname
     * @param hours 
     */
    public static void addViewer(BigDecimal id, String nickname, float hours) {
        Viewer d = new Viewer(id, nickname, hours); //Nuevo objeto
        ListViewers p = new ListViewers();
        p = myViewers;
        
        //Se inserta el nuevo objeto al final de la lista
        if (p.myViewer == null) {
            myViewers.myViewer = d;
        } else {
            while (p.link != null) {
                p = p.link;
            }
            ListViewers q = new ListViewers();
            p.link = q;
            q.myViewer = d;
        }
    }
    
    /**
     * Muestra los elementos que hay en el momento en la lista de viewers
     * @return La lista con formato
     */
    public static String showViewers() {
        String result;
        result = "The viewers list is: "
                + "\n--------------------------";
        ListViewers p = new ListViewers();
        p = myViewers;
        while ((p != null) && (p.myViewer != null)) {
            result = result + "\nViewer: " + p.myViewer.idViewer
                    + "\nUsername: " + p.myViewer.nickName
                    + "\nFollowers: " + p.myViewer.Hours
                    + "\n---------------------------";
            p = p.link;
        }
        return result;
    }
    //</editor-fold>

    //<editor-fold defaultstate="open" desc="Streamer">
    
    /**
     * Escribe en el archivo los cambios hechos en la lista Streamers
     */
    public static void saveStreamers() {
        as.write(myStreamers);
    }

    /**
     * Ordena de menor a mayor la lista de streamers segun el numero de seguidores
     */
    public static void sortStreamers() {
        ListStreamers p = new ListStreamers();
        ListStreamers q = new ListStreamers();
        p = myStreamers;

        while ((p.link != null) && (p.link.myStreamer != null)) {
            q = myStreamers;
            while ((q.link != null) && (q.link.myStreamer != null)) {
                if (q.myStreamer.followers > q.link.myStreamer.followers) {
                    ListStreamers temp = new ListStreamers();
                    temp.myStreamer = q.myStreamer;
                    q.myStreamer = q.link.myStreamer;
                    q.link.myStreamer = temp.myStreamer;
                }
                q = q.link;
            }
            p = p.link;
        }
    }

    /**
     * Encuentra el máximo, mínimo y el promedio de los seguidores que poseen los streamers
     * @return los resultados con formato
     */
    public static String calcStreamers() {
        ListStreamers min = new ListStreamers();
        float minFollowers;
        ListStreamers max = new ListStreamers();
        float maxFollowers;

        float promFollowers;
        float total = 0;
        int nStreamers = 0;

        ListStreamers p = new ListStreamers();
        p = myStreamers;
        minFollowers = p.myStreamer.followers;
        maxFollowers = p.myStreamer.followers;
        min.myStreamer = p.myStreamer;
        max.myStreamer = p.myStreamer;
        while ((p != null) && (p.myStreamer != null)) {
            if (p.myStreamer.followers > maxFollowers) {
                maxFollowers = p.myStreamer.followers;
                max.myStreamer = p.myStreamer;
            }
            if (p.myStreamer.followers < minFollowers) {
                minFollowers = p.myStreamer.followers;
                min.myStreamer = p.myStreamer;
            }

            total = total + p.myStreamer.followers;
            nStreamers++;
            p = p.link;
        }
        promFollowers = total / nStreamers;
        String result;
        result = "\nThe streamer with the maximum followers is: "
                + "\n--------------------------------------"
                + "\nId: " + max.myStreamer.idStreamer
                + "\nUsername: " + max.myStreamer.userName
                + "\nFollowers: " + max.myStreamer.followers
                + "\n--------------------------------------"
                + "\nThe streamer with the maximum followers is: "
                + "\n--------------------------------------"
                + "\nId: " + min.myStreamer.idStreamer
                + "\nUsername: " + min.myStreamer.userName
                + "\nFollowers: " + min.myStreamer.followers
                + "\n--------------------------------------"
                + "\nAverage followers: " + promFollowers;
        return result;

    }

    /**
     * Busca un streamer dada su id
     * @param idStreamer
     * @return La correspondiente información del streamer buscado
     */
    public static String searchStreamer(BigDecimal idStreamer) {
        ListStreamers p = new ListStreamers();
        p = myStreamers;
        while ((p.myStreamer.idStreamer != idStreamer) && (p != null)) {
            p = p.link;
        }
        String result;
        if (p.myStreamer.idStreamer == idStreamer) {
            result = "The streamer was found"
                    + "\n------------------------"
                    + "\nUsername: " + p.myStreamer.userName
                    + "\nFollowers: " + p.myStreamer.followers
                    + "\n--------------------------";
        } else {
            result = "The streamer don´t exist";
        }
        return result;
    }

    /**
     * Agrega un elemento a la lista de streamers
     * @param id
     * @param username
     * @param followers 
     */
    public static void addStreamer(BigDecimal id, String username, float followers) {

        Streamer d = new Streamer(id, username, followers);
        ListStreamers p = new ListStreamers();
        p = myStreamers;

        if (p.myStreamer == null) {
            myStreamers.myStreamer = d;
        } else {
            while (p.link != null) {
                p = p.link;
            }
            ListStreamers q = new ListStreamers();
            p.link = q;
            q.myStreamer = d;
        }
    }

    /**
     * Muestra los elementos que hay en el momento en la lista de streamers
     * @return La lista con formato
     */
    public static String showStreamers() {
        String result = "The Streamers list is: "
                + "\n--------------------------";
        ListStreamers p = new ListStreamers();
        p = myStreamers;
        while ((p != null) && (p.myStreamer != null)) {
            result = result + "\nStreamer: " + p.myStreamer.idStreamer
                    + "\nUsername: " + p.myStreamer.userName
                    + "\nFollowers: " + p.myStreamer.followers
                    + "\n---------------------------";
            p = p.link;
        }
        return result;
    }
    //</editor-fold>

    //<editor-fold defaultstate="open" desc="Donation">}
    
    /**
     * Escribe en el archivo los cambios hechos en la lista donaciones
     */
    public static void saveDonations() {
        ad.write(myDonations);
    }

    /**
     * Ordena de menor a mayor la lista de donaciones segun cantidad de dinero
     */
    public static void sortDonations() {
        ListDonations p = new ListDonations();
        ListDonations q = new ListDonations();
        p = myDonations;

        while ((p.link != null) && (p.link.myDonation != null)) {
            q = myDonations;
            while ((q.link != null) && (q.link.myDonation != null)) {
                if (q.myDonation.money > q.link.myDonation.money) {
                    ListDonations temp = new ListDonations();
                    temp.myDonation = q.myDonation;
                    q.myDonation = q.link.myDonation;
                    q.link.myDonation = temp.myDonation;
                }
                q = q.link;
            }
            p = p.link;
        }
    }

    /**
     * Encuentra el máximo, mínimo y el promedio de la cantidad de las donaciones
     * @return los resultados con formato
     */
    public static String calcDonations() {
        ListDonations min = new ListDonations();
        float minMoney;
        ListDonations max = new ListDonations();
        float maxMoney;

        float promMoney;
        float total = 0;
        int nDonations = 0;

        ListDonations p = new ListDonations();
        p = myDonations;
        minMoney = p.myDonation.money;
        maxMoney = p.myDonation.money;
        min.myDonation = p.myDonation;
        max.myDonation = p.myDonation;
        while ((p != null) && (p.myDonation != null)) {
            if (p.myDonation.money > maxMoney) {
                maxMoney = p.myDonation.money;
                max.myDonation = p.myDonation;
            }
            if (p.myDonation.money < minMoney) {
                minMoney = p.myDonation.money;
                min.myDonation = p.myDonation;
            }

            total = total + p.myDonation.money;
            nDonations++;
            p = p.link;
        }
        promMoney = total / nDonations;

        String result = "The donation with the maximum money is: "
                + "\n--------------------------------------"
                + "\nId: " + max.myDonation.idDonations
                + "\nMessage: " + max.myDonation.msg
                + "\nMoney: " + max.myDonation.money
                + "\n--------------------------------------"
                + "\nThe donation with the minimum money is: "
                + "\n--------------------------------------"
                + "\nId: " + min.myDonation.idDonations
                + "\nMessage: " + min.myDonation.msg
                + "\nMoney: " + min.myDonation.money
                + "\n--------------------------------------"
                + "\nAverage money: " + promMoney;
        return result;
    }

    /**
     * Busca una donación dada su id
     * @param idDonation
     * @return La correspondiente información de la donación buscado
     */
    public static String searchDonation(BigDecimal idDonation) {
        ListDonations p = new ListDonations();
        p = myDonations;
        while ((p.myDonation.idDonations.compareTo(idDonation) != 0) && (p != null)) {
            p = p.link;
        }
        String result;
        if (p.myDonation.idDonations.compareTo(idDonation) == 0) {
            result = "The donation was found"
                    + "\n------------------------"
                    + "\nMessage: " + p.myDonation.msg
                    + "\nMoney: " + p.myDonation.money
                    + "\n--------------------------";
        } else {
            result = "The donation don´t exist";
        }
        return result;
    }

    /**
     * Agrega un elemento a la lista de donaciones
     * @param id
     * @param msg
     * @param money
     */
    public static void addDonation(BigDecimal id, String msg, float money) {
        Donations d = new Donations(id, msg, money);
        ListDonations p = new ListDonations();
        p = myDonations;

        if (p.myDonation == null) {
            myDonations.myDonation = d;
        } else {
            while (p.link != null) {
                p = p.link;
            }
            ListDonations q = new ListDonations();
            p.link = q;
            q.myDonation = d;
        }
    }

    /**
     * Muestra los elementos que hay en el momento en la lista de donaciones
     * @return La lista con formato
     */
    public static String showDonations() {
        String result = "The donation list is: "
                + "\n--------------------------";
        ListDonations p = new ListDonations();
        p = myDonations;
        while ((p != null) && (p.myDonation != null)) {
            result = result + "\nDonation: " + p.myDonation.idDonations
                    + "\nMessage: " + p.myDonation.msg
                    + "\nMoney: " + p.myDonation.money
                    + "\n---------------------------";
            p = p.link;
        }
        return result;
    }
    //</editor-fold>

}
