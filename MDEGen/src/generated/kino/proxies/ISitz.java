/**--- Generated at Sat Mar 06 15:22:40 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import idManagement.Identifiable;
import db.executer.PersistenceException;
import generated.kino.Sitz;
import generated.kino.Reihe;
public interface ISitz extends Identifiable{
   public Sitz getTheObject();
   public Integer getId();
   public Integer getSitzNummer() ;
   public void setSitzNummer(Integer newSitzNummer) throws PersistenceException;
   public Reihe getReihe() throws PersistenceException;
}
