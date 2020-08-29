package cache.hole;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.*;

/**
 * @Description: 采用常用的布隆过滤器，将所有可能存在的数据哈希到一个足够大的bitmap中，
 * 一个一定不存在的数据会被这个bitmap拦截掉，从而避免了对底层存储系统的查询压力
 * 在访问所有资源(cache, DB)之前，将存在的key用布隆过滤器提前保存起来，做第一层拦截
 * @Author: mtdp
 * @Date: 2020-08-16
 */

public class CachePenetrationBloomFilter {

    private static final int insertions = 1000000;//100W

    public static void main(String[] args) {
        BloomFilter bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), insertions);
        Set sets = new HashSet(insertions);
        List<String> lists = new ArrayList(insertions);

        for (int i = 0; i < insertions; i++) {
            String uuid = UUID.randomUUID().toString();
            bf.put(uuid);
            sets.add(uuid);
            lists.add(uuid);
        }

        int wrong = 0;
        int right = 0;
        for (int i = 0; i < 10000; i++) {
            String test = i % 100 == 0 ? lists.get(i / 100) : UUID.randomUUID().toString();  //随机生成1W字符串
            if (bf.mightContain(test)) {
                if (sets.contains(test)) {
                    right++;
                } else {
                    wrong++;
                }
            }
        }

        System.out.println("========right=======" + right);
        System.out.println("========wrong=======" + wrong);
    }
}
