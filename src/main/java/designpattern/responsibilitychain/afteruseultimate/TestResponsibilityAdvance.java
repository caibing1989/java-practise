package designpattern.responsibilitychain.afteruseultimate;

import designpattern.responsibilitychain.afteruse.Study;
import designpattern.responsibilitychain.beforeuse.PreparationList;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-27
 */

public class TestResponsibilityAdvance {

    public static void main(String[] args) {
        PreparationList preparationList = new PreparationList();
        preparationList.setWashFace(true);
        preparationList.setWashHair(false);
        preparationList.setHaveBreakfast(true);

        Study study = new Study();

        StudyPrepareFilter washFaceFilter = new WashFaceFilter();
        StudyPrepareFilter washHairFilter = new WashHairFilter();
        StudyPrepareFilter haveBreakfastFilter = new HaveBreakfastFilter();

        FilterChain filterChain = new FilterChain(study);
        filterChain.addFilter(washFaceFilter);
        filterChain.addFilter(washHairFilter);
        filterChain.addFilter(haveBreakfastFilter);

        filterChain.doFilter(preparationList, filterChain);
    }
}

/**
 * FilterChain里面有studyPrepareFilterList，我们完全可以把FilterChain做成一个Spring Bean，
 * 所有的Filter具体实现类也都是Spring Bean，注入studyPrepareFilterList就好了
 *
 * 1 <bean id="filterChain" class="xxx.xxx.xxx.FilterChain">
 * 2     <property name="studyPrepareFilterList">
 * 3         <list>
 * 4             <ref bean="washFaceFilter" />
 * 5             <ref bean="washHairFilter" />
 * 6             <ref bean="haveBreakfastFilter" />
 * 7         </list>
 * 8     </property>
 * 9 </bean>
 */
