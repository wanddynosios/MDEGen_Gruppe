/**--- Generated at Fri Mar 05 18:16:22 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import db.executer.PersistenceException;
import java.util.Optional;
import db.executer.*;
import generated.kino.Reihe;
import java.sql.ResultSet;
import generated.kino.Sitz;
import java.util.List;
import exceptions.ConstraintViolation;
import generated.kino.Kategorie;
import generated.kino.relationControl.reiheKategorieSupervisor;
public class ReiheProxy implements IReihe{
   private Integer id;
   private Optional<Reihe> theObject;
   private DBDMLExecuter dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
   public ReiheProxy(Integer id){
      this.id = id;
      this.theObject = Optional.empty();
   }
   public ReiheProxy(Reihe theObject) throws PersistenceException{
      this(theObject.getId());
      this.theObject = Optional.of(theObject);
   }
   public boolean isObjectPresent() {
      return this.theObject.isPresent();
   }
   public Reihe getTheObject()
   {
      try{if(!this.isObjectPresent()) this.theObject = Optional.of(this.load());}catch(PersistenceException pe){assert false : "Fatal Error Occured when loading an existing object from DB: " + "Reihe";}
      return this.theObject.get();
   }
   public Integer getId(){
      return this.id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof IReihe)) return false;
      return ((IReihe)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   private Reihe load() throws PersistenceException {
      ResultSet rs = null;
      try {
         rs = DBExecuterFactory.getConfiguredFactory().getDBDMLExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("Reihe", this.id);
         Kategorie kategorie = reiheKategorieSupervisor.getInstance().getKategorie(this).getTheObject();
         Integer reihenNummer = rs.getInt("reihenNummer");
         return Reihe.createAlreadyPersistent(this, kategorie, reihenNummer);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
   public List<Sitz> getSitze() throws PersistenceException{
      return this.getTheObject().getSitze();
   }
   public void addToSitze(Sitz arg) throws ConstraintViolation, PersistenceException{
      this.getTheObject().addToSitze(arg);
   }
   public boolean removeFromSitze(Sitz arg) throws ConstraintViolation, PersistenceException{
      return this.getTheObject().removeFromSitze(arg);
   }
   public Kategorie getKategorie() throws PersistenceException{
      return this.getTheObject().getKategorie();
   }
   public void setKategorie(Kategorie newKategorie)throws PersistenceException{
      this.getTheObject().setKategorie(newKategorie);
   }
   public Integer getReihenNummer() {
      return this.getTheObject().getReihenNummer();
   }
   public void setReihenNummer(Integer newReihenNummer) throws PersistenceException{
      this.getTheObject().setReihenNummer(newReihenNummer);
   }
}
