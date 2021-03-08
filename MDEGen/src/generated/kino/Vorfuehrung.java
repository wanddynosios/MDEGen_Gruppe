/**--- Generated at Mon Mar 08 12:14:37 CET 2021 
 * --- Change only in Editable Sections!  
 * --- Do not touch section numbering!   
 */
package generated.kino;
//10 ===== GENERATED:      Import Section =========
import java.sql.SQLException;
import db.connection.NoConnectionException;
import db.connection.TypeKeyManager;
import db.executer.PersistenceExecuterFactory;
import generated.kino.proxies.VorfuehrungProxy;
import observation.Observable;
import generated.kino.proxies.IVorfuehrung;
import generated.kino.relationControl.*;
import generated.kino.proxies.*;
import db.executer.PersistenceException;
import java.util.Set;
import java.util.HashSet;
import exceptions.ConstraintViolation;
import java.util.Collection;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class Vorfuehrung extends Observable implements java.io.Serializable, IVorfuehrung
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   private Integer vorfuehrungsNummer;
   private Integer preisParkett;
   private Integer preisMitte;
   private Integer preisLoge;
   private Integer freiePlaetzeParkett;
   private Integer freiePlaetzeMitte;
   private Integer freiePlaetzeLoge;
   private Boolean bereitsVorbei;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private Vorfuehrung(Integer id, Film film, Saal saal, Integer vorfuehrungsNummer, Integer preisParkett, Integer preisMitte, Integer preisLoge, Integer freiePlaetzeParkett, Integer freiePlaetzeMitte, Integer freiePlaetzeLoge, Boolean bereitsVorbei, boolean objectOnly)
   throws PersistenceException{
      super();
      this.setId(id);
      vorfuehrungFilmSupervisor.getInstance().set(this, film);
      vorfuehrungSaalSupervisor.getInstance().set(this, saal);
      this.vorfuehrungsNummer = vorfuehrungsNummer;
      this.preisParkett = preisParkett;
      this.preisMitte = preisMitte;
      this.preisLoge = preisLoge;
      this.freiePlaetzeParkett = freiePlaetzeParkett;
      this.freiePlaetzeMitte = freiePlaetzeMitte;
      this.freiePlaetzeLoge = freiePlaetzeLoge;
      this.bereitsVorbei = bereitsVorbei;
      if(objectOnly) return;
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static Vorfuehrung createAlreadyPersistent(VorfuehrungProxy proxy, Film film, Saal saal, Integer vorfuehrungsNummer, Integer preisParkett, Integer preisMitte, Integer preisLoge, Integer freiePlaetzeParkett, Integer freiePlaetzeMitte, Integer freiePlaetzeLoge, Boolean bereitsVorbei)throws PersistenceException{
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new Vorfuehrung(proxy.getId(), film, saal, vorfuehrungsNummer, preisParkett, preisMitte, preisLoge, freiePlaetzeParkett, freiePlaetzeMitte, freiePlaetzeLoge, bereitsVorbei, true);
   }
   public static Vorfuehrung createFresh(Film film, Saal saal, Integer vorfuehrungsNummer, Integer preisParkett, Integer preisMitte, Integer preisLoge, Integer freiePlaetzeParkett, Integer freiePlaetzeMitte, Integer freiePlaetzeLoge, Boolean bereitsVorbei)throws PersistenceException{
      db.executer.DBDMLExecuter dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("Vorfuehrung", "id, typeKey, vorfuehrungsNummer, preisParkett, preisMitte, preisLoge, freiePlaetzeParkett, freiePlaetzeMitte, freiePlaetzeLoge, bereitsVorbei", 
         id.toString() + ", " + TypeKeyManager.getTheInstance().getTypeKey("Kino", "Vorfuehrung").toString() + ", " + vorfuehrungsNummer.toString() + ", " + preisParkett.toString() + ", " + preisMitte.toString() + ", " + preisLoge.toString() + ", " + freiePlaetzeParkett.toString() + ", " + freiePlaetzeMitte.toString() + ", " + freiePlaetzeLoge.toString() + ", " + bereitsVorbei.toString());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      Vorfuehrung me = new Vorfuehrung(id, film, saal, vorfuehrungsNummer, preisParkett, preisMitte, preisLoge, freiePlaetzeParkett, freiePlaetzeMitte, freiePlaetzeLoge, bereitsVorbei, false);
      Kino.getInstance().addVorfuehrungProxy(new VorfuehrungProxy(me));
      return me;
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public Vorfuehrung getTheObject(){
      return this;
   }
   public Integer getId(){
      return this.id;
   }
   protected void setId(Integer id){
      this.id = id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof IVorfuehrung)) return false;
      return ((IVorfuehrung)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   public Set<Resevierung> getReservierungen() throws PersistenceException{
      Set<Resevierung> result = new HashSet<>();
      for (IResevierung i : vorfuehrungReservierungenSupervisor.getInstance().getReservierungen(this)) result.add(i.getTheObject());
      return result;
   }
   public void addToReservierungen(Resevierung arg) throws ConstraintViolation, PersistenceException{
      vorfuehrungReservierungenSupervisor.getInstance().add(this, arg);
   }
   public boolean removeFromReservierungen(Resevierung arg) throws ConstraintViolation, PersistenceException{
      return vorfuehrungReservierungenSupervisor.getInstance().remove(this, arg);
   }
   public Film getFilm() throws PersistenceException{
      return vorfuehrungFilmSupervisor.getInstance().getFilm(this).getTheObject();
   }
   public void setFilm(Film newFilm)throws PersistenceException{
      vorfuehrungFilmSupervisor.getInstance().change(this, this.getFilm(), newFilm);
   }
   public Saal getSaal() throws PersistenceException{
      return vorfuehrungSaalSupervisor.getInstance().getSaal(this).getTheObject();
   }
   public void setSaal(Saal newSaal)throws PersistenceException{
      vorfuehrungSaalSupervisor.getInstance().change(this, this.getSaal(), newSaal);
   }
   public Set<Buchung> getBuchungen() throws PersistenceException{
      Set<Buchung> result = new HashSet<>();
      for (IBuchung i : vorfuehrungBuchungenSupervisor.getInstance().getBuchungen(this)) result.add(i.getTheObject());
      return result;
   }
   public void addToBuchungen(Buchung arg) throws ConstraintViolation, PersistenceException{
      vorfuehrungBuchungenSupervisor.getInstance().add(this, arg);
   }
   public boolean removeFromBuchungen(Buchung arg) throws ConstraintViolation, PersistenceException{
      return vorfuehrungBuchungenSupervisor.getInstance().remove(this, arg);
   }
   public Integer getVorfuehrungsNummer() {
      return this.vorfuehrungsNummer;
   }
   public void setVorfuehrungsNummer(Integer newVorfuehrungsNummer) throws PersistenceException{
      this.vorfuehrungsNummer = newVorfuehrungsNummer;
      try{PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter().update("Vorfuehrung", "vorfuehrungsNummer", newVorfuehrungsNummer.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public Integer getPreisParkett() {
      return this.preisParkett;
   }
   public void setPreisParkett(Integer newPreisParkett) throws PersistenceException{
      this.preisParkett = newPreisParkett;
      try{PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter().update("Vorfuehrung", "preisParkett", newPreisParkett.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public Integer getPreisMitte() {
      return this.preisMitte;
   }
   public void setPreisMitte(Integer newPreisMitte) throws PersistenceException{
      this.preisMitte = newPreisMitte;
      try{PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter().update("Vorfuehrung", "preisMitte", newPreisMitte.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public Integer getPreisLoge() {
      return this.preisLoge;
   }
   public void setPreisLoge(Integer newPreisLoge) throws PersistenceException{
      this.preisLoge = newPreisLoge;
      try{PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter().update("Vorfuehrung", "preisLoge", newPreisLoge.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public Integer getFreiePlaetzeParkett() {
      return this.freiePlaetzeParkett;
   }
   public void setFreiePlaetzeParkett(Integer newFreiePlaetzeParkett) throws PersistenceException{
      this.freiePlaetzeParkett = newFreiePlaetzeParkett;
      try{PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter().update("Vorfuehrung", "freiePlaetzeParkett", newFreiePlaetzeParkett.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public Integer getFreiePlaetzeMitte() {
      return this.freiePlaetzeMitte;
   }
   public void setFreiePlaetzeMitte(Integer newFreiePlaetzeMitte) throws PersistenceException{
      this.freiePlaetzeMitte = newFreiePlaetzeMitte;
      try{PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter().update("Vorfuehrung", "freiePlaetzeMitte", newFreiePlaetzeMitte.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public Integer getFreiePlaetzeLoge() {
      return this.freiePlaetzeLoge;
   }
   public void setFreiePlaetzeLoge(Integer newFreiePlaetzeLoge) throws PersistenceException{
      this.freiePlaetzeLoge = newFreiePlaetzeLoge;
      try{PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter().update("Vorfuehrung", "freiePlaetzeLoge", newFreiePlaetzeLoge.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   public Boolean getBereitsVorbei() {
      return this.bereitsVorbei;
   }
   public void setBereitsVorbei(Boolean newBereitsVorbei) throws PersistenceException{
      this.bereitsVorbei = newBereitsVorbei;
      try{PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter().update("Vorfuehrung", "bereitsVorbei", newBereitsVorbei.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============
/**
 * Wandelt eine Reservierung in eine konkrete Buchung umr
 */
   public void buche(Resevierung reservierung, Collection<BuchungsEinheit> buchungsEinheiten) throws PlatzGebuchtException, PersistenceException {
      // TODO: Implement Operation buche
      return;
   }
/**
 * Reserviert eine bestimmte Anzahl an Plätzen in einer Kategorie
 */
   public void reserviere(Resevierung reservierung) throws NichtGenugPlaetzeException, PersistenceException, ConstraintViolation {
      Kategorie kategorie = reservierung.getKategorie().getTheObject();
      Integer freiePlaetze = 0;
      switch (kategorie.getClass().getSimpleName()){
         case "KategorieParkett": freiePlaetze = getFreiePlaetzeParkett(); break;
         case "KategorieMitte": freiePlaetze = getFreiePlaetzeMitte(); break;
         case "KategorieLoge": freiePlaetze = getFreiePlaetzeLoge(); break;
         default: throw new RuntimeException("Unbekannte Kategorie. Schreibweise geaendert?");
      }
      if (freiePlaetze - reservierung.getAnzahlPlaetze() < 0)
         throw new NichtGenugPlaetzeException();
      switch (kategorie.getClass().getSimpleName()){
         case "KategorieParkett": setFreiePlaetzeParkett(getFreiePlaetzeParkett()-reservierung.getAnzahlPlaetze()); break;
         case "KategorieMitte": setFreiePlaetzeMitte(getFreiePlaetzeMitte()-reservierung.getAnzahlPlaetze()); break;
         case "KategorieLoge": setFreiePlaetzeLoge(getFreiePlaetzeLoge()-reservierung.getAnzahlPlaetze()); break;
         default: throw new RuntimeException("Unbekannte Kategorie. Schreibweise geaendert?");
      }
      reservierung.getVorfuehrung().addToReservierungen(reservierung);
   }

   @Override
   public String toString() {
      return "Vorfuehrung{" +
              "id=" + id +
              ", vorfuehrungsNummer=" + vorfuehrungsNummer +
              ", preisParkett=" + preisParkett +
              ", preisMitte=" + preisMitte +
              ", preisLoge=" + preisLoge +
              ", freiePlaetzeParkett=" + freiePlaetzeParkett +
              ", freiePlaetzeMitte=" + freiePlaetzeMitte +
              ", freiePlaetzeLoge=" + freiePlaetzeLoge +
              '}';
   }

   //90 ===== GENERATED: End of Your Operations ======
}
