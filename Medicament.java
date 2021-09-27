import java.util.Scanner;

/**
 * Classe Medicament
 */
public class Medicament {
    private String nom;
    private double prix;
    private int stock;

    private static Scanner scanner = new Scanner(System.in); // pour récupérer les entrées clavier

    // Méthode d'instanciation
    public Medicament(String nom, double prix, int stock) {
        this.nom = nom;
        this.prix = prix;
        this.stock = stock;
        System.out.println("Le médicament '" + nom + "' a bien été créé.");
    }
    public String getNom() {return nom;} // retourne le nom d'un médicament
    public double getPrix() {return prix;} // retourne le prix d'un médicament
    public int getStock() {return stock;} // retourne le stock d'un médicament
    // Méthode pour chercher un médicament
    public static Medicament lireMedicament(Medicament[] medicaments) {
        System.out.println("Précisez le nom d'un médicament svp : ");
        String entry = scanner.nextLine(); // pour récupérer un nom
        for (Medicament medicament : medicaments) {
            // on test si le nom existe dans la liste des médicaments
            if (medicament.getNom().equalsIgnoreCase(entry) == true)
                return medicament; // si oui, on return le médicament en question
        }
        System.out.println("Le nom du médicament saisi est incorrect. Veuillez réessayer.");
        return lireMedicament(medicaments); // si non, on utilise la récursivité pour recommencer
    }
    // Méthode pour ajouter du stock à un médicament
    public void approvisionner() {
        System.out.println("Veuillez spécifier le nombre à ajouter au stock svp : ");
        int nb = scanner.nextInt(); // pour récupérer un nombre
        this.stock += nb;
    }
    // Méthode pour retirer du stock (si besoin, après un achat)
    public void minusStock(int nb) {
        this.stock -= nb;
    }
}
