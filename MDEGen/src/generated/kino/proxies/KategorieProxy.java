/**--- Generated at Fri Mar 05 15:18:57 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import db.executer.PersistenceException;
import generated.kino.Kategorie;
public abstract class KategorieProxy implements IKategorie{
   public abstract Kategorie getTheObject();
   public boolean equals(Object o) {
      if(!(o instanceof IKategorie)) return false;
      return ((IKategorie)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
}