/**--- Generated at Fri Mar 05 15:18:57 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import idManagement.Identifiable;
import db.executer.PersistenceException;
import generated.kino.Resevierung;
import generated.kino.Sitz;
import java.util.List;
import generated.kino.Kategorie;
import generated.kino.Vorfuehrung;
public interface IResevierung extends Identifiable{
   public Resevierung getTheObject();
   public Integer getId();
   public List<Sitz> getSitze() throws PersistenceException;
   public void addToSitze(Sitz arg) throws PersistenceException;
   public boolean removeFromSitze(Sitz arg) throws PersistenceException;
   public Kategorie getKategorie() throws PersistenceException;
   public void setKategorie(Kategorie newKategorie)throws PersistenceException;
   public String getName() ;
   public void setName(String newName) throws PersistenceException;
   public Integer getAnzahlPlaetze() ;
   public void setAnzahlPlaetze(Integer newAnzahlPlaetze) throws PersistenceException;
   public Vorfuehrung getVorfuehrung() throws PersistenceException;
}
