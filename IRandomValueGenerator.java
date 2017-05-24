package processor;

public interface IRandomValueGenerator {

	public int getNextInt();

	public boolean getTrueWithProbability(double p);
}
