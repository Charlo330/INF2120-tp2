import java.util.NoSuchElementException;

public class ListeGroupesChainee<T extends IGroupe> implements IListeGroupes<T> {

    /**
     * La liste de groupes est implémentée comme une liste chaînée.
     * Chaque maillon de la liste contient un groupe et un pointeur vers le maillon suivant.
     */
    private MaillonListe<T> elements = null;

    /**
     * Le nombre total d'éléments dans la liste de groupes.
     * Il est mis à jour à chaque ajout ou suppression d'élément.
     */
    private int nbrElements = 0;

    /**
     * Constructeur qui initialise une liste de groupes vide.
     * La liste ne contient aucun élément.
     */
    public ListeGroupesChainee() {
        this.elements = null; //liste de groupe vide
        this.nbrElements = 0; //aucun element
    }

    /**
     * Constructeur qui initialise une liste de groupes avec un maillon de groupe.
     *
     * @param element l'élément à ajouter.
     */
    @Override
    public boolean ajouter(T element) {
        // si aucun élement n'est fourni, on ne peut pas l'ajouter
        if (element == null) {
            throw new NullPointerException("L'élément ne peut pas être null.");
        }
        // si la liste est vide, on crée un nouveau maillon avec le groupe
        if (this.elements == null) {
            this.elements = new MaillonListe<>(new MaillonGroupe<>(element));
            this.nbrElements++;
            return true;
        }

        MaillonListe<T> liste = this.trouverListe(element.getId());

        // si le groupe n'existe pas, on ajoute un nouveau groupe avec l'élément
        if (liste == null) {
            MaillonListe<T> dernierMaillon = this.dernierMaillonListe(this.elements);
            dernierMaillon.setSuivant(new MaillonListe<>(new MaillonGroupe<>(element)));
            this.nbrElements++;
            return true;
        }
        // si le groupe existe, on vérifie si l'élément existe déjà dans le groupe
        else if (this.trouverElementDansGroupe(liste.getInfo(), element) == null) {
            MaillonGroupe<T> dernierGroupe = dernierMaillonGroupe(liste.getInfo());
            dernierGroupe.setSuivant(new MaillonGroupe<>(element));
            this.nbrElements++;
            return true;
        }

        return false;
    }

    /**
     * Supprime un groupe de la liste de groupes en fonction de son ID.
     * Si le groupe n'existe pas, la liste reste inchangée.
     *
     * @param idGroupe l'ID du groupe à supprimer.
     * @return le nombre d'éléments supprimés du groupe, ou 0 si le groupe n'existe pas.
     */
    @Override
    public int supprimerGroupe(int idGroupe) {
        // vérifier si le premier élément de la liste est le groupe à supprimer
        if (this.elements != null && this.elements.getInfo().getInfo().getId() == idGroupe) {
            int tailleGroupe = this.taille(idGroupe);
            this.elements = this.elements.getSuivant(); // supprimer le premier
            this.nbrElements -= tailleGroupe;
            return tailleGroupe;
        }

        MaillonListe<T> listePrecedent = this.trouverListePrecedent(idGroupe);

        if (listePrecedent == null) {
            return 0; // le groupe n'existe pas, rien à supprimer
        }

        int tailleGroupe = this.taille(idGroupe);

        if (listePrecedent.getSuivant().getSuivant() == null) {
            listePrecedent.setSuivant(null); // si c'est le dernier groupe, on le supprime
            return tailleGroupe;
        }

        // supprimer le groupe en le reliant le précédent au suivant
        listePrecedent.setSuivant(listePrecedent.getSuivant().getSuivant());

        this.nbrElements -= tailleGroupe; // mettre à jour le nombre total d'éléments

        return tailleGroupe;
    }

    /**
     * Supprime un élément de son groupe d'appartenance.
     * Si le groupe devient vide après la suppression, il est également supprimé de la liste.
     *
     * @param element l'élément à supprimer de la liste de groupes.
     * @return true si l'élément a été supprimé, false sinon.
     * @throws NullPointerException si l'élément est null.
     */
    @Override
    public boolean supprimerElement(T element) {
        if (element == null) {
            throw new NullPointerException("L'élément ne peut pas être null.");
        }

        MaillonListe<T> liste = this.trouverListe(element.getId());

        if (liste == null) return false;

        if (!elementExiste(element)) {
            return false; // l'élément n'existe pas dans le groupe
        }

        MaillonGroupe<T> precedent = this.trouverElementPrecedentDansGroupe(liste.getInfo(), element);

        if (precedent == null) {
            // si l’élément est le premier du groupe
            liste.setInfo(liste.getInfo().getSuivant());
        } else {
            precedent.setSuivant(precedent.getSuivant().getSuivant());
        }

        this.nbrElements--;
        return true;
    }

    /**
     * Permet d'obtenir le numéro d'identification du groupe à la position donnée.
     * La position doit être valide, c'est-à-dire comprise entre 0 et nbrGroupes() - 1 inclusivement.
     *
     * @param position la position du groupe dont on veut obtenir l'ID.
     * @return l'ID du groupe à la position donnée.
     * @throws NoSuchElementException si la position n'est pas valide.
     */
    @Override
    public int obtenirIdGroupe(int position) {
        int index = position;
        MaillonListe<T> groupe = this.elements;

        while (groupe != null && index > 0) {
            groupe = groupe.getSuivant();
            index--;
        }

        if (groupe != null) {
            return groupe.getInfo().getInfo().getId();
        }
        else {
            throw new NoSuchElementException("La position donnée n'est pas valide dans cette liste de groupes.");
        }
    }

    /**
     * Permet d'obtenir l'élément à la position donnée dans le groupe dont l'ID est idGroupe.
     * La position doit être valide, c'est-à-dire comprise entre 0 et taille(idGroupe) - 1 inclusivement.
     *
     * @param idGroupe l'ID du groupe dans lequel rechercher l'élément.
     * @param position la position de l'élément à retourner dans le groupe.
     * @return l'élément à la position donnée dans le groupe dont l'ID est idGroupe.
     * @throws NoSuchElementException si le groupe n'existe pas ou si la position n'est pas valide.
     */
    @Override
    public T obtenirElement(int idGroupe, int position) {
        MaillonListe<T> liste = this.trouverListe(idGroupe);

        if (liste == null) {
            throw new NoSuchElementException("Le groupe avec l'ID " + idGroupe +
                    " n'existe pas dans cette liste de groupes.");
        }

        MaillonGroupe<T> groupe = liste.getInfo();

        if (groupe == null) {
            throw new NoSuchElementException("Le groupe avec l'ID " + idGroupe +
                    " est vide, il n'y a pas d'éléments à cette position.");
        }

        int index = position;

        while (groupe != null && index > 0) {
            groupe = groupe.getSuivant();
            index--;
        }

        if (groupe != null) {
            return groupe.getInfo();
        } else {
            throw new NoSuchElementException("La position donnée n'est pas valide " +
                    "dans le groupe avec l'ID " + idGroupe + ".");
        }
    }

    /**
     * Permet d'obtenir le nombre total d'éléments dans la liste de groupes.
     *
     * @return le nombre total d'éléments dans la liste de groupes.
     */
    @Override
    public int taille() {
        return this.nbrElements;
    }

    /**
     * Permet d'obtenir le nombre d'éléments dans le groupe dont l'ID est idGroupe.
     * Si le groupe n'existe pas, retourne -1.
     *
     * @param idGroupe l'ID du groupe dont on veut obtenir le nombre d'éléments.
     * @return le nombre d'éléments dans le groupe, ou -1 si le groupe n'existe pas.
     */
    @Override
    public int taille(int idGroupe) {
        MaillonListe<T> liste = this.trouverListe(idGroupe);

        if (liste == null) {
            return -1; // le groupe n'existe pas
        }

        MaillonGroupe<T> groupe = liste.getInfo();

        if (groupe == null) {
            return 0; // le groupe est vide
        }

        int taille = 0;

        while (groupe != null) {
            taille++;
            groupe = groupe.getSuivant();
        }
        return taille;
    }

    /**
     * Permet d'obtenir le nombre de groupes dans la liste de groupes.
     *
     * @return le nombre de groupes dans la liste de groupes.
     */
    @Override
    public int nbrGroupes() {
        if (this.elements == null) {
            return 0; // aucune liste de groupes
        }

        return nbrGroupes(this.elements.getInfo()) + 1; // compte le groupe actuel et les suivants
    }

    /**
     * Vérifie si un groupe existe dans la liste de groupes en fonction de son ID.
     *
     * @param idGroupe l'ID du groupe à vérifier.
     * @return true si le groupe existe, false sinon.
     */
    @Override
    public boolean groupeExiste(int idGroupe) {
        MaillonListe<T> liste = this.trouverListe(idGroupe);
        return liste != null; // retourne true si le groupe existe, false sinon
    }

    /**
     * Vérifie si un élément existe dans la liste de groupes.
     * Un élément existe s'il est présent dans le groupe dont l'ID est element.getId().
     *
     * @param element l'élément à vérifier.
     * @return true si l'élément existe, false sinon.
     * @throws NullPointerException si l'élément est null.
     */
    @Override
    public boolean elementExiste(T element) {
        if (element == null) {
            throw new NullPointerException("L'élément ne peut pas être null.");
        }

        MaillonListe<T> liste = this.trouverListe(element.getId());

        if (liste == null) {
            return false; // le groupe n'existe pas
        }

        return this.trouverElementDansGroupe(liste.getInfo(), element) != null; // vérifie si l'élément existe dans le groupe
    }

    /**
     * Remplace un élément dans son groupe d'appartenance.
     * Si l'élément n'existe pas, la méthode retourne false.
     *
     * @param element1 l'élément à remplacer.
     * @param element2 le nouvel élément qui remplacera element1.
     * @return true si l'élément a été remplacé, false sinon.
     * @throws NullPointerException si l'un des éléments est null.
     */
    @Override
    public boolean remplacer(T element1, T element2) {
        // si l'un des éléments est null, on ne peut pas le remplacer
        if (element1 == null || element2 == null) {
            throw new NullPointerException("Les éléments ne peuvent pas être null.");
        }

        // on ne peut pas remplacer un élément par lui-même
        if (element1.getId() != element2.getId()) {
            return false;
        }

        MaillonListe<T> liste = this.trouverListe(element1.getId());
        if (liste == null) {
            return false; // le groupe n'existe pas
        }

        MaillonGroupe<T> maillonGroupe = this.trouverElementDansGroupe(liste.getInfo(), element1);

        // si le groupe n'existe pas, on ne peut pas remplacer l'élément
        if (maillonGroupe == null) {
            return false;
        }

        // si l'élément2 existe déjà dans le groupe, on ne peut pas le remplacer
        if (this.trouverElementDansGroupe(liste.getInfo(), element2) != null) {
            return false;
        }

        maillonGroupe.setInfo(element2);

        return true;
    }

    /**
     * Retourne un groupe en fonction de l'id de ses groupes.
     *
     * @return une chaîne de caractères représentant la liste de groupes.
     */
    private MaillonListe<T> trouverListe(int id) {
        if (elements != null && elements.getInfo().getInfo().getId() == id) {
            return elements;
        }

        MaillonListe<T> precedent = trouverListePrecedent(id);
        if (precedent == null) {
            return null;
        }

        return precedent.getSuivant();
    }


    /**
     * Trouve le maillonListe précédent à celui dont l'ID de ses groupes est donné.
     *
     * @param id l'ID du groupe dont on veut trouver le précédent.
     * @return le maillon du groupe précédent, ou null si aucun groupe précédent n'est trouvé.
     */
    private MaillonListe<T> trouverListePrecedent(int id) {
        MaillonListe<T> precedent = null;
        MaillonListe<T> courant = elements;
        while (courant != null) {
            if (courant.getInfo() == null || courant.getInfo().getInfo() == null) {
                courant = courant.getSuivant();
                continue;
            }
            int courantId = courant.getInfo().getInfo().getId();
            if (courantId == id) {
                return precedent; // trouvé
            }
            precedent = courant;
            courant = courant.getSuivant();
        }
        return null; // non trouvé
    }

    /**
     * Trouve un élément dans un groupe donné.
     *
     * @param groupe le groupe dans lequel chercher l'élément.
     * @param element l'élément à chercher.
     * @return le maillon du groupe contenant l'élément, ou null si l'élément n'existe pas.
     */
    private MaillonGroupe<T> trouverElementDansGroupe(MaillonGroupe<T> groupe, T element) {
        MaillonGroupe<T> courant = groupe;

        while (courant != null) {
            if (courant.getInfo().equals(element)) {
                return courant; // trouvé
            }
            courant = courant.getSuivant();
        }

        return null; // non trouvé
    }
    /**
     * Trouve l'élément précédent dans un groupe donné.
     *
     * @param groupe le groupe dans lequel chercher l'élément.
     * @param element l'élément dont on veut trouver le précédent.
     * @return le maillon précédent de l'élément, ou null si l'élément n'existe pas.
     */
    private MaillonGroupe<T> trouverElementPrecedentDansGroupe(MaillonGroupe<T> groupe, T element) {
        MaillonGroupe<T> courant = groupe;
        MaillonGroupe<T> precedent = null;

        while (courant != null) {
            if (courant.getInfo().equals(element)) {
                return precedent; // retourne le maillon précédent
            }
            precedent = courant;
            courant = courant.getSuivant();
        }
        return null; // aucun élément trouvé
    }

    /**
     * Retourne le dernier maillon de la liste de maillons.
     *
     * @param maillon le maillon de départ pour trouver le dernier maillon.
     * @return le dernier maillon de la liste, ou null si la liste est vide.
     */
    private MaillonListe<T> dernierMaillonListe(MaillonListe<T> maillon) {
        if (maillon == null) {
            return null;
        }

        while (maillon.getSuivant() != null) {
            maillon = maillon.getSuivant();
        }
        return maillon;
    }

    /**
     * Retourne le dernier maillon du groupe.
     *
     * @param groupe le groupe dont on veut trouver le dernier maillon.
     * @return le dernier maillon du groupe, ou null si le groupe est vide.
     */
    private MaillonGroupe<T> dernierMaillonGroupe(MaillonGroupe<T> groupe) {
        if (groupe == null) {
            return null;
        }

        while (groupe.getSuivant() != null) {
            groupe = groupe.getSuivant();
        }
        return groupe;
    }

    /**
     * Compte le nombre de groupes dans la liste de groupes.
     *
     * @param groupe le groupe à partir duquel commencer le comptage.
     * @return le nombre de groupes dans la liste de groupes.
     */
    private int nbrGroupes(MaillonGroupe<T> groupe) {

        if (groupe == null) {
            return 0; // aucun groupe
        }

        return nbrGroupes(groupe.getSuivant()) + 1;
    }
}
