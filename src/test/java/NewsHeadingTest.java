import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsHeadingTest {
    List<String> newsHeadingsList = null;
    List<Integer> scorePoints = null;
    NewsHeadingWithMaximumPoints headings = new NewsHeadingWithMaximumPoints();
    Map<String, Integer> sampleTestData = null;
    String mostOccuredWord = "";

    @BeforeMethod
    public void setUp() {
        mostOccuredWord = "is";
        newsHeadingsList = new ArrayList();
        scorePoints = new ArrayList<>();
        sampleTestData = new HashMap<>();

        newsHeadingsList.add("Apple is best fruit");
        newsHeadingsList.add("Rajesh is very good boy");
        newsHeadingsList.add("Computer is the most smart device");
        scorePoints.add(20);
        scorePoints.add(24);
        scorePoints.add(34);
        sampleTestData.put("Apple is best fruit", 20);
        sampleTestData.put("Rajesh is very good boy", 64);
        sampleTestData.put("Computer is the most smart device", 34);


    }

    @Test
    public void givenListOfWordsWhenNotNull_ThenShouldReturnNumberOfWords() {
        List<String> listOfWords = headings.listOfWords(newsHeadingsList);
        Assert.assertEquals(15, listOfWords.size());
    }

    @Test
    public void givenSentenceMapAndMostOccuredWord_WhenCorrect_ThenReturnPopularSentenceAccordingToScorePoint() {
        String popularNewsHeading = headings.getPopularNewsHeading(sampleTestData, mostOccuredWord);
        Assert.assertEquals("Rajesh is very good boy",popularNewsHeading);
    }
}