import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespostaAPI {
    private List<User> results;

    public List<User> getResults() {return results;}
    public void setResults(List<User> results) { this.results = results; }
}
