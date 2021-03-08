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
import generated.kino.proxies.BuchungsEinheitProxy;
import observation.Observable;
import generated.kino.proxies.IBuchungsEinheit;
import db.executer.PersistenceException;
import exceptions.ConstraintViolation;
import generated.kino.relationControl.*;
import generated.kino.proxies.*;
import java.util.Set;
import java.util.HashSet;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class BuchungsEinheit extends Observable implements java.io.Serializable, IBuchungsEinheit
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private BuchungsEinheit(Integer id, Sitz sitz, boolean objectOnly)
   throws PersistenceException{
      super();
      this.setId(id);
      buchungseinheitSitzeSupervisor.getInstance().set(this, sitz);
      if(objectOnly) return;
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static BuchungsEinheit createAlreadyPersistent(BuchungsEinheitProxy proxy, Sitz sitz)throws PersistenceException{
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new BuchungsEinheit(proxy.getId(), sitz, true);
   }
   public static BuchungsEinheit createFresh(Sitz sitz)throws PersistenceException{
      db.executer.DBDMLExecuter dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("BuchungsEinheit", "id, typeKey", 
         id.toString() + ", " + TypeKeyManager.getTheInstance().getTypeKey("Kino", "BuchungsEinheit").toString());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      BuchungsEinheit me = new BuchungsEinheit(id, sitz, false);
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
   public Sitz getSitz() throws PersistenceException{
      return buchungseinheitSitzeSupervisor.getInstance().getSitz(this).getTheObject();
   }
   public void setSitz(Sitz newSitz)throws PersistenceException{
      buchungseinheitSitzeSupervisor.getInstance().change(this, this.getSitz(), newSitz);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
