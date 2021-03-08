/**--- Generated at Mon Mar 08 14:44:48 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class Film_constructor_Command extends ServiceCommand<Film>{
   private static final long serialVersionUID = -1406528031L;
   private String  filmName;
   public Film_constructor_Command(String  filmName){
      super();
      this.filmName = filmName;
   }
   public void execute(){
      try{this.result = Film.createFresh(filmName);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
