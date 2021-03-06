/**--- Generated at Sat Mar 06 15:22:39 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class holeFilm_Command extends ServiceCommand<Film>{
   private static final long serialVersionUID = -884811493L;
   private String filmName;
   public holeFilm_Command(String filmName){
      super();
      this.filmName = filmName;
   }
   public void execute(){
      try{this.result = Kino.getInstance().holeFilm(filmName);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
