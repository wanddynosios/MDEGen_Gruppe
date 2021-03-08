/**--- Generated at Mon Mar 08 14:44:48 CET 2021 
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
   private Boolean istBereitsEingeloest;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private Resevierung(Integer id, Kategorie kategorie, String name, Integer anzahlPlaetze, Boolean istBereitsEingeloest, Vorfuehrung vorfuehrung, boolean objectOnly)
   throws PersistenceException{
      super();
      this.setId(id);
      reservierungKategorieSupervisor.getInstance().set(this, kategorie);
      this.name = name;
      this.anzahlPlaetze = anzahlPlaetze;
      this.istBereitsEingeloest = istBereitsEingeloest;
      if(objectOnly) return;
      try{vorfuehrungReservierungenSupervisor.getInstance().add(vorfuehrung,this);}catch(ConstraintViolation cv){}// Ok, because consistency is guaranteed with this statement
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static Resevierung createAlreadyPersistent(ResevierungProxy proxy, Kategorie kategorie, String name, Integer anzahlPlaetze, Boolean istBereitsEingeloest, Vorfuehrung vorfuehrung)throws PersistenceException{
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new Resevierung(proxy.getId(), kategorie, name, anzahlPlaetze, istBereitsEingeloest, vorfuehrung, true);
   }
   public static Resevierung createFresh(Kategorie kategorie, String name, Integer anzahlPlaetze, Boolean istBereitsEingeloest, Vorfuehrung vorfuehrung)throws PersistenceException{
      db.executer.DBDMLExecuter dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("Resevierung", "id, typeKey, name, anzahlPlaetze, istBereitsEingeloest", 
         id.toString() + ", " + TypeKeyManager.getTheInstance().getTypeKey("Kino", "Resevierung").toString() + ", " + "'" + name + "'" + ", " + anzahlPlaetze.toString() + ", " + istBereitsEingeloest.toString());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      Resevierung me = new Resevierung(id, kategorie, name, anzahlPlaetze, istBereitsEingeloest, vorfuehrung, false);
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
   public Boolean getIstBereitsEingeloest() {
      return this.istBereitsEingeloest;
   }
   public void setIstBereitsEingeloest(Boolean newIstBereitsEingeloest) throws PersistenceException{
      this.istBereitsEingeloest = newIstBereitsEingeloest;
      try{PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter().update("Resevierung", "istBereitsEingeloest", newIstBereitsEingeloest.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public Vorfuehrung getVorfuehrung() throws PersistenceException{
      return vorfuehrungReservierungenSupervisor.getInstance().getVorfuehrung(this).getTheObject();
   }
   //80 ===== Editable : Your Operations =============
/**
 * gibt die Anzahl der Plaetze Mitte
 */
   public Integer getAnzahlPlaetzeMitte(){
      // TODO: Implement Operation getAnzahlPlaetzeMitte
      return null;
   }
/**
 * gibt die Anzahl der Plaetze Loge
 */
   public Integer getAnzahlPlaetzeLoge(){
      // TODO: Implement Operation getAnzahlPlaetzeLoge
      return null;
   }
/**
 * gibt die Anzahl der Plaetze Parkett
 */
   public Integer getAnzahlPlaetzeParkett(){
      // TODO: Implement Operation getAnzahlPlaetzeParkett
      return null;
   }
//90 ===== GENERATED: End of Your Operations ======
}
