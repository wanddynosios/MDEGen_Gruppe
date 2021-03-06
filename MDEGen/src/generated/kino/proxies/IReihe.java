/**--- Generated at Sat Mar 06 17:55:51 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import idManagement.Identifiable;
import db.executer.PersistenceException;
import generated.kino.Reihe;
import generated.kino.Sitz;
import java.util.List;
import exceptions.ConstraintViolation;
import generated.kino.Kategorie;
public interface IReihe extends Identifiable{
   public Reihe getTheObject();
   public Integer getId();
   public List<Sitz> getSitze() throws PersistenceException;
   public void addToSitze(Sitz arg) throws ConstraintViolation, PersistenceException;
   public boolean removeFromSitze(Sitz arg) throws ConstraintViolation, PersistenceException;
   public Kategorie getKategorie() throws PersistenceException;
   public void setKategorie(Kategorie newKategorie)throws PersistenceException;
   public Integer getReihenNummer() ;
   public void setReihenNummer(Integer newReihenNummer) throws PersistenceException;
}
