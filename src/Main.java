import java.util.NoSuchElementException;

public class Main {

    // Compteurs de tests
    private static int total  = 0;
    private static int passed = 0;

    public static void main(String[] args) {
        System.out.println("------------------------");
        System.out.println(" Tests TP2 – Main Class ");
        System.out.println("------------------------\n");

        // Instanciation d’une liste vide
        IListeGroupes<AbonneRevue> liste = new ListeGroupesChainee<>();

        // Quelques abonnés de test
        AbonneRevue kim3 = new AbonneRevue(3, "Kim");
        AbonneRevue max1 = new AbonneRevue(1, "Max");
        AbonneRevue sam7 = new AbonneRevue(7, "Sam");
        AbonneRevue kim4 = new AbonneRevue(4, "Kim"); // même nom, id différent
        AbonneRevue max3 = new AbonneRevue(3, "Max"); // même id que Kim, nom diff.
        AbonneRevue joe3 = new AbonneRevue(3, "Joe");

        //------------------------------------------------
        //  Ajout d’éléments et composition des groupes
        //------------------------------------------------
        check(liste.ajouter(kim3), "ajouter premier élément (Kim, id 3)");
        check(liste.taille() == 1 && liste.nbrGroupes() == 1, "taille = 1, group = 1");

        check(liste.ajouter(max1), "ajouter Max (id 1) crée un 2ᵉ groupe");
        check(liste.taille() == 2 && liste.nbrGroupes() == 2, "taille = 2, group = 2");

        check(liste.ajouter(kim4), "ajout Kim (id 4) – doublon permis dans autre groupe");

        check(!liste.ajouter(new AbonneRevue(3, "Kim")), "doublon refusé même groupe (Kim id 3)");
        check(liste.taille() == 3, "taille reste 3 après rejet doublon");

        //------------------------------------------------
        //  Lecture (obtenirIdGroupe / obtenirElement)
        //------------------------------------------------
        check(liste.obtenirIdGroupe(0) == 3, "obtenirIdGroupe(0) == 3");
        check(liste.obtenirIdGroupe(1) == 1, "obtenirIdGroupe(1) == 1");
        check(liste.obtenirElement(3, 0).equals(kim3), "obtenirElement(3,0) == Kim");

        //------------------------------------------------
        //  Gestion des exceptions attendues
        //------------------------------------------------
        boolean ok;
        ok = false;
        try {
            liste.ajouter(null);
        } catch (NullPointerException e) {
            ok = true;
        }
        check(ok, "ajouter(null) lève NullPointerException");

        ok = false;
        try {
            liste.obtenirIdGroupe(99);
        } catch (NoSuchElementException e) {
            ok = true;
        }
        check(ok, "obtenirIdGroupe position invalide lève NoSuchElementException");

        //------------------------------------------------
        //  Suppression d’éléments et de groupes
        //------------------------------------------------
        check(liste.supprimerElement(max1), "supprimer Max (dernier du groupe 1)");
        check(!liste.groupeExiste(1), "groupe 1 supprimé car vide");
        check(liste.taille() == 2 && liste.nbrGroupes() == 2, "taille = 2 après suppression");

        // Ajout d’un autre élément pour tester remplacer
        check(liste.ajouter(max3), "ajouter Max (id 3) dans groupe 3");

        //------------------------------------------------
        //  Remplacement
        //------------------------------------------------
        check(liste.remplacer(max3, joe3), "remplacer Max par Joe (même groupe 3)");
        check(!liste.elementExiste(max3) && liste.elementExiste(joe3), "remplacement effectif");

        //------------------------------------------------
        //  Récapitulatif
        //------------------------------------------------
        System.out.printf("\nRésultat : %d/%d tests réussis\n", passed, total);
    }

    // Utilitaire de vérification
    private static void check(boolean condition, String message) {
        total++;
        if (condition) {
            passed++;
            System.out.println("[ OK  ] " + message);
        } else {
            System.out.println("[FAIL ] " + message);
        }
    }
}