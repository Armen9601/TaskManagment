//package listener;
//
//import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//@WebListener
//public class AutListener implements ServletContextListener {
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        ServletContextListener.super.contextInitialized(sce);
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        AbandonedConnectionCleanupThread.checkedShutdown();
//    }
//}
