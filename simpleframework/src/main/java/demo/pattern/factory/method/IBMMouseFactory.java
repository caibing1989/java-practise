package demo.pattern.factory.method;

import demo.pattern.factory.entity.IBMMouse;
import demo.pattern.factory.entity.Mouse;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-09-06
 */

public class IBMMouseFactory extends HPMouseFactory {
    @Override
    public Mouse createMouse() {
        return new IBMMouse();
    }
}
