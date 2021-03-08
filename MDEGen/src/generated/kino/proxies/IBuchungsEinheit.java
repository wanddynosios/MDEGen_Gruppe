/**--- Generated at Mon Mar 08 14:44:48 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import idManagement.Identifiable;
import db.executer.PersistenceException;
import generated.kino.BuchungsEinheit;
import generated.kino.Sitz;
public interface IBuchungsEinheit extends Identifiable{
   public BuchungsEinheit getTheObject();
   public Integer getId();
   public Sitz getSitz() throws PersistenceException;
   public void setSitz(Sitz newSitz)throws PersistenceException;
}
