package cache.guavacache;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

import java.util.Collections;
import java.util.List;


/**
 * @Description: 用于模拟当缓存中没有相关数据的时候，我们需要去数据库里或者其他文件中读取
 * @Author: mtdp
 * @Date: 2020-08-16
 */

public class MockDB {
    public static List getCityListFromDB(String cityId) {
        System.out.println("getting from DB,cityId:" + cityId + " please wait...");

        List<String> returnList = null;

        // 模仿从数据库中取数据
        try {
            switch (cityId) {
                case "0101":
                    returnList = ImmutableList.of("上海", "北京", "广州", "深圳");
                    break;
            }
        } catch (Exception e) {
            // 记日志
            System.out.println("Exception e = " + e);
        }

        return Optional.fromNullable(returnList).or(Collections.EMPTY_LIST);
    }
}
