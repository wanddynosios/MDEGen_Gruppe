/**--- Generated at Sat Mar 06 17:55:51 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class Saal_constructor_Command extends ServiceCommand<Saal>{
   private static final long serialVersionUID = 1153933162L;
   private Integer  SaalNummer;
   public Saal_constructor_Command(Integer  SaalNummer){
      super();
      this.SaalNummer = SaalNummer;
   }
   public void execute(){
      try{this.result = Saal.createFresh(SaalNummer);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
