/**--- Generated at Fri Mar 05 18:16:22 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import idManagement.Identifiable;
import db.executer.PersistenceException;
import generated.kino.Film;
public interface IFilm extends Identifiable{
   public Film getTheObject();
   public Integer getId();
   public String getFilmName() ;
   public void setFilmName(String newFilmName) throws PersistenceException;
}
