package sinninglopezcamilojose;

import Archives.ArchiveDonations;
import Archives.ArchiveStreamers;
import Archives.ArchiveViewers;
import Lists.ListDonations;
import Lists.ListStreamers;
import Lists.ListViewers;
import java.math.BigDecimal;
import java.util.Scanner;

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
            } else if (command.contains("add")) {
                if (command.equals("addD")) {
                    addDonation();
                } else if (command.equals("addS")) {
                    addStreamer();
                } else if (command.equals("addV")) {
                    addViewer();
                }
                saved = false;
            } else if (command.contains("show")) {
                if (command.equals("showD")) {
                    showDonations();
                } else if (command.equals("showS")) {
                    showStreamers();
                } else if (command.equals("showV")) {
                    showViewers();
                }
            } else if (command.contains("sort")) {
                if (command.equals("sortD")) {
                    sortDonations();
                    showDonations();
                } else if (command.equals("sortS")) {
                    sortStreamers();
                    showStreamers();
                } else if (command.equals("sortV")) {
                    sortViewers();
                    showViewers();
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
                    calcDonations();
                } else if (command.equals("calcS")) {
                    calcStreamers();
                } else if (command.equals("calcV")) {
                    calcViewers();
                }
            } else if (command.contains("help")) {
                help();
            } else if (command.equals("generate")) {
                myDonations = ad.read();
                myStreamers = as.read();
                myViewers = av.read();
            } else if (command.contains("search")) {
                BigDecimal id = new BigDecimal(command.substring(7));
                if (command.contains("searchD")) {
                    searchDonation(id);
                } else if (command.contains("searchS")) {
                    searchStreamer(id);
                } else if (command.contains("searchV")) {
                    searchViewer(id);
                }
            }

            // </editor-fold>
        } while (running);

    }

    public static void close() {
        System.exit(0);
    }

    public static void help() {
        //todos los comandos
        System.out.println("to close the program: close");
        System.out.println("to read the records: generate");
        System.out.println("to add a donation: addD");
        System.out.println("to add a streamer: addS");
        System.out.println("to add a viewer: addV");
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
    }

    //<editor-fold defaultstate="open" desc="Viewer">
    public static void saveViewers() {
        av.write(myViewers);
    }

    public static void deleteViewer() {

    }

    public static void sortViewers() {
        ListViewers p = new ListViewers();
        ListViewers q = new ListViewers();
        p = myViewers;
        

        while ((p.link != null) && (p.link.myViewer != null)) {
            q = myViewers;
            while ((q.link != null) && (q.link.myViewer != null)) {
                if (q.myViewer.Hours > q.link.myViewer.Hours) {
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

    public static void calcViewers() {
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
        while ((p != null) && (p.myViewer != null)) {
            if (p.myViewer.Hours > maxHours) {
                maxHours = p.myViewer.Hours;
                max.myViewer = p.myViewer;
            }
            if (p.myViewer.Hours < minHours) {
                minHours = p.myViewer.Hours;
                min.myViewer = p.myViewer;
            }

            total = total + p.myViewer.Hours;
            nViewers++;
            p = p.link;
        }
        promHours = total / nViewers;

        System.out.println("The viewer with the maximum hours is: ");
        System.out.println("--------------------------------------");
        System.out.println("Id: " + max.myViewer.idViewer);
        System.out.println("Nickname: " + max.myViewer.nickName);
        System.out.println("Hours: " + max.myViewer.Hours);
        System.out.println("--------------------------------------");
        System.out.println("The viewer with the minimum hours is: ");
        System.out.println("--------------------------------------");
        System.out.println("Id: " + min.myViewer.idViewer);
        System.out.println("Nickname: " + min.myViewer.nickName);
        System.out.println("Hours: " + min.myViewer.Hours);
        System.out.println("--------------------------------------");
        System.out.println("Average hours: " + promHours);

    }

    public static void searchViewer(BigDecimal idViewer) {
        ListViewers p = new ListViewers();
        p = myViewers;
        while ((p.myViewer.idViewer != idViewer) && (p != null)) {
            p = p.link;
        }
        if (p.myViewer.idViewer == idViewer) {
            System.out.println("The viewer was found");
            System.out.println("--------------------------");
            System.out.println("Nickname: " + p.myViewer.nickName);
            System.out.println("hours: " + p.myViewer.Hours);
            System.out.println("--------------------------");
        } else {
            System.out.println("The viewer don´t exist");
        }
    }

    public static void addViewer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert the viewer id");
        BigDecimal id = new BigDecimal(sc.next());
        sc.nextLine();
        System.out.println("Insert the viewer nickname");
        String nickname = sc.nextLine();
        System.out.println("Insert the hours watched");
        float hours = sc.nextInt();

        Viewer d = new Viewer(id, nickname, hours);
        ListViewers p = new ListViewers();
        p = myViewers;

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

    public static void showViewers() {
        System.out.println("The viewers list is: ");
        System.out.println("--------------------------");
        ListViewers p = new ListViewers();
        p = myViewers;
        while ((p != null) && (p.myViewer != null)) {
            System.out.println("Viewer: " + p.myViewer.idViewer);
            System.out.println("Username: " + p.myViewer.nickName);
            System.out.println("Followers: " + p.myViewer.Hours);
            System.out.println("---------------------------");
            p = p.link;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="open" desc="Streamer">
    public static void saveStreamers() {
        as.write(myStreamers);
    }

    public static void deleteStreamer() {

    }

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

    public static void calcStreamers() {
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

        System.out.println("The streamer with the maximum followers is: ");
        System.out.println("--------------------------------------");
        System.out.println("Id: " + max.myStreamer.idStreamer);
        System.out.println("Username: " + max.myStreamer.userName);
        System.out.println("Followers: " + max.myStreamer.followers);
        System.out.println("--------------------------------------");
        System.out.println("The streamer with the maximum followers is: ");
        System.out.println("--------------------------------------");
        System.out.println("Id: " + min.myStreamer.idStreamer);
        System.out.println("Username: " + min.myStreamer.userName);
        System.out.println("Followers: " + min.myStreamer.followers);
        System.out.println("--------------------------------------");
        System.out.println("Average followers: " + promFollowers);

    }

    public static void searchStreamer(BigDecimal idStreamer) {
        ListStreamers p = new ListStreamers();
        p = myStreamers;
        while ((p.myStreamer.idStreamer != idStreamer) && (p != null)) {
            p = p.link;
        }
        if (p.myStreamer.idStreamer == idStreamer) {
            System.out.println("The streamer was found");
            System.out.println("------------------------");
            System.out.println("Username: " + p.myStreamer.userName);
            System.out.println("Followers: " + p.myStreamer.followers);
            System.out.println("--------------------------");
        } else {
            System.out.println("The streamer don´t exist");
        }
    }

    public static void addStreamer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert the streamer id");
        BigDecimal id = new BigDecimal(sc.nextInt());
        sc.nextLine();
        System.out.println("Insert the Streamer username");
        String username = sc.nextLine();
        System.out.println("Insert the number of followers");
        float followers = sc.nextInt();

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

    public static void showStreamers() {
        System.out.println("The Streamers list is: ");
        System.out.println("--------------------------");
        ListStreamers p = new ListStreamers();
        p = myStreamers;
        while ((p != null) && (p.myStreamer != null)) {
            System.out.println("Streamer: " + p.myStreamer.idStreamer);
            System.out.println("Username: " + p.myStreamer.userName);
            System.out.println("Followers: " + p.myStreamer.followers);
            System.out.println("---------------------------");
            p = p.link;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="open" desc="Donation">}
    public static void saveDonations() {
        ad.write(myDonations);
    }

    public static void deleteDonation() {

    }

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

    public static void calcDonations() {
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

        System.out.println("The donation with the maximum money is: ");
        System.out.println("--------------------------------------");
        System.out.println("Id: " + max.myDonation.idDonations);
        System.out.println("Message: " + max.myDonation.msg);
        System.out.println("Money: " + max.myDonation.money);
        System.out.println("--------------------------------------");
        System.out.println("The donation with the minimum money is: ");
        System.out.println("--------------------------------------");
        System.out.println("Id: " + min.myDonation.idDonations);
        System.out.println("Message: " + min.myDonation.msg);
        System.out.println("Money: " + min.myDonation.money);
        System.out.println("--------------------------------------");
        System.out.println("Average money: " + promMoney);
    }

    public static void searchDonation(BigDecimal idDonation) {
        ListDonations p = new ListDonations();
        p = myDonations;
        while ((p.myDonation.idDonations.compareTo(idDonation) != 0) && (p != null)) {
            p = p.link;
        }
        if (p.myDonation.idDonations.compareTo(idDonation)==0) {
            System.out.println("The donation was found");
            System.out.println("------------------------");
            System.out.println("Message: " + p.myDonation.msg);
            System.out.println("Money: " + p.myDonation.money);
            System.out.println("--------------------------");
        } else {
            System.out.println("The donation don´t exist");
        }
    }

    public static void addDonation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert the donation id");
        BigDecimal id = new BigDecimal(sc.nextInt());
        sc.nextLine();
        System.out.println("Insert the donation message");
        String msg = sc.nextLine();
        System.out.println("Insert the money");
        float money = sc.nextInt();

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

    public static void showDonations() {
        System.out.println("The donation list is: ");
        System.out.println("--------------------------");
        ListDonations p = new ListDonations();
        p = myDonations;
        while ((p != null) && (p.myDonation != null)) {
            System.out.println("Donation: " + p.myDonation.idDonations);
            System.out.println("Message: " + p.myDonation.msg);
            System.out.println("Money: " + p.myDonation.money);
            System.out.println("---------------------------");
            p = p.link;
        }
    }
    //</editor-fold>

}
