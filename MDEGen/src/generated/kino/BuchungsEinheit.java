/**--- Generated at Sat Mar 06 17:55:52 CET 2021 
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
   private BuchungsEinheit(Integer id, boolean objectOnly)
   {
      super();
      this.setId(id);
      if(objectOnly) return;
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static BuchungsEinheit createAlreadyPersistent(BuchungsEinheitProxy proxy){
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new BuchungsEinheit(proxy.getId(), true);
   }
   public static BuchungsEinheit createFresh()throws PersistenceException{
      db.executer.DBDMLExecuter dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("BuchungsEinheit", "id, typeKey", 
         id.toString() + ", " + TypeKeyManager.getTheInstance().getTypeKey("Kino", "BuchungsEinheit").toString());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      BuchungsEinheit me = new BuchungsEinheit(id, false);
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
   public Set<Sitz> getSitz() throws PersistenceException{
      Set<Sitz> result = new HashSet<>();
      for (ISitz i : buchungseinheitSitzeSupervisor.getInstance().getSitz(this)) result.add(i.getTheObject());
      return result;
   }
   public void addToSitz(Sitz arg) throws PersistenceException{
      buchungseinheitSitzeSupervisor.getInstance().add(this, arg);
   }
   public boolean removeFromSitz(Sitz arg) throws PersistenceException{
      return buchungseinheitSitzeSupervisor.getInstance().remove(this, arg);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
