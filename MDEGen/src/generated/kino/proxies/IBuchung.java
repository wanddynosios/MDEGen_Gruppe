/**--- Generated at Sat Mar 06 17:55:52 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import idManagement.Identifiable;
import db.executer.PersistenceException;
import generated.kino.Buchung;
import generated.kino.BuchungsEinheit;
import java.util.Set;
import generated.kino.Vorfuehrung;
public interface IBuchung extends Identifiable{
   public Buchung getTheObject();
   public Integer getId();
   public Set<BuchungsEinheit> getBuchungsEinheiten() throws PersistenceException;
   public void addToBuchungsEinheiten(BuchungsEinheit arg) throws PersistenceException;
   public boolean removeFromBuchungsEinheiten(BuchungsEinheit arg) throws PersistenceException;
   public Integer getBuchungsNummer() ;
   public void setBuchungsNummer(Integer newBuchungsNummer) throws PersistenceException;
   public Vorfuehrung getVorfuehrung() throws PersistenceException;
}
