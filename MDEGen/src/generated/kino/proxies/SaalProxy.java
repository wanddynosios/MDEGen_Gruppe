/**--- Generated at Mon Mar 08 14:44:48 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import db.executer.PersistenceException;
import java.util.Optional;
import db.executer.*;
import generated.kino.Saal;
import java.sql.ResultSet;
import generated.kino.Reihe;
import java.util.List;
public class SaalProxy implements ISaal{
   private Integer id;
   private Optional<Saal> theObject;
   private DBDMLExecuter dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
   public SaalProxy(Integer id){
      this.id = id;
      this.theObject = Optional.empty();
   }
   public SaalProxy(Saal theObject) throws PersistenceException{
      this(theObject.getId());
      this.theObject = Optional.of(theObject);
   }
   public boolean isObjectPresent() {
      return this.theObject.isPresent();
   }
   public Saal getTheObject()
   {
      try{if(!this.isObjectPresent()) this.theObject = Optional.of(this.load());}catch(PersistenceException pe){assert false : "Fatal Error Occured when loading an existing object from DB: " + "Saal";}
      return this.theObject.get();
   }
   public Integer getId(){
      return this.id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof ISaal)) return false;
      return ((ISaal)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   private Saal load() throws PersistenceException {
      ResultSet rs = null;
      try {
         rs = DBExecuterFactory.getConfiguredFactory().getDBDMLExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("Saal", this.id);
         Integer SaalNummer = rs.getInt("SaalNummer");
         return Saal.createAlreadyPersistent(this, SaalNummer);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
   public List<Reihe> getReihen() throws PersistenceException{
      return this.getTheObject().getReihen();
   }
   public void addToReihen(Reihe arg) throws PersistenceException{
      this.getTheObject().addToReihen(arg);
   }
   public boolean removeFromReihen(Reihe arg) throws PersistenceException{
      return this.getTheObject().removeFromReihen(arg);
   }
   public Integer getSaalNummer() {
      return this.getTheObject().getSaalNummer();
   }
   public void setSaalNummer(Integer newSaalNummer) throws PersistenceException{
      this.getTheObject().setSaalNummer(newSaalNummer);
   }
}
