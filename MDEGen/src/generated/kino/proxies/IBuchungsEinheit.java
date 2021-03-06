/**--- Generated at Sat Mar 06 17:55:52 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import idManagement.Identifiable;
import db.executer.PersistenceException;
import generated.kino.BuchungsEinheit;
import generated.kino.Sitz;
import java.util.Set;
public interface IBuchungsEinheit extends Identifiable{
   public BuchungsEinheit getTheObject();
   public Integer getId();
   public Set<Sitz> getSitz() throws PersistenceException;
   public void addToSitz(Sitz arg) throws PersistenceException;
   public boolean removeFromSitz(Sitz arg) throws PersistenceException;
}
