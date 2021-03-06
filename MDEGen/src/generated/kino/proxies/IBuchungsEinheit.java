/**--- Generated at Sat Mar 06 13:28:16 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import idManagement.Identifiable;
import db.executer.PersistenceException;
import generated.kino.BuchungsEinheit;
public interface IBuchungsEinheit extends Identifiable{
   public BuchungsEinheit getTheObject();
   public Integer getId();
   public Integer getReihenNr() ;
   public void setReihenNr(Integer newReihenNr) throws PersistenceException;
   public Integer getSitzNr() ;
   public void setSitzNr(Integer newSitzNr) throws PersistenceException;
}
