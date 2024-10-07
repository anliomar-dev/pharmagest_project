package models;

public class LigneCmdLivraison {
    private int qteCmd;
    private int qteLivree;

    // Constructeur
    public LigneCmdLivraison(int qteCmd, int qteLivree) {
        this.qteCmd = qteCmd;
        this.qteLivree = qteLivree;
    }

    // Getters et Setters
    public int getQteCmd() {
        return qteCmd;
    }

    public void setQteCmd(int qteCmd) {
        this.qteCmd = qteCmd;
    }

    public int getQteLivree() {
        return qteLivree;
    }

    public void setQteLivree(int qteLivree) {
        this.qteLivree = qteLivree;
    }
}

