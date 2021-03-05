/**--- Generated at Fri Mar 05 18:16:22 CET 2021 
 * --- Change only in Editable Sections!  
 * --- Do not touch section numbering!   
 */
package generated.kino;
//10 ===== GENERATED:      Import Section =========
import java.sql.SQLException;
import db.connection.NoConnectionException;
import db.connection.TypeKeyManager;
import db.executer.PersistenceExecuterFactory;
import generated.kino.proxies.BuchungsEinheitProxy;
import observation.Observable;
import generated.kino.proxies.IBuchungsEinheit;
import db.executer.PersistenceException;
import exceptions.ConstraintViolation;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class BuchungsEinheit extends Observable implements java.io.Serializable, IBuchungsEinheit
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   private Integer reihenNr;
   private Integer sitzNr;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private BuchungsEinheit(Integer id, Integer reihenNr, Integer sitzNr, boolean objectOnly)
   {
      super();
      this.setId(id);
      this.reihenNr = reihenNr;
      this.sitzNr = sitzNr;
      if(objectOnly) return;
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static BuchungsEinheit createAlreadyPersistent(BuchungsEinheitProxy proxy, Integer reihenNr, Integer sitzNr){
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new BuchungsEinheit(proxy.getId(), reihenNr, sitzNr, true);
   }
   public static BuchungsEinheit createFresh(Integer reihenNr, Integer sitzNr)throws PersistenceException{
      db.executer.DBDMLExecuter dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("BuchungsEinheit", "id, typeKey, reihenNr, sitzNr", 
         id.toString() + ", " + TypeKeyManager.getTheInstance().getTypeKey("Kino", "BuchungsEinheit").toString() + ", " + reihenNr.toString() + ", " + sitzNr.toString());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      BuchungsEinheit me = new BuchungsEinheit(id, reihenNr, sitzNr, false);
      Kino.getInstance().addBuchungsEinheitProxy(new BuchungsEinheitProxy(me));
      return me;
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public BuchungsEinheit getTheObject(){
      return this;
   }
   public Integer getId(){
      return this.id;
   }
   protected void setId(Integer id){
      this.id = id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof IBuchungsEinheit)) return false;
      return ((IBuchungsEinheit)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   public Integer getReihenNr() {
      return this.reihenNr;
   }
   public void setReihenNr(Integer newReihenNr) throws PersistenceException{
      this.reihenNr = newReihenNr;
      try{PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter().update("BuchungsEinheit", "reihenNr", newReihenNr.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public Integer getSitzNr() {
      return this.sitzNr;
   }
   public void setSitzNr(Integer newSitzNr) throws PersistenceException{
      this.sitzNr = newSitzNr;
      try{PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter().update("BuchungsEinheit", "sitzNr", newSitzNr.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
