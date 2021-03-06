/**--- Generated at Sat Mar 06 12:51:23 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class reservierePlaetze_Command extends ServiceCommand<Void>{
   private static final long serialVersionUID = 78023862L;
   private Resevierung reservierung;
   public reservierePlaetze_Command(Resevierung reservierung){
      super();
      this.reservierung = reservierung;
   }
   public void execute(){
      try{Kino.getInstance().reservierePlaetze(reservierung);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
