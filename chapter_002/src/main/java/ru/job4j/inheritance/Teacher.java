package ru.job4j.inheritance;

/**
 * Class Class describe teacher.
 * @author atrifonov
 * @since 17.07.2017
 * @version 1
 */
public class Teacher extends Profession {
    /**
     * The name of course.
     */
    private String CourseSpokenEnglish;

    /**
     * The kind of diploma.
     */
    private String DiplomaUniversityEducation;

    /**
     * The name of book.
     */
    private String BookStartEnglish;

    /**
     * Constructs a new Teacher object, that has the name.
     * @param name The name of teacher.
     */
    public Teacher(String name) {
        super(name);
    }

    /**
     * Describe of children teaching.
     * @param child A child that teaching.
     * @return The string describing the action of teacher. Teacher learn child.
     */
    public String learnChildren(Children child) {
        return "Учитель " + this.getName() + " учит " + child;
    }

    /**
     * Describe of course making.
     * @param course The name of course
     * @return The string describing the action of teacher. Teacher make course.
     */
    public String makeCourse(Course course) {
        return "Учитель " + this.getName() + " создает " + course;
    }

    /**
     * Describe of play teacher with children.
     * @param childOne First child.
     * @param childTwo Second child.
     * @param childThree Third child.
     * @return The string describing the action of teacher. Teacher play with children.
     */
    public String playWithChildren(Children childOne, Children childTwo, Children childThree) {
        return "Учитель " + this.getName() + " играет с детьми: " + childOne + " " + childTwo + " " + childThree;
    }

    /**
     * Describe of article making.
     * @param article The name of article.
     * @return The string describing the action of teacher. Teacher make article.
     */
    public String makeArticle(Article article) {
        return "Учитель " + this.getName() + " создаёт " + article;
    }
}
