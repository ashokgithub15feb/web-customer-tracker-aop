package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	//setup logger
	private Logger logger = Logger.getLogger(getClass().getName());
	
	//setup pointcut declaration
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	public void forControllerPackage()
	{
	}

	//do the same for service and also
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	public void forDaoPackage()
	{
	}

	
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	public void forServicePackage()
	{
	}
	
	@Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
	private void forAppFlow()
	{
	}
	
	//add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint)
	{
		 String shortString = joinPoint.getSignature().toShortString();
		 
		 logger.info("===========>>>>>>>>>> in @Before: calling method: "+shortString);
		 
		 //display the argument to the method
		 
		 Object[] args = joinPoint.getArgs();
		 //get the argument
		 
		 for(Object ar: args)
		 {
			 logger.info("===========>>>>>>>>>> in @Before: Argument: "+ar);
		 }
		 
	}
	
	//add @AfterReturning advice
	
	@AfterReturning(
				pointcut = "forAppFlow()",
				returning = "result"
			)
	public void afterReturning(JoinPoint joinPoint, Object result)
	{
		//display method we are returning form
		String shortString = joinPoint.getSignature().toShortString();
		 
		logger.info("===========>>>>>>>>>> in @AfterReturning: calling method: "+shortString);
		 
		
		//display data returnded
		
		logger.info("===========>>>>>>>>>> in @AfterReturning: Result: "+result);
	}
	
}
