/**--- Generated at Fri Mar 05 12:20:39 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class holeSaal_Command extends ServiceCommand<Saal>{
   private static final long serialVersionUID = -1404374992L;
   private Integer saalId;
   public holeSaal_Command(Integer saalId){
      super();
      this.saalId = saalId;
   }
   public void execute(){
      try{this.result = Kino.getInstance().holeSaal(saalId);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
