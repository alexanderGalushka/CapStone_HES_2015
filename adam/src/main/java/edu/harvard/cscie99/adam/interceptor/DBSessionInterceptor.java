//package edu.harvard.cscie99.adam.interceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
// 
//
//
//
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.context.internal.ManagedSessionContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
// 
//public class DBSessionInterceptor implements HandlerInterceptor  {
//	
//	@Autowired
//    private SessionFactory sessionFactory;
//	
//	
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//            HttpServletResponse response, Object handler) throws Exception {
//         
//        System.out.println("Pre-handle");
//        Session session = sessionFactory.openSession();
////        session.beginTransaction();
////        ManagedSessionContext.bind(session);
////        sessionFactory.getCurrentSession().getTransaction().begin();
//         
//        return true;
//    }
//    //override postHandle() and afterCompletion() 
//
//	@Override
//	public void afterCompletion(HttpServletRequest arg0,
//			HttpServletResponse arg1, Object arg2, Exception arg3)
//			throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println("After-handle");
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
//			Object arg2, ModelAndView arg3) throws Exception {
//		System.out.println("Post-handle");
////		sessionFactory.getCurrentSession().getTransaction().commit();
//		Session session = sessionFactory.getCurrentSession();
//		session.getTransaction().commit();
//		session.close();
////		ManagedSessionContext.unbind(sessionFactory);
//		
//	}
//}