/**--- Generated at Fri Mar 05 15:18:57 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import db.executer.PersistenceException;
import java.util.Optional;
import db.executer.*;
import generated.kino.BuchungsEinheit;
import java.sql.ResultSet;
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
         Integer reihenNr = rs.getInt("reihenNr");
         Integer sitzNr = rs.getInt("sitzNr");
         return BuchungsEinheit.createAlreadyPersistent(this, reihenNr, sitzNr);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
   public Integer getReihenNr() {
      return this.getTheObject().getReihenNr();
   }
   public void setReihenNr(Integer newReihenNr) throws PersistenceException{
      this.getTheObject().setReihenNr(newReihenNr);
   }
   public Integer getSitzNr() {
      return this.getTheObject().getSitzNr();
   }
   public void setSitzNr(Integer newSitzNr) throws PersistenceException{
      this.getTheObject().setSitzNr(newSitzNr);
   }
}
