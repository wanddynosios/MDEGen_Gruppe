/**--- Generated at Mon Mar 08 14:44:48 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import idManagement.Identifiable;
import db.executer.PersistenceException;
import generated.kino.Vorfuehrung;
import generated.kino.Resevierung;
import java.util.Set;
import exceptions.ConstraintViolation;
import generated.kino.Film;
import generated.kino.Saal;
import generated.kino.Buchung;
public interface IVorfuehrung extends Identifiable{
   public Vorfuehrung getTheObject();
   public Integer getId();
   public Set<Resevierung> getReservierungen() throws PersistenceException;
   public void addToReservierungen(Resevierung arg) throws ConstraintViolation, PersistenceException;
   public boolean removeFromReservierungen(Resevierung arg) throws ConstraintViolation, PersistenceException;
   public Film getFilm() throws PersistenceException;
   public void setFilm(Film newFilm)throws PersistenceException;
   public Saal getSaal() throws PersistenceException;
   public void setSaal(Saal newSaal)throws PersistenceException;
   public Set<Buchung> getBuchungen() throws PersistenceException;
   public void addToBuchungen(Buchung arg) throws ConstraintViolation, PersistenceException;
   public boolean removeFromBuchungen(Buchung arg) throws ConstraintViolation, PersistenceException;
   public Integer getVorfuehrungsNummer() ;
   public void setVorfuehrungsNummer(Integer newVorfuehrungsNummer) throws PersistenceException;
   public Integer getPreisParkett() ;
   public void setPreisParkett(Integer newPreisParkett) throws PersistenceException;
   public Integer getPreisMitte() ;
   public void setPreisMitte(Integer newPreisMitte) throws PersistenceException;
   public Integer getPreisLoge() ;
   public void setPreisLoge(Integer newPreisLoge) throws PersistenceException;
   public Integer getFreiePlaetzeParkett() ;
   public void setFreiePlaetzeParkett(Integer newFreiePlaetzeParkett) throws PersistenceException;
   public Integer getFreiePlaetzeMitte() ;
   public void setFreiePlaetzeMitte(Integer newFreiePlaetzeMitte) throws PersistenceException;
   public Integer getFreiePlaetzeLoge() ;
   public void setFreiePlaetzeLoge(Integer newFreiePlaetzeLoge) throws PersistenceException;
   public Boolean getBereitsVorbei() ;
   public void setBereitsVorbei(Boolean newBereitsVorbei) throws PersistenceException;
}
