package services;

import java.util.Arrays;
import java.util.List;

public class DataGenerator {

    // Generate a random unique email address
    public static String generateEmail(Integer length){
        StringBuilder email = new StringBuilder();
        for(int i=0; i<=length;i++){
            email.append((char) Math.floor(Math.random()*(122-97)+97));
        }
        return email + "@test.com";
    }

    // Generate a random pair of names
    public static String generateName(String gender){
        List<String> femaleNames, maleNames = Arrays.asList(
                "Dean", "Kevin", "Anderson", "Jack", "Hughes","Isaac","Newton", "Dennis", "Albert", "Joseph", "Daniel","Bernard");

        femaleNames = Arrays.asList(
                "Lizzie", "Laura", "Emily", "Brianna", "Heather", "Denise", "Amanda", "Gillian", "Danielle", "Ivana", "Monica", "Irene");

        return (gender.equalsIgnoreCase("male"))?
                maleNames.get((int)Math.floor(Math.random()*maleNames.size()))+" "+maleNames.get((int)Math.floor(Math.random()*maleNames.size())):
                ((gender.equalsIgnoreCase("female"))?
                femaleNames.get((int)Math.floor(Math.random()*femaleNames.size()))+" "+femaleNames.get((int)Math.floor(Math.random()*femaleNames.size())):
                null);
    }
}
