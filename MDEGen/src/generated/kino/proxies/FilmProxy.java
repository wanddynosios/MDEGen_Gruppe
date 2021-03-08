/**--- Generated at Mon Mar 08 14:44:48 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import db.executer.PersistenceException;
import java.util.Optional;
import db.executer.*;
import generated.kino.Film;
import java.sql.ResultSet;
public class FilmProxy implements IFilm{
   private Integer id;
   private Optional<Film> theObject;
   private DBDMLExecuter dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
   public FilmProxy(Integer id){
      this.id = id;
      this.theObject = Optional.empty();
   }
   public FilmProxy(Film theObject) throws PersistenceException{
      this(theObject.getId());
      this.theObject = Optional.of(theObject);
   }
   public boolean isObjectPresent() {
      return this.theObject.isPresent();
   }
   public Film getTheObject()
   {
      try{if(!this.isObjectPresent()) this.theObject = Optional.of(this.load());}catch(PersistenceException pe){assert false : "Fatal Error Occured when loading an existing object from DB: " + "Film";}
      return this.theObject.get();
   }
   public Integer getId(){
      return this.id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof IFilm)) return false;
      return ((IFilm)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   private Film load() throws PersistenceException {
      ResultSet rs = null;
      try {
         rs = DBExecuterFactory.getConfiguredFactory().getDBDMLExecuter().selectIdSpecifiedCursorAleadyAtFirstRow("Film", this.id);
         String filmName = rs.getString("filmName");
         return Film.createAlreadyPersistent(this, filmName);
      } catch (Exception e) {throw new PersistenceException(e.getMessage());}
   }
   public String getFilmName() {
      return this.getTheObject().getFilmName();
   }
   public void setFilmName(String newFilmName) throws PersistenceException{
      this.getTheObject().setFilmName(newFilmName);
   }
}
