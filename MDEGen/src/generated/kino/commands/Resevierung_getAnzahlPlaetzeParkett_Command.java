/**--- Generated at Sat Mar 06 14:34:07 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class Resevierung_getAnzahlPlaetzeParkett_Command extends ObjectCommand<Resevierung, Integer>{
   private static final long serialVersionUID = 240337740L;
   public Resevierung_getAnzahlPlaetzeParkett_Command(Resevierung receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getAnzahlPlaetzeParkett();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
