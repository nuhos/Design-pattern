package Calc;

public class ScientificAdapter implements appOperation {

    private final String op;
    private final Object tool; 

    public ScientificAdapter(String op) {
        this.op = op.toLowerCase();
        switch (this.op) {
            case "x²": 
                this.tool = new ScientificSquare(); 
                break;
            case "^":
                this.tool = new ScientificPower();
                break;
            case "sin":
            case "cos":
                this.tool = new ScientificTrig();
                break;
            default: 
                throw new IllegalArgumentException("Unsupported operation: " + op);
        }
    }

    @Override
    public double calculate(double a, double b) {
        switch (op) {
            case "x²": 
                return ((ScientificSquare) tool).calculateSquare(a); 
            case "^": 
                return ((ScientificPower) tool).pow(a, b);
            case "sin": 
                return ((ScientificTrig) tool).sinDeg(a);
            case "cos": 
                return ((ScientificTrig) tool).cosDeg(a); 
            default: 
                throw new IllegalArgumentException("Unknown op: " + op);
        }
    }
}
