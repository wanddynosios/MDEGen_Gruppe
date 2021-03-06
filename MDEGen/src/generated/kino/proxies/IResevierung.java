/**--- Generated at Sat Mar 06 17:55:51 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import idManagement.Identifiable;
import db.executer.PersistenceException;
import generated.kino.Resevierung;
import generated.kino.Kategorie;
import generated.kino.Vorfuehrung;
public interface IResevierung extends Identifiable{
   public Resevierung getTheObject();
   public Integer getId();
   public Kategorie getKategorie() throws PersistenceException;
   public void setKategorie(Kategorie newKategorie)throws PersistenceException;
   public String getName() ;
   public void setName(String newName) throws PersistenceException;
   public Integer getAnzahlPlaetze() ;
   public void setAnzahlPlaetze(Integer newAnzahlPlaetze) throws PersistenceException;
   public Boolean getIstBereitsEingeloest() ;
   public void setIstBereitsEingeloest(Boolean newIstBereitsEingeloest) throws PersistenceException;
   public Vorfuehrung getVorfuehrung() throws PersistenceException;
}
