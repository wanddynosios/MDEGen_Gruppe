/**--- Generated at Sat Mar 06 14:34:07 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class Resevierung_getAnzahlPlaetzeLoge_Command extends ObjectCommand<Resevierung, Integer>{
   private static final long serialVersionUID = 323439506L;
   public Resevierung_getAnzahlPlaetzeLoge_Command(Resevierung receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getAnzahlPlaetzeLoge();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
