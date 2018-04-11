import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ResourceManager {

    private List<Source> sources;

    public ResourceManager() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        InputStream input = getClass().getResourceAsStream("sources.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }

        sources = mapper.readValue(out.toString(), new TypeReference<List<Source>>(){});
    }

    public List<Source> getSources() {
        return sources;
    }
}
