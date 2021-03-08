/**--- Generated at Mon Mar 08 14:44:48 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import db.executer.PersistenceException;
import java.util.Optional;
import db.executer.*;
import generated.kino.Buchung;
import java.sql.ResultSet;
import generated.kino.BuchungsEinheit;
import java.util.Set;
import generated.kino.Vorfuehrung;
import generated.kino.relationControl.vorfuehrungBuchungenSupervisor;
public class BuchungProxy implements IBuchung{
   private Integer id;
   private Optional<Buchung> theObject;
   private DBDMLExecuter dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
   public BuchungProxy(Integer id){
      this.id = id;
      this.theObject = Optional.empty();
   }
   public BuchungProxy(Buchung theObject) throws PersistenceException{
      this(theObject.getId());
      this.theObject = Optional.of(theObject);
   }
   public boolean isObjectPresent() {
      return this.theObject.isPresent();
   }
   public Buchung getTheObject()
   {
      try{if(!this.isObjectPresent()) this.theObject = Optional.of(this.load());}catch(PersistenceException pe){assert false : "Fatal Error Occured when loading an existing object from DB: " + "Buchung";}
      return this.theObject.get();
   }
   public Integer getId(){
      return this.id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof IBuchung)) return false;
      return ((IBuchung)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   private Buchung load() throws PersistenceException {
      ResultSet rs = null;
      try {
         rs = DBExecuterFactory.getConfiguredFactory().getDBDMLExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("Buchung", this.id);
         Integer buchungsNummer = rs.getInt("buchungsNummer");
         Vorfuehrung vorfuehrung = vorfuehrungBuchungenSupervisor.getInstance().getVorfuehrung(this).getTheObject();
         return Buchung.createAlreadyPersistent(this, buchungsNummer, vorfuehrung);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
   public Set<BuchungsEinheit> getBuchungsEinheiten() throws PersistenceException{
      return this.getTheObject().getBuchungsEinheiten();
   }
   public void addToBuchungsEinheiten(BuchungsEinheit arg) throws PersistenceException{
      this.getTheObject().addToBuchungsEinheiten(arg);
   }
   public boolean removeFromBuchungsEinheiten(BuchungsEinheit arg) throws PersistenceException{
      return this.getTheObject().removeFromBuchungsEinheiten(arg);
   }
   public Integer getBuchungsNummer() {
      return this.getTheObject().getBuchungsNummer();
   }
   public void setBuchungsNummer(Integer newBuchungsNummer) throws PersistenceException{
      this.getTheObject().setBuchungsNummer(newBuchungsNummer);
   }
   public Vorfuehrung getVorfuehrung() throws PersistenceException{
      return this.getTheObject().getVorfuehrung();
   }
}
