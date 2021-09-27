import java.util.Scanner;

/**
 * Classe Pharmacie
 */
class Pharmacie {
    // La classe Scanner est utilisée pour réaliser une entrée au clavier
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) {

        Client[] clients = new Client[2];
        Medicament[] medicaments = new Medicament[2];
        // Le constructeur prend 2 paramètres, le nom du client et son solde
        clients[0] = new Client("Karamazov", 0.0);
        clients[1] = new Client("Deray", 0.0);
        // Le constructeur prend 3 paramètres, le nom du médicament, son prix
        // et son stock
        medicaments[0] = new Medicament("Transipeg", 20.40, 5);
        medicaments[1] = new Medicament("Effortil", 19.15, 5);

        int choix;
        do {
            choix = menu();
            switch (choix) {
                case 1:
                    achat(clients, medicaments);
                    break;
                case 2:
                    approvisionnement(medicaments);
                    break;
                case 3:
                    affichage(clients, medicaments);
                    break;
                case 4:
                    quitter();
                    return;
            }
        } while (choix < 4);
    }

    // Les méthodes utilitaires
    static int menu() {
        int choix = 0;
        System.out.println();
        System.out.println();
        System.out.println("1 : Achat de médicament");
        System.out.println("2 : Approvisionnement en médicaments");
        System.out.println("3 : Etats des stocks et des crédits");
        System.out.println("4 : Quitter");
        while ((choix != 1) && (choix != 2) && (choix != 3) && (choix != 4)) {
            choix = scanner.nextInt();
        }
        return choix;
    }
    // Méthode pour effectuer un achat
    private static void achat(Client[] clients, Medicament[] medicaments) {
        Client leClient = Client.lireClient(clients); // pour récupérer le client
        Medicament leMedicament = Medicament.lireMedicament(medicaments); // pour récupérer le médicament
        System.out.println("M/Mme "+leClient.getNom()+", combien de "+leMedicament.getNom()+" voulez-vous acheter ?");
        int nb = scanner.nextInt(); // pour récupérer une quantité
        double prix = leMedicament.getPrix() * nb; // pour calculer le prix à payer
        double aPayer = 0;
        if (leClient.getCredit() < 0) { // Si le client a des crédits en plus sur son compte
            System.out.println("Il restait " +leClient.getCredit()*-1+"e sur votre compte.");
            aPayer = prix - leClient.getCredit()*-1; // calcul du prix final à payer
            System.out.println("Il vous reste donc "+aPayer+"e à payer.");
        }
        else { // s'il n'en a pas
            aPayer = prix; // le prix final à payer ne change pas
            System.out.println("Il y a "+aPayer+"e à payer.");
        }
        System.out.println("Combien voulez-vous payer ?");
        double given = scanner.nextDouble(); // pour récupérer la somme dépensée par le client
        double reste = aPayer - given; // calcul du reste après paiement
        leMedicament.minusStock(nb); // on retire du stock le nombre de médicament(s) acheté(s)
        if (reste > 0) {
            leClient.addCredit(reste); // ajout du reste à la dette du client
            System.out.println("Merci pour votre fidélité. "+reste+"e ont été ajouté à votre dette.");
        }
        else {
            leClient.minusCredit(reste*-1); // ajoute de la différence en moins au crédit (= argent en trop à dépenser +tard)
            System.out.println("Merci pour votre fidélité. "+reste*-1+"e on été retiré de votre dette.");
        }
    }
    // Méthode pour approvisionner un médicament
    private static void approvisionnement(Medicament[] medicaments) {
        Medicament leMedicament = Medicament.lireMedicament(medicaments); // pour récupérer le médicament
        leMedicament.approvisionner(); // pour ajouter du stock
    }
    // Méthode affichant la liste des clients et médicaments (ainsi que le nom, crédit, prix, stock)
    private static void affichage(Client[] clients, Medicament[] medicaments) {
        int i = 1;
        System.out.println("Voici la liste des clients : ");
        for (Client client : clients) { // Affichage client
            System.out.println("\tn°"+i+" : \n\t\tNom : "+client.getNom()+"\n\t\tCrédit : "+client.getCredit());
            i += 1;
        }
        i = 1;
        System.out.println("\nVoici la liste des médicaments : ");
        for (Medicament medicament : medicaments) { // Affichage médicament
            System.out.println("\tn°"+i+" : \n\t\tNom : "+medicament.getNom()+"\n\t\tPrix : "+medicament.getPrix()+"\n\t\tStock : "+medicament.getStock());
            i += 1;
        }
    }
    // Méthode pour quitter le programme
    private static void quitter() {
        System.out.println("Programme terminé !");
    }
}
