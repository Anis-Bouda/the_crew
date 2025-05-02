public class Connection {
    public AbstractComponent compSource;
    public String portTypeSource;
    public int portIndexSource;
    
    public AbstractComponent compTarget;
    public String portTypeTarget;
    public int portIndexTarget;
    
    public Ligne line;
    
    // lien avec le fil du modele
    public Fil fil; 

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

    public void setFil(Fil fil) {
        this.fil = fil;
    }

    public Fil getFil() {
        return fil;
    }
}
