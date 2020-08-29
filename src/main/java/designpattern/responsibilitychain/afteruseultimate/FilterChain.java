package designpattern.responsibilitychain.afteruseultimate;

import designpattern.responsibilitychain.afteruse.Study;
import designpattern.responsibilitychain.beforeuse.PreparationList;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-27
 */

public class FilterChain implements StudyPrepareFilter {
    private int pos = 0;

    private Study study;

    private List<StudyPrepareFilter> studyPrepareFilterList;

    public FilterChain(Study study) {
        this.study = study;
    }

    public void addFilter(StudyPrepareFilter studyPrepareFilter) {
        if (studyPrepareFilterList == null) {
            studyPrepareFilterList = new ArrayList<StudyPrepareFilter>();
        }

        studyPrepareFilterList.add(studyPrepareFilter);
    }

    @Override
    public void doFilter(PreparationList preparationList, FilterChain filterChain) {
        // 所有过滤器执行完毕
        if (pos == studyPrepareFilterList.size()) {
            study.study();
            return;
        }

        studyPrepareFilterList.get(pos++).doFilter(preparationList, filterChain);
    }
}
