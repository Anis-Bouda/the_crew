package projet;

public class TestComposants {
	public static void main(String[] args) {
        System.out.println("=== Début des tests ===\n");
        
        testShiftRegister();
        System.out.println("\n=== Fin des tests ===");
}
	public static void testShiftRegister() {
        Composant ShiftRegister = new ShiftRegister("registre", 2, 0, 8, ShiftType.CIRCULAIRE, false);
        ShiftRegister.setInputs(0, State.UNKNOWN);
        ShiftRegister.setInputs(1, State.False);
        ShiftRegister.setInputs(2, State.True);
        ShiftRegister.setInputs(3, State.True);
        ShiftRegister.setInputs(4, State.ERROR);
        ShiftRegister.setInputs(5, State.True);
        ShiftRegister.setInputs(6, State.False);
        ShiftRegister.setInputs(7, State.True);
        ShiftRegister.evaluate();
        System.out.println("Test -> [" +
                ShiftRegister.getOutput(0) + ", " + ShiftRegister.getOutput(1) + ", " +
                ShiftRegister.getOutput(2) + ", " + ShiftRegister.getOutput(3) + ", " +
                ShiftRegister.getOutput(4) + ", " + ShiftRegister.getOutput(5) + ", " +
                ShiftRegister.getOutput(6) + ", " + ShiftRegister.getOutput(7) +"] ");
    }
}
