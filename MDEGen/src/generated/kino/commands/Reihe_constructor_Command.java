/**--- Generated at Sat Mar 06 13:28:16 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class Reihe_constructor_Command extends ServiceCommand<Reihe>{
   private static final long serialVersionUID = 801620439L;
   private Kategorie kategorie;
   private Integer  reihenNummer;
   public Reihe_constructor_Command(Kategorie kategorie, Integer  reihenNummer){
      super();
      this.kategorie = kategorie;
      this.reihenNummer = reihenNummer;
   }
   public void execute(){
      try{this.result = Reihe.createFresh(kategorie, reihenNummer);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
