package random;

import java.util.HashMap;
import java.util.Map;

public class FileTypeSizeMapping {

    Map<String, Long> resultMappings;
    Map<String, String> typeExtensionMapping;

    public String solution(String S) {

        resultMappings = new HashMap<>();
        typeExtensionMapping = new HashMap<String, String>(){{
            put("mp3", "music");
            put("aac", "music");
            put("flac", "music");
            put("jpg", "image");
            put("bmp", "image");
            put("gif", "image");
            put("mp4", "movie");
            put("avi", "movie");
            put("mkv", "movie");
        }};

        String[] lines = S.split("\n");

        for (String line : lines) {
            String[] fileDetails = line.split(" ");
            long bytes = Long.parseLong(fileDetails[1].substring(0, fileDetails[1].length() - 1));
            String extension = getExtesnion(fileDetails[0]);
            findAndAddInMappings(extension, bytes);
        }

        return createResultString();
    }

    private String createResultString() {
        StringBuilder sg = new StringBuilder("");

        sg.append("music "+resultMappings.getOrDefault("music", 0L)+"b\n");
        sg.append("images "+resultMappings.getOrDefault("image", 0L)+"b\n");
        sg.append("movies "+resultMappings.getOrDefault("movie", 0L)+"b\n");
        sg.append("other "+resultMappings.getOrDefault("other", 0L)+"b");

        return sg.toString();
    }

    private void addInMappings(String type, long bytes){
        long curr = resultMappings.getOrDefault(type,0L);
        resultMappings.put(type, curr + bytes);
    }

    private void findAndAddInMappings(String extension, long bytes){

            if (typeExtensionMapping.containsKey(extension))
                addInMappings(typeExtensionMapping.get(extension), bytes);
            else
                addInMappings("other", bytes);
    }

    private String getExtesnion(String fileName) {
        int extensionStartIndex = -1;
        for (int i = fileName.length() - 1; i >=0 ; i--){
            if (fileName.charAt(i) == '.'){
                extensionStartIndex = i;
                break;
            }
        }
        if (extensionStartIndex == -1)
            throw new IllegalArgumentException("File name not having extension: "+ fileName);

        return fileName.substring(extensionStartIndex + 1);
    }

    public static void main(String[] args) {
        FileTypeSizeMapping fp = new FileTypeSizeMapping();
        String result = fp.solution("my.song.mp3 11b\n" +
                "greatSong.flac 1000b\n" +
                "not3.txt 5b\n" +
                "video.mp4 200b\n" +
                "game.exe 100b\n" +
                "mov!e.mkv 10000b");

        System.out.println(result);

    }

}
