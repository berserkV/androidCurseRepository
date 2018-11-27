package dev.berserk.firststeps.dcrecyclerview.models;

public class CharacterDC {

    public String title;

    public String year;

    public String mainCharacter;

    public String image;

    public CharacterDC(String title, String year, String mainCharacter, String image) {
        this.title = title;
        this.year = year;
        this.mainCharacter = mainCharacter;
        this.image = image;
    }

    public CharacterDC() {

    }

    @Override
    public String toString() {
        return "CharacterDC{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", mainCharacter='" + mainCharacter + '\'' +
                '}';
    }
}
