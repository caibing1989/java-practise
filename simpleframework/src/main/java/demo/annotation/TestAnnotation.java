package demo.annotation;


/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-09-06
 */

@CourseInfoAnnotation(courseName = "SQL高级技能", courseTag = "培训", courseProfile = "第二阶段的SQL教学")
public class TestAnnotation {

    @PersonInfoAnnotation(name = "caibing", language = {"sql", "java"})
    private String author;

    @CourseInfoAnnotation(courseName = "Java基础技能", courseTag = "培训", courseProfile = "第一阶段的Java教学", courseIndex = 102)
    public void getCourseInfo() {

    }

    public static void main(String[] args) {
        TestAnnotation testAnnotation = new TestAnnotation();
        testAnnotation.getCourseInfo();
        System.out.println("finish");
    }
}
