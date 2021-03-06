/**--- Generated at Sat Mar 06 17:09:20 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class holeReservierung_Command extends ServiceCommand<Resevierung>{
   private static final long serialVersionUID = -1427588606L;
   private String reservierungsName;
   public holeReservierung_Command(String reservierungsName){
      super();
      this.reservierungsName = reservierungsName;
   }
   public void execute(){
      try{this.result = Kino.getInstance().holeReservierung(reservierungsName);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
