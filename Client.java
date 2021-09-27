import java.util.Scanner;

/**
 * Classe Client
 */
public class Client {
    private String nom;
    private double credit;

    private static Scanner scanner = new Scanner(System.in); // pour récupérer les entrées clavier

    // Méthode d'instanciation
    public Client(String nom, double credit) {
        this.nom = nom;
        this.credit = credit;
        System.out.println("Le client '" + nom + "' a bien été créé.");
    }
    public String getNom() {return nom;} // retourne le nom d'un client
    public double getCredit() {return credit;} // retourne le crédit d'un client
    // Méthode pour chercher un client
    public static Client lireClient(Client[] clients) {
        System.out.println("Précisez le nom d'un client svp : ");
        String entry = scanner.nextLine(); // pour récupérer un nom
        for (Client client : clients) {
            // on test si le nom existe dans la liste des clients
            if (client.getNom().equalsIgnoreCase(entry) == true)
                return client; // si oui, on return le client en question
        }
        System.out.println("Le nom du client saisi est incorrect. Veuillez réessayer.");
        return lireClient(clients); // si non, on utilise la récursivité pour recommencer
    }
    // Méthode pour ajouter des crédits à un client (si besoin, après un achat)
    public void addCredit(double nb) {
        credit += nb;
    }
    // Méthode pour retirer des crédits à un client
    public void minusCredit(double nb) {
        credit -= nb;
    }
}
