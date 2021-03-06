/**--- Generated at Sat Mar 06 17:55:52 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import db.executer.PersistenceException;
import java.util.Optional;
import db.executer.*;
import generated.kino.BuchungsEinheit;
import java.sql.ResultSet;
import generated.kino.Sitz;
import java.util.Set;
public class BuchungsEinheitProxy implements IBuchungsEinheit{
   private Integer id;
   private Optional<BuchungsEinheit> theObject;
   private DBDMLExecuter dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
   public BuchungsEinheitProxy(Integer id){
      this.id = id;
      this.theObject = Optional.empty();
   }
   public BuchungsEinheitProxy(BuchungsEinheit theObject) throws PersistenceException{
      this(theObject.getId());
      this.theObject = Optional.of(theObject);
   }
   public boolean isObjectPresent() {
      return this.theObject.isPresent();
   }
   public BuchungsEinheit getTheObject()
   {
      try{if(!this.isObjectPresent()) this.theObject = Optional.of(this.load());}catch(PersistenceException pe){assert false : "Fatal Error Occured when loading an existing object from DB: " + "BuchungsEinheit";}
      return this.theObject.get();
   }
   public Integer getId(){
      return this.id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof IBuchungsEinheit)) return false;
      return ((IBuchungsEinheit)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   private BuchungsEinheit load() throws PersistenceException {
      ResultSet rs = null;
      try {
         rs = DBExecuterFactory.getConfiguredFactory().getDBDMLExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("BuchungsEinheit", this.id);
         return BuchungsEinheit.createAlreadyPersistent(this);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
   public Set<Sitz> getSitz() throws PersistenceException{
      return this.getTheObject().getSitz();
   }
   public void addToSitz(Sitz arg) throws PersistenceException{
      this.getTheObject().addToSitz(arg);
   }
   public boolean removeFromSitz(Sitz arg) throws PersistenceException{
      return this.getTheObject().removeFromSitz(arg);
   }
}
