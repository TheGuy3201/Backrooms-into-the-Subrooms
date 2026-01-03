package levels;

public class Level {
    private final int id;
    private String name;
    private final String filePath;
    
    public Level(int id, String name, String filePath) {
        this.id = id;
        this.name = name;
        this.filePath = filePath;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getFilePath() {
        return filePath;
    }

    public void setName(String name) {
        this.name = name;
    }
}
