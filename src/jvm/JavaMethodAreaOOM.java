//import java.lang.reflect.Method;
//import net.sf.cglib.proxy.MethodProxy;
//
///**
// * @author tangxinxing
// * @version 1.0
// * @description
// * @date 2024/8/9
// */
//public class JavaMethodAreaOOM {
//    public static void main(String[] args) {
//        while (true) {
//            Enhancer enhancer = new Enhancer();
//            enhancer.setSuperclass(JavaHeapOom.OOMObject.class);
//            enhancer.setUseCache(false);
//            enhancer.setCallback(new MethodInterceptor() {
//                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable return proxy.invokeSuper(obj, args);
//            });
//        }
//        enhancer.create();
//        }
//    }
//}
