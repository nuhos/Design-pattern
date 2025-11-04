package Calc;

public class ScientificAdapter implements appOperation {

    private final String op;
    private final Object tool;

    public ScientificAdapter(String op) {
        this.op = op.toLowerCase();
        switch (this.op) {
            case "power": this.tool = new ScientificPower(); break;
            case "log": this.tool = new ScientificLog(); break;
            case "sin":
            case "cos": this.tool = new ScientificTrig(); break;
            default: throw new IllegalArgumentException("Unsupported operation: " + op);
        }
    }

    @Override
    public double calculate(double a, double b) {
        switch (op) {
            case "power": return ((ScientificPower) tool).pow(a, b);
            case "log":
                if (a <= 0) throw new IllegalArgumentException("Invalid log arg");
                return ((ScientificLog) tool).log10(a);
            case "sin": return ((ScientificTrig) tool).sinDeg(a);
            case "cos": return ((ScientificTrig) tool).cosDeg(a);
            default: throw new IllegalArgumentException("Unknown op: " + op);
        }
    }
}
