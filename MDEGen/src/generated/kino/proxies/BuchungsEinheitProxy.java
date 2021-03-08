/**--- Generated at Mon Mar 08 14:44:48 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import db.executer.PersistenceException;
import java.util.Optional;
import db.executer.*;
import generated.kino.BuchungsEinheit;
import java.sql.ResultSet;
import generated.kino.Sitz;
import generated.kino.relationControl.buchungseinheitSitzeSupervisor;
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
         Sitz sitz = buchungseinheitSitzeSupervisor.getInstance().getSitz(this).getTheObject();
         return BuchungsEinheit.createAlreadyPersistent(this, sitz);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
   public Sitz getSitz() throws PersistenceException{
      return this.getTheObject().getSitz();
   }
   public void setSitz(Sitz newSitz)throws PersistenceException{
      this.getTheObject().setSitz(newSitz);
   }
}
