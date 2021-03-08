/**--- Generated at Mon Mar 08 14:44:48 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import idManagement.Identifiable;
import db.executer.PersistenceException;
import generated.kino.Saal;
import generated.kino.Reihe;
import java.util.List;
public interface ISaal extends Identifiable{
   public Saal getTheObject();
   public Integer getId();
   public List<Reihe> getReihen() throws PersistenceException;
   public void addToReihen(Reihe arg) throws PersistenceException;
   public boolean removeFromReihen(Reihe arg) throws PersistenceException;
   public Integer getSaalNummer() ;
   public void setSaalNummer(Integer newSaalNummer) throws PersistenceException;
}
