/**
 * Classe qui modelise un maillon permettant de creer une liste de listes 
 * chainees. Chaque maillon possede un suivant (pour le chainage) et possede une
 * info qui pointe vers le debut (MaillonGroupe) d'une sous-liste chainee.
 *
 * @author Melanie Lord
 * @version E25
 * @param <T> le type de l'information stockee dans les maillons de la sous-liste
 *            chainee attache a ce maillon (et dont le debut est l'attribut info).
 */
public class MaillonListe <T extends IGroupe> {

   //l'information dans ce maillon = une autre liste chainee (sous-liste)
   private MaillonGroupe<T> info;   
   
   //le maillon suivant
   private MaillonListe<T> suivant; 

   /**
    * Cree un nouveau maillon n'ayant pas de maillon suivant.
    * @param info l'information qui sera stockee dans le maillon.
    */
   public MaillonListe(MaillonGroupe<T>  info) {
      this(info, null);
   }

   /**
    * Cree un nouveau maillon ayant un maillon suivant.
    * @param info le premier maillonGroupe de la sous-liste de maillons
    *             chainee attachee a ce maillonListe.
    * @param suivant le maillon qui sera le suivant du maillon cree.
    */
   public MaillonListe(MaillonGroupe<T>  info, MaillonListe<T> suivant) {
      this.info = info;
      this.suivant = suivant;
   }

   /**
    * Permet d'obtenir l'info de ce maillon c.-a-d. le premier maillonGroupe
    * de la sous-liste chainee attachee a ce maillon.
    * @return l'info de ce maillon.
    */
   public MaillonGroupe<T>  getInfo() {
      return info;
   }

   /**
    * Permet d'obtenir le maillon suivant de ce maillon.
    * @return le maillon suivant de ce suivant ou null si aucun suivant.
    */
   public MaillonListe<T> getSuivant() {
      return suivant;
   }

   /**
    * Permet de modifier l'info (le premier maillon de la sous-liste attachee
    * a ce maillon) par l'info donnee en parametre.
    * @param info la nouvelle info pour ce maillon.
    */
   public void setInfo(MaillonGroupe<T>  info) {
      this.info = info;
   }

   /**
    * Permet de modifier le suivant de ce maillon par le maillon donne en 
    * parametre.
    * @param suivant le nouveau suivant de ce maillon.
    */
   public void setSuivant(MaillonListe<T> suivant) {
      this.suivant = suivant;
   }
   
}
