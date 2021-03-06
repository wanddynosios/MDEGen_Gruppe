/**--- Generated at Sat Mar 06 15:22:40 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class Sitz_constructor_Command extends ServiceCommand<Sitz>{
   private static final long serialVersionUID = -1505581889L;
   private Integer  sitzNummer;
   private Reihe  reihe;
   public Sitz_constructor_Command(Integer  sitzNummer, Reihe  reihe){
      super();
      this.sitzNummer = sitzNummer;
      this.reihe = reihe;
   }
   public void execute(){
      try{this.result = Sitz.createFresh(sitzNummer, reihe);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
