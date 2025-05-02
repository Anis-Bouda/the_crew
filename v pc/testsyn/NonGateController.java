public class NonGateController {
    private NON model;
    private NonGateView view;

    public NonGateController(NON model, NonGateView view) {
        this.model = model;
        this.view = view;

        // Ajout d'un écouteur pour le bouton
        this.view.addEvaluateListener(e -> evaluateGate());
    }

    private void evaluateGate() {
        State inputState = view.getSelectedInput();
        model.setInputs(0,inputState);
        model.evaluate();
        view.updateOutput(model.getOutput(0));
    }
}