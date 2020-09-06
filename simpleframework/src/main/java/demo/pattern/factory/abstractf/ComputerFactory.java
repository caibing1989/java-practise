package demo.pattern.factory.abstractf;

import demo.pattern.factory.entity.Keyboard;
import demo.pattern.factory.entity.Mouse;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-09-06
 */

public interface ComputerFactory {

    Mouse createMouse();

    Keyboard createKeyboard();
}
