package andreistefanwork.backend.dtos;

public record AgePrediction(String name, int age) {
    private AgePrediction() {
        this("", 0);
    }

    public static AgePrediction empty() {
        return new AgePrediction();
    }
}
