/**--- Generated at Fri Mar 05 18:16:22 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class BuchungsEinheit_constructor_Command extends ServiceCommand<BuchungsEinheit>{
   private static final long serialVersionUID = 1461437922L;
   private Integer  reihenNr;
   private Integer  sitzNr;
   public BuchungsEinheit_constructor_Command(Integer  reihenNr, Integer  sitzNr){
      super();
      this.reihenNr = reihenNr;
      this.sitzNr = sitzNr;
   }
   public void execute(){
      try{this.result = BuchungsEinheit.createFresh(reihenNr, sitzNr);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
