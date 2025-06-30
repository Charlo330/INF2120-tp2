
import java.util.NoSuchElementException;

/**
 * Cette interface definit les methodes pour la gestion d'une liste de groupes.
 * 
 * @author Melanie Lord
 * @version E25
 * @param <T> le type des elements dans la listeGroupes. Doit implementer 
 *            l'interface IGroupe. 
 */
public interface IListeGroupes <T extends IGroupe> {
   
   /**
    * Ajoute l'element donne a cette ListeGroupes. 
    * 
    * Si le groupe dont le numero d'identification est element.getId() existe deja 
    * dans cette listeGroupes, l'element est ajoute A LA FIN du groupe existant 
    * SI ET SEULEMENT S'IL n'existe aucun element de ce groupe etant egal a 
    * l'element donne. Autrement, l'element donne n'est pas ajoute.
    * 
    * Si le groupe element.getId() n'existe pas dans cette listeGroupes, un nouveau
    * groupe est ajoute (A LA FIN de cette listeGroupes) et l'element donne devient 
    * le premier element du nouveau groupe element.getId().
    * 
    * @param element l'element a ajouter a cette listeGroupes.
    * @return true si l'element donne est ajoute, false sinon.
    * @throws NullPointerException si l'element donne est null.
    */
   public boolean ajouter(T element);
   
   /**
    * Supprime de cette listeGroupes le groupe dont l'id est l'idGroupe donne. 
    * Si le groupe ayant l'idGroupe donne n'existe pas dans cette listeGroupes, 
    * celle-ci demeure inchangee.
    * 
    * @param idGroupe le numero d'identification du groupe a supprimer.
    * @return le nombre d'elements dans le groupe supprime ou 0 si la suppression
    *         n'a pas lieu.
    */
   public int supprimerGroupe(int idGroupe);
   
   /**
    * Supprime l'element donne de son groupe d'appartenance.
    * 
    * Si apres suppression de l'element le groupe est vide, il est supprime
    * de cette listeGroupes.
    * 
    * Si le groupe element.getId() n'existe pas dans cette listeGroupes ou 
    * si l'element donne n'est pas dans le groupe element.getId(), cette
    * listeGroupes demeure inchangee.
    * 
    * @param element l'element a supprimer de cette listeGroupes.
    * @return true si l'element est supprime, false sinon.
    * @throws NullPointerException si element est null.
    */
   public boolean supprimerElement(T element);
   
   /**
    * Permet d'obtenir le numero d'identification (l'id) du groupe ayant la 
    * postion donnee dans cette listeGroupes, si cette position est valide. 
    * Une position valide doit etre comprise entre 0 et nbrGroupes() - 1 
    * inclusivement.
    * 
    * @param position la position, dans cette listeGroupes, du groupe dont on 
    *                 veut obtenir l'id.
    * @return l'id du groupe ayant la position donnee.
    * @throws NoSuchElementException si la position donnee n'est pas une position
    *         valide dans cette listeGroupes.
    */
   public int obtenirIdGroupe(int position);
   
   /**
    * Permet d'obtenir l'element qui se trouve a la position donnee, dans le 
    * groupe dont l'id est l'idGroupe donne, si la position donnee est valide.
    * Une position valide doit etre comprise entre 0 et taille(idGroupe) - 1
    * inclusivement.
    * 
    * @param idGroupe l'id du groupe dans lequel rechercher l'element a retourner.
    * @param position la postition de l'element a retourner, dans le groupe dont
    *                  l'id est l'idGroupe donne.
    * @return l'element a la position donnee, dans le groupe dont l'id est 
    *         l'idGroupe donne.
    * @throws NoSuchElementException si le groupe ayant l'idGroupe donne n'existe 
    *         pas dans cette listeGroupes ou si la position donnee n'est pas un 
    *         indice valide dans le groupe dont l'id est l'idGroupe donne.
    */
   public T obtenirElement(int idGroupe, int position);
   
   /**
    * Permet d'obtenir le nombre d'elements presents dans cette listeGroupes.
    * 
    * @return le nombre d'elements presents dans cette listeGroupes.
    */
   public int taille ();
   
   /**
    * Permet d'obtenir le nombre d'elements presents dans le groupe ayant 
    * comme numero d'identification l'idGroupe donne.
    * 
    * @param idGroupe le numero d'identification du groupe dont on veut 
    *                 le nombre d'elements.
    * @return le nombre d'elements dans le groupe ayant l'idGroupe donne ou 
    *         -1 si l'idGroupe donne ne correspond a aucun groupe dans cette
    *         listeGroupes.
    */
   public int taille (int idGroupe);
   
   /**
    * Permet d'obtenir le nombre de groupes dans cette listeGroupes.
    * 
    * @return le nombre de groupes dans cette listeGroupes.
    */
   public int nbrGroupes ();
   
   /**
    * Teste si le groupe ayant l'idGroupe donne existe dans cette listeGroupes.
    * 
    * @param idGroupe le groupe dont on teste l'existence.
    * @return true si le groupe ayant l'idGroupe donne existe dans cette 
    *         listeGroupes, false sinon.
    */
   public boolean groupeExiste(int idGroupe);
   
   /**
    * Teste si l'element donne existe dans cette listeGroupes (s'il existe dans  
    * le groupe element.getId() de cette listeGroupes). Un element e1 existe dans 
    * un groupe si ce groupe contient un element e2 tel que e1.equals(e2) 
    * retourne true.
    * 
    * @param element l'element dont on teste l'existence.
    * @return true si element existe dans cette listeGroupes, false sinon.
    * @throws NullPointerException si element est null.
    */
   public boolean elementExiste (T element);
   
   /**
    * Remplace l'element1 de cette listeGroupes par l'element2 donne.
    * 
    * - Si element1 n'existe pas dans le groupe dont l'id est element1.getId(),
    *   le remplacement n'a pas lieu.
    * 
    * - Si l'id de element1 est different de l'id de l'element2, le remplacement
    *   n'a pas lieu.
    * 
    * - Si le remplacement avait pour effet de causer un doublon dans le groupe, 
    *   le remplacement ne n'a pas lieu.
    * 
    * @param element1 l'element a remplacer par element2.
    * @param element2 l'element qui remplace l'element1.
    * @return true si le remplacement a eu lieu, false sinon.
    * @throws NullPointerException si element1 ou element2 est null.
    */
   public boolean remplacer (T element1, T element2);

}
