package effectivejava.createanddestoryobj;



/**
 * @Description: 避免使用终结方法和清除方法
 * @Author: mtdp
 * @Date: 2020-09-26
 */

public class AvoidUseFinalizerAndCleaner {

}


/**
 * finalizer通常是不可预测的，也是很危险的，一般情况下是不必要的
 * 使用finalizer会导致行为不稳定、性能降低、以及可抑制性问题
 * 在java9中用cleaner代替了finalizer，cleaner没有finalizer那么危险
 * 但仍然是不可预测、运行缓慢、一般情况下也是不必要的
 * finalizer和cleaner的缺点在于不能保证会被及时执行
 * java语言规范不仅不保证finalizer或cleaner会被及时执行，而且根本就不保证它们会被执行
 * 使用finalizer的另一个问题是：如果忽略在finalizer过程中抛出来的未捕获的异常，该对象的终结过程也将终止
 * 因为终结方法阻止了有效的垃圾回收，导致非常严重的性能损失
 * 为了防止非final类受到终结方法攻击，要编写一个空的final的finalizer方法
 *
 * 如果类的对象中封装的资源（文件）确实需要终止，应该怎么做才能不用编写finalizer或者cleaner呢？
 * 只需要让类实现AutoCloseable，并要求客户端在每个实例不再需要的时候调用close方法
 * 一般是利用try-with-resources确保终止，即使遇到异常也是如此
 *
 * 那么finalizer和cleaner有什么好处呢？它们有两种合法用途：
 * 第一种用途是，当资源的所有者忘记调用它的close方法时，finalizer和cleaner可以充当安全网
 */