/**--- Generated at Mon Mar 08 14:44:48 CET 2021 
 * --- Change only in Editable Sections!  
 * --- Do not touch section numbering!   
 */
package generated.kino;
//10 ===== GENERATED:      Import Section =========
import java.sql.SQLException;
import db.connection.NoConnectionException;
import db.connection.TypeKeyManager;
import db.executer.PersistenceExecuterFactory;
import generated.kino.proxies.BuchungProxy;
import observation.Observable;
import generated.kino.proxies.IBuchung;
import generated.kino.relationControl.*;
import generated.kino.proxies.*;
import db.executer.PersistenceException;
import java.util.Set;
import java.util.HashSet;
import exceptions.ConstraintViolation;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class Buchung extends Observable implements java.io.Serializable, IBuchung
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   private Integer buchungsNummer;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private Buchung(Integer id, Integer buchungsNummer, Vorfuehrung vorfuehrung, boolean objectOnly)
   throws PersistenceException{
      super();
      this.setId(id);
      this.buchungsNummer = buchungsNummer;
      if(objectOnly) return;
      try{vorfuehrungBuchungenSupervisor.getInstance().add(vorfuehrung,this);}catch(ConstraintViolation cv){}// Ok, because consistency is guaranteed with this statement
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static Buchung createAlreadyPersistent(BuchungProxy proxy, Integer buchungsNummer, Vorfuehrung vorfuehrung)throws PersistenceException{
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new Buchung(proxy.getId(), buchungsNummer, vorfuehrung, true);
   }
   public static Buchung createFresh(Integer buchungsNummer, Vorfuehrung vorfuehrung)throws PersistenceException{
      db.executer.DBDMLExecuter dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("Buchung", "id, typeKey, buchungsNummer", 
         id.toString() + ", " + TypeKeyManager.getTheInstance().getTypeKey("Kino", "Buchung").toString() + ", " + buchungsNummer.toString());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      Buchung me = new Buchung(id, buchungsNummer, vorfuehrung, false);
      Kino.getInstance().addBuchungProxy(new BuchungProxy(me));
      return me;
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public Buchung getTheObject(){
      return this;
   }
   public Integer getId(){
      return this.id;
   }
   protected void setId(Integer id){
      this.id = id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof IBuchung)) return false;
      return ((IBuchung)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   public Set<BuchungsEinheit> getBuchungsEinheiten() throws PersistenceException{
      Set<BuchungsEinheit> result = new HashSet<>();
      for (IBuchungsEinheit i : buchungsBuchungseinheitenSupervisor.getInstance().getBuchungsEinheiten(this)) result.add(i.getTheObject());
      return result;
   }
   public void addToBuchungsEinheiten(BuchungsEinheit arg) throws PersistenceException{
      buchungsBuchungseinheitenSupervisor.getInstance().add(this, arg);
   }
   public boolean removeFromBuchungsEinheiten(BuchungsEinheit arg) throws PersistenceException{
      return buchungsBuchungseinheitenSupervisor.getInstance().remove(this, arg);
   }
   public Integer getBuchungsNummer() {
      return this.buchungsNummer;
   }
   public void setBuchungsNummer(Integer newBuchungsNummer) throws PersistenceException{
      this.buchungsNummer = newBuchungsNummer;
      try{PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter().update("Buchung", "buchungsNummer", newBuchungsNummer.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public Vorfuehrung getVorfuehrung() throws PersistenceException{
      return vorfuehrungBuchungenSupervisor.getInstance().getVorfuehrung(this).getTheObject();
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
