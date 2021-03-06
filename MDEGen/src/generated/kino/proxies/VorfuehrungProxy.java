/**--- Generated at Sat Mar 06 15:22:39 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import db.executer.PersistenceException;
import java.util.Optional;
import db.executer.*;
import generated.kino.Vorfuehrung;
import java.sql.ResultSet;
import generated.kino.Resevierung;
import java.util.Set;
import exceptions.ConstraintViolation;
import generated.kino.Film;
import generated.kino.relationControl.vorfuehrungFilmSupervisor;
import generated.kino.Saal;
import generated.kino.relationControl.vorfuehrungSaalSupervisor;
public class VorfuehrungProxy implements IVorfuehrung{
   private Integer id;
   private Optional<Vorfuehrung> theObject;
   private DBDMLExecuter dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
   public VorfuehrungProxy(Integer id){
      this.id = id;
      this.theObject = Optional.empty();
   }
   public VorfuehrungProxy(Vorfuehrung theObject) throws PersistenceException{
      this(theObject.getId());
      this.theObject = Optional.of(theObject);
   }
   public boolean isObjectPresent() {
      return this.theObject.isPresent();
   }
   public Vorfuehrung getTheObject()
   {
      try{if(!this.isObjectPresent()) this.theObject = Optional.of(this.load());}catch(PersistenceException pe){assert false : "Fatal Error Occured when loading an existing object from DB: " + "Vorfuehrung";}
      return this.theObject.get();
   }
   public Integer getId(){
      return this.id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof IVorfuehrung)) return false;
      return ((IVorfuehrung)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   private Vorfuehrung load() throws PersistenceException {
      ResultSet rs = null;
      try {
         rs = DBExecuterFactory.getConfiguredFactory().getDBDMLExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("Vorfuehrung", this.id);
         Film film = vorfuehrungFilmSupervisor.getInstance().getFilm(this).getTheObject();
         Saal saal = vorfuehrungSaalSupervisor.getInstance().getSaal(this).getTheObject();
         Integer vorfuehrungsNummer = rs.getInt("vorfuehrungsNummer");
         Integer preisParkett = rs.getInt("preisParkett");
         Integer preisMitte = rs.getInt("preisMitte");
         Integer preisLoge = rs.getInt("preisLoge");
         Integer freiePlaetzeParkett = rs.getInt("freiePlaetzeParkett");
         Integer freiePlaetzeMitte = rs.getInt("freiePlaetzeMitte");
         Integer freiePlaetzeLoge = rs.getInt("freiePlaetzeLoge");
         Boolean bereitsVorbei = rs.getBoolean("bereitsVorbei");
         return Vorfuehrung.createAlreadyPersistent(this, film, saal, vorfuehrungsNummer, preisParkett, preisMitte, preisLoge, freiePlaetzeParkett, freiePlaetzeMitte, freiePlaetzeLoge, bereitsVorbei);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
   public Set<Resevierung> getReservierungen() throws PersistenceException{
      return this.getTheObject().getReservierungen();
   }
   public void addToReservierungen(Resevierung arg) throws ConstraintViolation, PersistenceException{
      this.getTheObject().addToReservierungen(arg);
   }
   public boolean removeFromReservierungen(Resevierung arg) throws ConstraintViolation, PersistenceException{
      return this.getTheObject().removeFromReservierungen(arg);
   }
   public Film getFilm() throws PersistenceException{
      return this.getTheObject().getFilm();
   }
   public void setFilm(Film newFilm)throws PersistenceException{
      this.getTheObject().setFilm(newFilm);
   }
   public Saal getSaal() throws PersistenceException{
      return this.getTheObject().getSaal();
   }
   public void setSaal(Saal newSaal)throws PersistenceException{
      this.getTheObject().setSaal(newSaal);
   }
   public Integer getVorfuehrungsNummer() {
      return this.getTheObject().getVorfuehrungsNummer();
   }
   public void setVorfuehrungsNummer(Integer newVorfuehrungsNummer) throws PersistenceException{
      this.getTheObject().setVorfuehrungsNummer(newVorfuehrungsNummer);
   }
   public Integer getPreisParkett() {
      return this.getTheObject().getPreisParkett();
   }
   public void setPreisParkett(Integer newPreisParkett) throws PersistenceException{
      this.getTheObject().setPreisParkett(newPreisParkett);
   }
   public Integer getPreisMitte() {
      return this.getTheObject().getPreisMitte();
   }
   public void setPreisMitte(Integer newPreisMitte) throws PersistenceException{
      this.getTheObject().setPreisMitte(newPreisMitte);
   }
   public Integer getPreisLoge() {
      return this.getTheObject().getPreisLoge();
   }
   public void setPreisLoge(Integer newPreisLoge) throws PersistenceException{
      this.getTheObject().setPreisLoge(newPreisLoge);
   }
   public Integer getFreiePlaetzeParkett() {
      return this.getTheObject().getFreiePlaetzeParkett();
   }
   public void setFreiePlaetzeParkett(Integer newFreiePlaetzeParkett) throws PersistenceException{
      this.getTheObject().setFreiePlaetzeParkett(newFreiePlaetzeParkett);
   }
   public Integer getFreiePlaetzeMitte() {
      return this.getTheObject().getFreiePlaetzeMitte();
   }
   public void setFreiePlaetzeMitte(Integer newFreiePlaetzeMitte) throws PersistenceException{
      this.getTheObject().setFreiePlaetzeMitte(newFreiePlaetzeMitte);
   }
   public Integer getFreiePlaetzeLoge() {
      return this.getTheObject().getFreiePlaetzeLoge();
   }
   public void setFreiePlaetzeLoge(Integer newFreiePlaetzeLoge) throws PersistenceException{
      this.getTheObject().setFreiePlaetzeLoge(newFreiePlaetzeLoge);
   }
   public Boolean getBereitsVorbei() {
      return this.getTheObject().getBereitsVorbei();
   }
   public void setBereitsVorbei(Boolean newBereitsVorbei) throws PersistenceException{
      this.getTheObject().setBereitsVorbei(newBereitsVorbei);
   }
}
