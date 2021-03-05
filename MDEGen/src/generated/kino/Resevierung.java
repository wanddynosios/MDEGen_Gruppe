/**--- Generated at Fri Mar 05 15:18:57 CET 2021 
 * --- Change only in Editable Sections!  
 * --- Do not touch section numbering!   
 */
package generated.kino;
//10 ===== GENERATED:      Import Section =========
import java.sql.SQLException;
import db.connection.NoConnectionException;
import db.connection.TypeKeyManager;
import db.executer.PersistenceExecuterFactory;
import generated.kino.proxies.ResevierungProxy;
import observation.Observable;
import generated.kino.proxies.IResevierung;
import generated.kino.relationControl.*;
import generated.kino.proxies.*;
import db.executer.PersistenceException;
import java.util.List;
import java.util.ArrayList;
import exceptions.ConstraintViolation;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class Resevierung extends Observable implements java.io.Serializable, IResevierung
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   private String name;
   private Integer anzahlPlaetze;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private Resevierung(Integer id, Kategorie kategorie, String name, Integer anzahlPlaetze, Vorfuehrung vorfuehrung, boolean objectOnly)
   throws PersistenceException{
      super();
      this.setId(id);
      reservierungKategorieSupervisor.getInstance().set(this, kategorie);
      this.name = name;
      this.anzahlPlaetze = anzahlPlaetze;
      if(objectOnly) return;
      try{vorfuehrungReservierungenSupervisor.getInstance().add(vorfuehrung,this);}catch(ConstraintViolation cv){}// Ok, because consistency is guaranteed with this statement
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static Resevierung createAlreadyPersistent(ResevierungProxy proxy, Kategorie kategorie, String name, Integer anzahlPlaetze, Vorfuehrung vorfuehrung)throws PersistenceException{
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new Resevierung(proxy.getId(), kategorie, name, anzahlPlaetze, vorfuehrung, true);
   }
   public static Resevierung createFresh(Kategorie kategorie, String name, Integer anzahlPlaetze, Vorfuehrung vorfuehrung)throws PersistenceException{
      db.executer.DBDMLExecuter dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("Resevierung", "id, typeKey, name, anzahlPlaetze", 
         id.toString() + ", " + TypeKeyManager.getTheInstance().getTypeKey("Kino", "Resevierung").toString() + ", " + "'" + name + "'" + ", " + anzahlPlaetze.toString());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      Resevierung me = new Resevierung(id, kategorie, name, anzahlPlaetze, vorfuehrung, false);
      Kino.getInstance().addResevierungProxy(new ResevierungProxy(me));
      return me;
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public Resevierung getTheObject(){
      return this;
   }
   public Integer getId(){
      return this.id;
   }
   protected void setId(Integer id){
      this.id = id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof IResevierung)) return false;
      return ((IResevierung)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   public List<Sitz> getSitze() throws PersistenceException{
      List<Sitz> result = new ArrayList<>();
      for (ISitz i : reservierungSitzeSupervisor.getInstance().getSitze(this)) result.add(i.getTheObject());
      return result;
   }
   public void addToSitze(Sitz arg) throws PersistenceException{
      reservierungSitzeSupervisor.getInstance().add(this, arg);
   }
   public boolean removeFromSitze(Sitz arg) throws PersistenceException{
      return reservierungSitzeSupervisor.getInstance().remove(this, arg);
   }
   public Kategorie getKategorie() throws PersistenceException{
      return reservierungKategorieSupervisor.getInstance().getKategorie(this).getTheObject();
   }
   public void setKategorie(Kategorie newKategorie)throws PersistenceException{
      reservierungKategorieSupervisor.getInstance().change(this, this.getKategorie(), newKategorie);
   }
   public String getName() {
      return this.name;
   }
   public void setName(String newName) throws PersistenceException{
      this.name = newName;
      try{PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter().update("Resevierung", "name", "'" + newName + "'", this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public Integer getAnzahlPlaetze() {
      return this.anzahlPlaetze;
   }
   public void setAnzahlPlaetze(Integer newAnzahlPlaetze) throws PersistenceException{
      this.anzahlPlaetze = newAnzahlPlaetze;
      try{PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter().update("Resevierung", "anzahlPlaetze", newAnzahlPlaetze.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public Vorfuehrung getVorfuehrung() throws PersistenceException{
      return vorfuehrungReservierungenSupervisor.getInstance().getVorfuehrung(this).getTheObject();
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
