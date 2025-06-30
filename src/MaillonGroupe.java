/**
 * Classe qui modelise un maillon pour la construction d'une liste de maillons 
 * chaines comportant des elements de type T,dont la classe implemente l'interface 
 * IGroupe. 
 *
 * @author Melanie Lord
 * @version E25
 * @param <T> le type de l'information stockee dans ce maillon. T doit implementer
 *            l'interface IGroupe.
 */
public class MaillonGroupe<T extends IGroupe> {

   private T info;                   //l'information dans ce maillon (l'element)
   private MaillonGroupe<T> suivant; //le maillon suivant 

   /**
    * Cree un nouveau maillon n'ayant pas de maillon suivant.
    * @param info l'information qui sera stockee dans le maillon.
    */
   public MaillonGroupe(T info) {
      this(info, null);
   }

   /**
    * Cree un nouveau maillon ayant un maillon suivant.
    * @param info l'information qui sera stockee dans le maillon.
    * @param suivant le maillon qui sera le suivant du maillon cree.
    */
   public MaillonGroupe(T info, MaillonGroupe<T> suivant) {
      this.info = info;
      this.suivant = suivant;
   }

   /**
    * Permet d'obtenir l'info (l'element) de ce maillon.
    * @return l'info de ce maillon.
    */
   public T getInfo() {
      return info;
   }

   /**
    * Permet d'obtenir le maillon suivant de ce maillon.
    * @return le maillon suivant de ce suivant ou null si aucun suivant.
    */
   public MaillonGroupe<T> getSuivant() {
      return suivant;
   }

   /**
    * Permet de modifier l'info de ce maillon par l'info donnee en parametre.
    * @param info la nouvelle info pour ce maillon.
    */
   public void setInfo(T info) {
      this.info = info;
   }

   /**
    * Permet de modifier le suivant de ce maillon par le maillon donne en 
    * parametre.
    * @param suivant le nouveau maillon suivant de ce maillon.
    */
   public void setSuivant(MaillonGroupe<T> suivant) {
      this.suivant = suivant;
   }

}
