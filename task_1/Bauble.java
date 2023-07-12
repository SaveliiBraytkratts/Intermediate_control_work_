import java.util.Objects;

public class Bauble implements Comparable<Bauble> {

    private int toyId;
    private String toyTitle;
    private int toyVictoryFrequency;

    public Bauble(int toyId, String toyTitle, int toyVictoryFrequency) {
        this.toyId = toyId;
        this.toyTitle = toyTitle;
        this.toyVictoryFrequency = toyVictoryFrequency;
    }

    public int getToyId() {
        return toyId;
    }

    public String getToyTitle() {
        return toyTitle;
    }

    public int getToyVictoryFrequency() {
        return toyVictoryFrequency;
    }

    public void setToyVictoryFrequency(int toyVictoryFrequency) {
        this.toyVictoryFrequency = toyVictoryFrequency;
    }

    public String getInfo() {
        return String.format("ID: %d, Title: %s", toyId, toyTitle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bauble toy = (Bauble) o;
        return toyTitle.equals(toy.toyTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toyTitle);
    }

    @Override
    public int compareTo(Bauble o) {
        return Integer.compare(this.toyVictoryFrequency, o.toyVictoryFrequency);
    }
}