/**--- Generated at Sat Mar 06 13:28:16 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import db.executer.PersistenceException;
import java.util.Optional;
import db.executer.*;
import generated.kino.Sitz;
import java.sql.ResultSet;
import generated.kino.Reihe;
import generated.kino.relationControl.reihenSitzeSupervisor;
public class SitzProxy implements ISitz{
   private Integer id;
   private Optional<Sitz> theObject;
   private DBDMLExecuter dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
   public SitzProxy(Integer id){
      this.id = id;
      this.theObject = Optional.empty();
   }
   public SitzProxy(Sitz theObject) throws PersistenceException{
      this(theObject.getId());
      this.theObject = Optional.of(theObject);
   }
   public boolean isObjectPresent() {
      return this.theObject.isPresent();
   }
   public Sitz getTheObject()
   {
      try{if(!this.isObjectPresent()) this.theObject = Optional.of(this.load());}catch(PersistenceException pe){assert false : "Fatal Error Occured when loading an existing object from DB: " + "Sitz";}
      return this.theObject.get();
   }
   public Integer getId(){
      return this.id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof ISitz)) return false;
      return ((ISitz)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   private Sitz load() throws PersistenceException {
      ResultSet rs = null;
      try {
         rs = DBExecuterFactory.getConfiguredFactory().getDBDMLExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("Sitz", this.id);
         Integer sitzNummer = rs.getInt("sitzNummer");
         Reihe reihe = reihenSitzeSupervisor.getInstance().getReihe(this).getTheObject();
         return Sitz.createAlreadyPersistent(this, sitzNummer, reihe);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
   public Integer getSitzNummer() {
      return this.getTheObject().getSitzNummer();
   }
   public void setSitzNummer(Integer newSitzNummer) throws PersistenceException{
      this.getTheObject().setSitzNummer(newSitzNummer);
   }
   public Reihe getReihe() throws PersistenceException{
      return this.getTheObject().getReihe();
   }
}
