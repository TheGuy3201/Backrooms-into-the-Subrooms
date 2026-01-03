package levels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LevelLoader {
    final private List<Level> levels;
    
    public LevelLoader() {
        levels = new ArrayList<>();
        loadLevels();
    }
    
    private void loadLevels() {
        try {
            InputStream is = getClass().getResourceAsStream("/levels/levels.json");
            if (is == null) {
                System.err.println("Could not find levels.json");
                return;
            }
            
            StringBuilder jsonContent;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                jsonContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonContent.append(line);
                }
            }
            
            parseJSON(jsonContent.toString());
            
        } catch (IOException e) {
            System.err.println("Error loading levels.json: " + e.getMessage());
        }
    }
    
    private void parseJSON(String json) {
        // Simple JSON parser for our specific format
        json = json.trim();
        
        // Find the levels array
        int levelsStart = json.indexOf("\"levels\"");
        if (levelsStart == -1) return;
        
        int arrayStart = json.indexOf("[", levelsStart);
        int arrayEnd = json.lastIndexOf("]");
        
        if (arrayStart == -1 || arrayEnd == -1) return;
        
        String levelsArray = json.substring(arrayStart + 1, arrayEnd);
        
        // Split by objects (between { and })
        String[] objects = levelsArray.split("\\},\\s*\\{");
        
        for (String obj : objects) {
            obj = obj.trim();
            if (obj.startsWith("{")) obj = obj.substring(1);
            if (obj.endsWith("}")) obj = obj.substring(0, obj.length() - 1);
            
            int id = extractInt(obj, "id");
            String name = extractString(obj, "name");
            String filePath = extractString(obj, "filePath");
            
            if (name != null && filePath != null) {
                levels.add(new Level(id, name, filePath));
            }
        }
        
        System.out.println("Loaded " + levels.size() + " levels from JSON");
    }
    
    private int extractInt(String json, String key) {
        String pattern = "\"" + key + "\"\\s*:\\s*(\\d+)";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = p.matcher(json);
        if (m.find()) {
            return Integer.parseInt(m.group(1));
        }
        return -1;
    }
    
    private String extractString(String json, String key) {
        String pattern = "\"" + key + "\"\\s*:\\s*\"([^\"]+)\"";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = p.matcher(json);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }
    
    public Level getLevelById(int id) {
        for (Level level : levels) {
            if (level.getId() == id) {
                return level;
            }
        }
        return null;
    }
    
    public Level getLevelByName(String name) {
        for (Level level : levels) {
            if (level.getName().equals(name)) {
                return level;
            }
        }
        return null;
    }
    
    public List<Level> getAllLevels() {
        return new ArrayList<>(levels);
    }
    
    public int getLevelCount() {
        return levels.size();
    }
}
