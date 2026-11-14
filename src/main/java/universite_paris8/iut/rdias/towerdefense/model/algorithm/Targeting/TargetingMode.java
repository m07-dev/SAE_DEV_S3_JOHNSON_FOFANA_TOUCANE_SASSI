package universite_paris8.iut.rdias.towerdefense.model.algorithm.Targeting;

//j'ai fait un enum pour un potentiel bouton

public enum TargetingMode {
    NEAREST("Proche", new NearestEnemyStrategy()),
    STRONGEST("Resistant", new StrongestEnemyStrategy()),
    FASTEST("Rapide", new FastestEnemyStrategy());

    private final String label;
    private final TargetingStrategy strategy;

    TargetingMode(String label, TargetingStrategy strategy) {
        this.label = label;
        this.strategy = strategy;
    }

    public String getLabel() { return label; }
    public TargetingStrategy getStrategy() { return strategy; }

    public TargetingMode next() {
        TargetingMode[] modes = values();
        return modes[(ordinal() + 1) % modes.length];
    }
}
