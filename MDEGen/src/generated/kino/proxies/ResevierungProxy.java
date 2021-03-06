/**--- Generated at Sat Mar 06 17:55:51 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import db.executer.PersistenceException;
import java.util.Optional;
import db.executer.*;
import generated.kino.Resevierung;
import java.sql.ResultSet;
import generated.kino.Kategorie;
import generated.kino.relationControl.reservierungKategorieSupervisor;
import generated.kino.Vorfuehrung;
import generated.kino.relationControl.vorfuehrungReservierungenSupervisor;
public class ResevierungProxy implements IResevierung{
   private Integer id;
   private Optional<Resevierung> theObject;
   private DBDMLExecuter dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
   public ResevierungProxy(Integer id){
      this.id = id;
      this.theObject = Optional.empty();
   }
   public ResevierungProxy(Resevierung theObject) throws PersistenceException{
      this(theObject.getId());
      this.theObject = Optional.of(theObject);
   }
   public boolean isObjectPresent() {
      return this.theObject.isPresent();
   }
   public Resevierung getTheObject()
   {
      try{if(!this.isObjectPresent()) this.theObject = Optional.of(this.load());}catch(PersistenceException pe){assert false : "Fatal Error Occured when loading an existing object from DB: " + "Resevierung";}
      return this.theObject.get();
   }
   public Integer getId(){
      return this.id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof IResevierung)) return false;
      return ((IResevierung)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   private Resevierung load() throws PersistenceException {
      ResultSet rs = null;
      try {
         rs = DBExecuterFactory.getConfiguredFactory().getDBDMLExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("Resevierung", this.id);
         Kategorie kategorie = reservierungKategorieSupervisor.getInstance().getKategorie(this).getTheObject();
         String name = rs.getString("name");
         Integer anzahlPlaetze = rs.getInt("anzahlPlaetze");
         Boolean istBereitsEingeloest = rs.getBoolean("istBereitsEingeloest");
         Vorfuehrung vorfuehrung = vorfuehrungReservierungenSupervisor.getInstance().getVorfuehrung(this).getTheObject();
         return Resevierung.createAlreadyPersistent(this, kategorie, name, anzahlPlaetze, istBereitsEingeloest, vorfuehrung);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
   public Kategorie getKategorie() throws PersistenceException{
      return this.getTheObject().getKategorie();
   }
   public void setKategorie(Kategorie newKategorie)throws PersistenceException{
      this.getTheObject().setKategorie(newKategorie);
   }
   public String getName() {
      return this.getTheObject().getName();
   }
   public void setName(String newName) throws PersistenceException{
      this.getTheObject().setName(newName);
   }
   public Integer getAnzahlPlaetze() {
      return this.getTheObject().getAnzahlPlaetze();
   }
   public void setAnzahlPlaetze(Integer newAnzahlPlaetze) throws PersistenceException{
      this.getTheObject().setAnzahlPlaetze(newAnzahlPlaetze);
   }
   public Boolean getIstBereitsEingeloest() {
      return this.getTheObject().getIstBereitsEingeloest();
   }
   public void setIstBereitsEingeloest(Boolean newIstBereitsEingeloest) throws PersistenceException{
      this.getTheObject().setIstBereitsEingeloest(newIstBereitsEingeloest);
   }
   public Vorfuehrung getVorfuehrung() throws PersistenceException{
      return this.getTheObject().getVorfuehrung();
   }
}
