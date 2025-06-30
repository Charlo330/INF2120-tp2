
/**
 * Cette classe modelise un abonne a une revue. Elle implement l'interface 
 * IGroupe dont les methodes getId() et setId() permettent de consulter et 
 * modifier l'id de la revue a laquelle cet abonneRevue est abonne.
 * 
 * @author melanie lord
 * @version E25
 */
public class AbonneRevue implements IGroupe {
   
   //numero d'identification de la revue a laquelle cet abonneRevue est abonne
   private int idRevue;
   
   //le nom de cet abonneRevue
   private String nomAbonne;
   
   /**
    * Constructeur qui initialise l'id de la revue a laquelle cet abonneRevue est
    * abonne, ainsi que le nom de cet abonne avec les valeurs passees en 
    * parametres.
    * 
    * @param idRevue l'id de la revue a laquelle cet abonneRevue est abonne.
    * @param nomAbonne le nom de cet abonneRevue.
    * @throws NullPointerException si cet abonne est null.
    */
   public AbonneRevue (int idRevue, String nomAbonne) {
      if (nomAbonne == null) {
         throw new NullPointerException();
      }
      this.idRevue = idRevue;
      this.nomAbonne = nomAbonne;
   }
   
   //----------------------------------------
   // Implementation de l'interface IGroupe
   //----------------------------------------
   
   /**
    * Permet de consulter le numero d'identification de la revue a laquelle
    * cet abonneRevue est abonne.
    * 
    * @return le numero de la revue a laquelle cet abonneRevue est abonne.
    */
   @Override
   public int getId() {
      return idRevue;
   }
   
   /**
    * Permet de modifier le numero d'identification de la revue a laquelle 
    * cet abonneRevue est abonne par celui passe en parametre.
    * 
    * @param idRevue le nouveau numero d'identification de la revue a laquelle
    *                cet abonneRevue est abonne.
    */
   @Override
   public void setId(int idRevue) {
      this.idRevue = idRevue;
   }
   
   //-----------------------------
   // Getters et setters
   //-----------------------------
   
   /**
    * Permet de consulter le nom de cet abonneRevue.
    * 
    * @return le nom de cet abonneRevue.
    */
   public String getNomAbonne() {
      return nomAbonne;
   }
   
   /**
    * Permet de modifier le nom de cet abonne par celui passe en parametre.
    * 
    * @param nomAbonne le nouveau nom de cet abonneRevue.
    * @throws NullPointerException si nomAbonne est null.
    */
   public void setNomAbonne(String nomAbonne) {
      if (nomAbonne == null) {
         throw new NullPointerException();
      }
      this.nomAbonne = nomAbonne;
   }
   
   /**
    * Permet de tester l'egalite entre deux abonnes.
    * Deux abonnes sont consideres egaux s'ils ont le meme nom d'abonne.
    * 
    * @param autreAbonneRevue l'autre abonneRevue a comparer avec cet abonneRevue.
    * @return true si les deux abonnes sont egaux, false sinon.
    */
   @Override
   public boolean equals (Object autreAbonneRevue) {
      return autreAbonneRevue != null 
               && this.getClass().equals(autreAbonneRevue.getClass())  
               && this.nomAbonne.equalsIgnoreCase(((AbonneRevue)autreAbonneRevue)
                       .nomAbonne);
   }
   
   /**
    * Retourne une representation sous forme de chaine de caracteres de cet 
    * abonne. Par exemple, si le numero d'identification de la revue a laquelle
    * est abonne cet abonneRevue est 3, et que le nom de cet abonne est "Kim", 
    * cette methode retournera la chaine "(3, Kim)".
    * 
    * @return une representation sous forme de chaine de caracteres de cet 
    *         abonneRevue.
    */
   @Override
   public String toString() {
      return "(" + idRevue + ", " + nomAbonne + ")";
   }
   
}
