package seedu.address.model.lesson.predicate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import seedu.address.model.module.ReadOnlyLesson;
import seedu.address.model.module.predicates.LessonContainsKeywordsPredicate;
import seedu.address.testutil.LessonBuilder;

//@@author angtianlannus
public class LessonContainsKeywordsPredicateTest {

    public static final ReadOnlyLesson LESSON = new LessonBuilder().build();
    public static final String ATTRIBUTE_MODULE = "module";
    public static final String ATTRIBUTE_LOCATION = "location";

    @Test
    public void equals() {

        List<String> keywordOne = new ArrayList<String>() {
        };
        List<String> keywordTwo = new ArrayList<String>() {
        };
        List<String> keywordNull = new ArrayList<String>() {
        };

        keywordOne.add("111");
        keywordOne.add("222");
        keywordOne.add("333");

        keywordTwo.add("aaa");
        keywordTwo.add("bbb");
        keywordTwo.add("bbb");

        LessonContainsKeywordsPredicate predicateOne = new LessonContainsKeywordsPredicate(keywordOne, LESSON,
                ATTRIBUTE_MODULE);
        LessonContainsKeywordsPredicate predicateTwo = new LessonContainsKeywordsPredicate(keywordTwo, LESSON,
                ATTRIBUTE_MODULE);
        LessonContainsKeywordsPredicate predicateNull = new LessonContainsKeywordsPredicate(keywordNull, LESSON,
                ATTRIBUTE_MODULE);

        // same object -> returns true
        assertTrue(predicateOne.equals(predicateOne));

        // same values -> returns true
        LessonContainsKeywordsPredicate predicateTestOne = new LessonContainsKeywordsPredicate(keywordOne,
                LESSON, ATTRIBUTE_MODULE);
        assertTrue(predicateOne.equals(predicateTestOne));

        // different types -> returns false
        assertFalse(predicateOne.equals(1));

        // null -> returns false
        assertFalse(predicateOne.equals(null));

        // different keywords -> returns false
        assertFalse(predicateOne.equals(predicateTwo));

        // one predicate with keywords compare with another predicate without keywords -> returns false
        assertFalse(predicateOne.equals(predicateNull));
    }

    @Test
    public void test_lessonAllAttributeContainsKeywordsForModule_returnsTrue() {
        // One keyword to find location
        LessonContainsKeywordsPredicate predicate = new LessonContainsKeywordsPredicate(Collections.singletonList(
                "LT27"), LESSON, ATTRIBUTE_MODULE);
        assertTrue(predicate.test(new LessonBuilder().withLocation("LT27").build()));

        // One keyword to find group
        predicate = new LessonContainsKeywordsPredicate(Collections.singletonList(
                "12"), LESSON, ATTRIBUTE_MODULE);
        assertTrue(predicate.test(new LessonBuilder().withGroup("12").build()));

        // One keyword to find class type
        predicate = new LessonContainsKeywordsPredicate(Collections.singletonList(
                "LEC"), LESSON, ATTRIBUTE_MODULE);
        assertTrue(predicate.test(new LessonBuilder().withClassType("LEC").build()));

        // One keyword to find time slot
        predicate = new LessonContainsKeywordsPredicate(Collections.singletonList(
                "MON[1200-1300]"), LESSON, ATTRIBUTE_MODULE);
        assertTrue(predicate.test(new LessonBuilder().withTimeSlot("MON[1200-1300]").build()));

        // Only one matching keyword to find location
        predicate = new LessonContainsKeywordsPredicate(Arrays.asList("LT26", "LT27"), LESSON,
                ATTRIBUTE_MODULE);
        assertTrue(predicate.test(new LessonBuilder().withLocation("LT27").build()));

        // Only one matching keyword to find group
        predicate = new LessonContainsKeywordsPredicate(Arrays.asList("1", "2"), LESSON, ATTRIBUTE_MODULE);
        assertTrue(predicate.test(new LessonBuilder().withGroup("1").build()));

        // Only one matching keyword to find class type
        predicate = new LessonContainsKeywordsPredicate(Arrays.asList("TUT", "LEC"), LESSON, ATTRIBUTE_MODULE);
        assertTrue(predicate.test(new LessonBuilder().withClassType("LEC").build()));

        // Only one matching keyword to find time slot
        predicate = new LessonContainsKeywordsPredicate(Arrays.asList("MON[1200-1300]", "TUE[0900-1000]"),
                LESSON, ATTRIBUTE_MODULE);
        assertTrue(predicate.test(new LessonBuilder().withTimeSlot("MON[1200-1300]").build()));

        // Mixed-case keywords to find location
        predicate = new LessonContainsKeywordsPredicate(Arrays.asList("Lt12"),
                LESSON, ATTRIBUTE_MODULE);
        assertTrue(predicate.test(new LessonBuilder().withLocation("LT12").build()));

        // Mixed-case keywords to find class type
        predicate = new LessonContainsKeywordsPredicate(Arrays.asList("LeC"),
                LESSON, ATTRIBUTE_MODULE);
        assertTrue(predicate.test(new LessonBuilder().withClassType("LEC").build()));

        // Mixed-case keywords to find time slot
        predicate = new LessonContainsKeywordsPredicate(Arrays.asList("mOn[1200-1300]"),
                LESSON, ATTRIBUTE_MODULE);
        assertTrue(predicate.test(new LessonBuilder().withTimeSlot("MON[1200-1300]").build()));

        // partial keywords that is a substring of the location that user intend to find
        predicate = new LessonContainsKeywordsPredicate(Arrays.asList("LT"), LESSON, ATTRIBUTE_MODULE);
        assertTrue(predicate.test(new LessonBuilder().withLocation("LT12").build()));

        // partial keywords that is a substring of the time slot that user intend to find
        predicate = new LessonContainsKeywordsPredicate(Arrays.asList("MON"), LESSON, ATTRIBUTE_MODULE);
        assertTrue(predicate.test(new LessonBuilder().withTimeSlot("MON[1200-1300]").build()));

        // partial keywords that is a substring of the class type that user intend to find
        predicate = new LessonContainsKeywordsPredicate(Arrays.asList("T"), LESSON, ATTRIBUTE_MODULE);
        assertTrue(predicate.test(new LessonBuilder().withClassType("TUT").build()));

    }

    @Test
    public void test_lessonAllAttributeContainsKeywordsForLocation_returnsTrue() {

        // One keyword to find group
        LessonContainsKeywordsPredicate predicate = new LessonContainsKeywordsPredicate(Collections.singletonList(
                "12"), LESSON, ATTRIBUTE_LOCATION);
        assertTrue(predicate.test(new LessonBuilder().withGroup("12").build()));

        // One keyword to find module
        predicate = new LessonContainsKeywordsPredicate(Collections.singletonList(
                "MA1101R"), LESSON, ATTRIBUTE_LOCATION);
        assertTrue(predicate.test(new LessonBuilder().withCode("MA1101R").build()));

        // One keyword to find class type
        predicate = new LessonContainsKeywordsPredicate(Collections.singletonList(
                "LEC"), LESSON, ATTRIBUTE_LOCATION);
        assertTrue(predicate.test(new LessonBuilder().withClassType("LEC").build()));

        // One keyword to find time slot
        predicate = new LessonContainsKeywordsPredicate(Collections.singletonList(
                "MON[1200-1300]"), LESSON, ATTRIBUTE_LOCATION);
        assertTrue(predicate.test(new LessonBuilder().withTimeSlot("MON[1200-1300]").build()));

        // Only one matching keyword to find module
        predicate = new LessonContainsKeywordsPredicate(Arrays.asList("MA1101R", "CS2200"), LESSON,
                ATTRIBUTE_LOCATION);
        assertTrue(predicate.test(new LessonBuilder().withCode("CS2200").build()));

        // Only one matching keyword to find group
        predicate = new LessonContainsKeywordsPredicate(Arrays.asList("1", "2"), LESSON, ATTRIBUTE_LOCATION);
        assertTrue(predicate.test(new LessonBuilder().withGroup("1").build()));

        // Only one matching keyword to find class type
        predicate = new LessonContainsKeywordsPredicate(Arrays.asList("TUT", "LEC"), LESSON, ATTRIBUTE_LOCATION);
        assertTrue(predicate.test(new LessonBuilder().withClassType("LEC").build()));

        // Only one matching keyword to find time slot
        predicate = new LessonContainsKeywordsPredicate(Arrays.asList("MON[1200-1300]", "TUE[0900-1000]"),
                LESSON, ATTRIBUTE_LOCATION);
        assertTrue(predicate.test(new LessonBuilder().withTimeSlot("MON[1200-1300]").build()));

        // Mixed-case keywords to find module
        predicate = new LessonContainsKeywordsPredicate(Arrays.asList("cs2200"),
                LESSON, ATTRIBUTE_LOCATION);
        assertTrue(predicate.test(new LessonBuilder().withCode("CS2200").build()));

        // Mixed-case keywords to find class type
        predicate = new LessonContainsKeywordsPredicate(Arrays.asList("LeC"),
                LESSON, ATTRIBUTE_LOCATION);
        assertTrue(predicate.test(new LessonBuilder().withClassType("LEC").build()));

        // Mixed-case keywords to find time slot
        predicate = new LessonContainsKeywordsPredicate(Arrays.asList("mOn[1200-1300]"),
                LESSON, ATTRIBUTE_LOCATION);
        assertTrue(predicate.test(new LessonBuilder().withTimeSlot("MON[1200-1300]").build()));

        // partial keywords that is a substring of the module that user intend to find
        predicate = new LessonContainsKeywordsPredicate(Arrays.asList("CS22"), LESSON, ATTRIBUTE_LOCATION);
        assertTrue(predicate.test(new LessonBuilder().withCode("CS2200").build()));

        // partial keywords that is a substring of the time slot that user intend to find
        predicate = new LessonContainsKeywordsPredicate(Arrays.asList("MON"), LESSON, ATTRIBUTE_LOCATION);
        assertTrue(predicate.test(new LessonBuilder().withTimeSlot("MON[1200-1300]").build()));

        // partial keywords that is a substring of the class type that user intend to find
        predicate = new LessonContainsKeywordsPredicate(Arrays.asList("T"), LESSON, ATTRIBUTE_LOCATION);
        assertTrue(predicate.test(new LessonBuilder().withClassType("TUT").build()));

    }

    @Test
    public void test_lessonAllAttributeDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        LessonContainsKeywordsPredicate predicate = new LessonContainsKeywordsPredicate(Collections.emptyList(),
                LESSON, ATTRIBUTE_MODULE);
        assertFalse(predicate.test(LESSON));

        // Non-matching keyword for all attributes
        predicate = new LessonContainsKeywordsPredicate(Arrays.asList("COM1"), LESSON, ATTRIBUTE_MODULE);
        assertFalse(predicate.test(LESSON));

    }

    @Test
    public void test_differentModuleCode_returnsFalse() {
        LessonContainsKeywordsPredicate predicate = new LessonContainsKeywordsPredicate(Arrays.asList("MON"),
                LESSON, ATTRIBUTE_MODULE);
        assertFalse(predicate.test(new LessonBuilder().withCode("CS2100").build()));
    }

    @Test
    public void test_differentLocationCode_returnsFalse() {
        LessonContainsKeywordsPredicate predicate = new LessonContainsKeywordsPredicate(Arrays.asList("MON"),
                LESSON, ATTRIBUTE_LOCATION);
        assertFalse(predicate.test(new LessonBuilder().withLocation("LT22").build()));
    }
}
