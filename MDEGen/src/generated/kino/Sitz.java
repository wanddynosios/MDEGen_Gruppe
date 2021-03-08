/**--- Generated at Mon Mar 08 12:14:37 CET 2021 
 * --- Change only in Editable Sections!  
 * --- Do not touch section numbering!   
 */
package generated.kino;
//10 ===== GENERATED:      Import Section =========
import java.sql.SQLException;
import db.connection.NoConnectionException;
import db.connection.TypeKeyManager;
import db.executer.PersistenceExecuterFactory;
import generated.kino.proxies.SitzProxy;
import observation.Observable;
import generated.kino.proxies.ISitz;
import db.executer.PersistenceException;
import generated.kino.relationControl.*;
import exceptions.ConstraintViolation;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class Sitz extends Observable implements java.io.Serializable, ISitz
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   private Integer sitzNummer;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private Sitz(Integer id, Integer sitzNummer, Reihe reihe, boolean objectOnly)
   throws PersistenceException{
      super();
      this.setId(id);
      this.sitzNummer = sitzNummer;
      if(objectOnly) return;
      try{reihenSitzeSupervisor.getInstance().add(reihe,this);}catch(ConstraintViolation cv){}// Ok, because consistency is guaranteed with this statement
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static Sitz createAlreadyPersistent(SitzProxy proxy, Integer sitzNummer, Reihe reihe)throws PersistenceException{
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new Sitz(proxy.getId(), sitzNummer, reihe, true);
   }
   public static Sitz createFresh(Integer sitzNummer, Reihe reihe)throws PersistenceException{
      db.executer.DBDMLExecuter dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("Sitz", "id, typeKey, sitzNummer", 
         id.toString() + ", " + TypeKeyManager.getTheInstance().getTypeKey("Kino", "Sitz").toString() + ", " + sitzNummer.toString());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      Sitz me = new Sitz(id, sitzNummer, reihe, false);
      Kino.getInstance().addSitzProxy(new SitzProxy(me));
      return me;
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public Sitz getTheObject(){
      return this;
   }
   public Integer getId(){
      return this.id;
   }
   protected void setId(Integer id){
      this.id = id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof ISitz)) return false;
      return ((ISitz)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   public Integer getSitzNummer() {
      return this.sitzNummer;
   }
   public void setSitzNummer(Integer newSitzNummer) throws PersistenceException{
      this.sitzNummer = newSitzNummer;
      try{PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter().update("Sitz", "sitzNummer", newSitzNummer.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public Reihe getReihe() throws PersistenceException{
      return reihenSitzeSupervisor.getInstance().getReihe(this).getTheObject();
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
