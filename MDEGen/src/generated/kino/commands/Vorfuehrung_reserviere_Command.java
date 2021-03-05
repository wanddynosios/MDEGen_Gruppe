/**--- Generated at Fri Mar 05 12:20:39 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class Vorfuehrung_reserviere_Command extends ObjectCommand<Vorfuehrung, Void>{
   private static final long serialVersionUID = 1497026081L;
   private Resevierung reservierung;
   public Vorfuehrung_reserviere_Command(Vorfuehrung receiver, Resevierung reservierung){
      super(receiver);
      this.reservierung = reservierung;
   }
   public void execute(){
      try{this.receiver.reserviere(reservierung);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
