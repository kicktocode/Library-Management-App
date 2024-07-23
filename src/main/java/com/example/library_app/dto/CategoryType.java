package com.example.library_app.dto;

public enum CategoryType {
    RESEARCH_HISTORY("Research-History"),
    SCIENCE("Science"),
    COMIC("Comic"),
    CHILD_AND_YOUTH("Child and youth"),
    LESSON_TEST_BOOKS("Lesson and Test books"),
    RELIGION("Religion"),
    LITERATURE("Literature"),
    EDUCATION_REFERENCE("Education"),
    PHILOSOPHY("Philosophy"),
    FOREIGN_LANGUAGES("Foreign Languages"),
    HOBBY("Hobby"),
    HUMOR("Humor"),
    ART_DESIGN("Art Design"),
    AUDIO_BOOKS("Audio Books"),
    OTHER("Other");

    private final String value;
     CategoryType(String value)
     {
         this.value= value;
     }
     public String getValue()
     {
         return value;
     }

}
