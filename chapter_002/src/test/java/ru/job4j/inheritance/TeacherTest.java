package ru.job4j.inheritance;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author atrifonov.
 * @since 17.07.2017.
 * @version 1.
 */
public class TeacherTest {
    @Test
    /*
     * Test learnChildren.
     */
    public void whenTeacherLearnVasjaThenStringAboutThis() {
        Teacher teacher = new Teacher("Maria Ivanovna");
        String teacherLearn = teacher.learnChildren("Vasja");
        String expected = "Учитель Maria Ivanovna учит Vasja";
        assertThat(teacherLearn, is(expected));
    }

    @Test
    /*
     * Test makeCourse.
     */
    public void whenTeacherMakeCourseStartEnglishThenStringAboutThis() {
        Teacher teacher = new Teacher("Maria Ivanovna");
        String teacherMakeCourse = teacher.makeCourse("Start English");
        String expected = "Учитель Maria Ivanovna создает Start English";
        assertThat(teacherMakeCourse, is(expected));
    }

    @Test
    /*
     * Test playWithChildren.
     */
    public void whenTeacherPlayWithChildrenThenStringAboutThis() {
        Teacher teacher = new Teacher("Maria Ivanovna");
        String teacherPlayWithChildren = teacher.playWithChildren("Vasja", "Zhora", "Ben");
        String expected = "Учитель Maria Ivanovna играет с детьми: Vasja Zhora Ben";
        assertThat(teacherPlayWithChildren, is(expected));
    }

    @Test
    /*
     * Test makeArticle.
     */
    public void whenTeacherMakeArticleThenStringAboutThis() {
        Teacher teacher = new Teacher("Maria Ivanovna");
        String teacherMakeArticle = teacher.makeArticle("About plays at lessons");
        String expected = "Учитель Maria Ivanovna создаёт About plays at lessons";
        assertThat(teacherMakeArticle, is(expected));
    }
}
