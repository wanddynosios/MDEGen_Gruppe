/**--- Generated at Sat Mar 06 14:34:07 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class Resevierung_getAnzahlPlaetzeMitte_Command extends ObjectCommand<Resevierung, Integer>{
   private static final long serialVersionUID = -350195760L;
   public Resevierung_getAnzahlPlaetzeMitte_Command(Resevierung receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getAnzahlPlaetzeMitte();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
