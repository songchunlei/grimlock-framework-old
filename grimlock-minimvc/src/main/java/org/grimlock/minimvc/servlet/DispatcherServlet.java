package org.grimlock.minimvc.servlet;

import org.grimlock.minimvc.annotation.Controller;
import org.grimlock.minimvc.annotation.Qualifier;
import org.grimlock.minimvc.annotation.RequestMapping;
import org.grimlock.minimvc.annotation.Service;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 16:22 2018-1-4
 * @Modified By:
 */
public class DispatcherServlet extends HttpServlet {

    //发现的className
    private List<String> classNames = new ArrayList<>();

    //发现的bean要放到map中（类的实例）
    //Key是注解里的value值
    Map<String,Object> beans = new HashMap<>();

    //记录所有的请求路径
    Map<String,Object> handlerMap = new HashMap<>();

    public DispatcherServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected long getLastModified(HttpServletRequest req) {
        return super.getLastModified(req);
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doHead(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //比如http://127.0.0.1:8080/bank-oa-web/LoginController/login.do取到bank-oa-web/LoginController/login.do
        String uri = req.getRequestURI();
        //取到bank-oa-web
        String context = req.getContextPath();
        //截取掉上下文，取到LoginController/login.do
        String path = uri.replace(context,"");
        //在handlerMap取到方法
        Method method = (Method)handlerMap.get(path);
        //在beans里调用方法
        Object instance = beans.get("/"+path.split("/")[1]);
        try {
            method.invoke(instance,new Object[]{req,resp,this.getClass().getName()});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doTrace(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public String getInitParameter(String name) {
        return super.getInitParameter(name);
    }

    @Override
    public Enumeration getInitParameterNames() {
        return super.getInitParameterNames();
    }

    @Override
    public ServletConfig getServletConfig() {
        return super.getServletConfig();
    }

    @Override
    public ServletContext getServletContext() {
        return super.getServletContext();
    }

    @Override
    public String getServletInfo() {
        return super.getServletInfo();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //1.扫描子包和子包下的类
        scanPackage("org.grimlock.minimvc");

        for (String className:classNames) {
            System.out.println("class："+className);
        }

        //2. 扫描出来的类进行实例化
        instance();
        for (Map.Entry<String,Object> entry:beans.entrySet()) {
            System.out.println("beans："+entry.getKey()+":"+entry.getValue());
        }

        //3.依赖注入
        ioc();


        HandlerMapping();

        for (Map.Entry<String,Object> entry:handlerMap.entrySet()) {
            System.out.println("handlerMap："+entry.getKey()+":"+entry.getValue());
        }

        super.init(config);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void log(String msg) {
        super.log(msg);
    }

    @Override
    public void log(String message, Throwable t) {
        super.log(message, t);
    }

    @Override
    public String getServletName() {
        return super.getServletName();
    }

    private void scanPackage(String basePackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + replaceTo(basePackage));
        String fileStr = url.getFile();
        File file = new File(fileStr);
        //拿到文件夹下所有子文件
        String[] filesStr = file.list();
        for (String path:filesStr)
        {
            File filePath = new File(fileStr+path);
            if(filePath.isDirectory())
            {
                scanPackage(basePackage+"."+path);
            }
            else
            {
                classNames.add(basePackage+"."+filePath.getName());
            }
        }
    }

    private String replaceTo(String basePackage)
    {
        return basePackage.replace("\\.",".");
    }

    //实例化
    private void instance(){
        if(classNames.size()<=0)
        {
            System.out.println("包扫描失败");
            return;
        }

        for(String className : classNames){
            //真正能编译到的是class文件，而不是java文件
            String cn = className.replace(".class","");
            try {
                Class clazz = Class.forName(cn);
                //是否有注解存在
                if(clazz.isAnnotationPresent(Controller.class))
                {
                    Controller controller = (Controller) clazz.getAnnotation(Controller.class);
                    Object instance = clazz.newInstance();

                    RequestMapping requestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
                    String rmvalue = requestMapping.value();

                    beans.put(rmvalue,instance);

                }
                else if(clazz.isAnnotationPresent(Service.class))
                {
                    Service service = (Service) clazz.getAnnotation(Service.class);
                    Object instance = clazz.newInstance();
                    String rmvalue = service.value();
                    beans.put(rmvalue,instance);
                }
                else
                {
                    continue;
                }


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    //依赖注入
    private void ioc()
    {
        if(beans.entrySet().size()<=0)
        {
            System.out.println("没有类的实例化");
            return;
        }

        for (Map.Entry<String,Object> entry : beans.entrySet())
        {
            Object instance = entry.getValue();
            Class clazz = instance.getClass();

            if(clazz.isAnnotationPresent(Controller.class))
            {
                //拿到私有的
                //只有特别的FIELD才需要注入（有Qualifier注解）
               Field[] fields  = clazz.getDeclaredFields();
                for (Field field:fields) {
                    if(field.isAnnotationPresent(Qualifier.class))
                    {
                        Qualifier qualifier = field.getAnnotation(Qualifier.class);
                        String value = qualifier.value();

                        //使FIELD可写
                        field.setAccessible(true);
                        try {
                            field.set(instance,beans.get(value));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
                       
            }
            else
            {
                continue;
            }

        }
    }

    private void HandlerMapping()
    {
        if(beans.entrySet().size()<=0)
        {

        }

        for (Map.Entry<String,Object> entry : beans.entrySet())
        {

            Object instance = entry.getValue();
            Class clazz = instance.getClass();

            if(clazz.isAnnotationPresent(Controller.class))
            {
                RequestMapping requestMapping = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
                String classPath = requestMapping.value();
                //再去循环CLASS下所有的方法
                Method[] methods = clazz.getMethods();
                for (Method method:methods)
                {
                    if(method.isAnnotationPresent(RequestMapping.class))
                    {
                        RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
                        String methodPath = methodRequestMapping.value();
                        handlerMap.put(classPath+methodPath,method);
                    }
                    else
                    {
                        continue;
                    }
                }
            }
        }
    }
}
