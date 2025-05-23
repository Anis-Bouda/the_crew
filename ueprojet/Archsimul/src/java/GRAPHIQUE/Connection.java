package GRAPHIQUE ;
import Logique.Principale.*;
public class Connection {
    public AbstractComponent compSource;
    public String portTypeSource;
    public int portIndexSource;
    
    public AbstractComponent compTarget;
    public String portTypeTarget;
    public int portIndexTarget;
    
    public Ligne line;
    
    public File filLogic;
    public Wire filview;
    
    public Connection(AbstractComponent compSource, String portTypeSource, int portIndexSource,
                      AbstractComponent compTarget, String portTypeTarget, int portIndexTarget,
                      Ligne line) {
        this.compSource = compSource;
        this.portTypeSource = portTypeSource;
        this.portIndexSource = portIndexSource;
        this.compTarget = compTarget;
        this.portTypeTarget = portTypeTarget;
        this.portIndexTarget = portIndexTarget;
        this.line = line;
    }
}
