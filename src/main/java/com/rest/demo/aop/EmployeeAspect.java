package com.rest.demo.aop;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import com.rest.demo.exception.EmployeeNotFoundException;

// @Before - before
// @After - after
// @AfterThrowing - after exception thrown
// @AfterReturning - After returning something 
// @Around - @Before + @After


@Aspect																// An Aspect 
@Configuration
public class EmployeeAspect {
	Logger logger = Logger. getLogger("EmployeeAspect");
	
	@Pointcut("execution(* com.rest.demo.controller.*.*(..))")		// Points where this Aspect will be provided with Advices
	public void repositoryClassMethods() {}
	
	// What kind of method calls I would intercept					// @Before,@After.....all are advices
	// execution(* PACKAGE.*.*(..))
	// Weaving & Weaver
//	@Before("repositoryClassMethods()")       				
	public void before(JoinPoint joinPoint) {
		//advice
		logger.info("controller got called - "+joinPoint.getSignature());
	}
	
//	@After("execution(* com.rest.demo.controller.*.*(..))")			//JointPoint is method which is executed
	public void after(JoinPoint joinPoint) {
		//advice
		logger.info("Employee returned successfully or exception is thrown - "+joinPoint.getSignature());
	}
	
	@Around("execution(* com.rest.demo.controller.*.*(..))")			//JointPoint is method which is executed
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		//advice
		logger.info("Before method execution - "+joinPoint.getSignature());
		joinPoint.proceed();
		logger.info("Employee returned successfully or exception is thrown - "+joinPoint.getSignature());
	}
	
//	@AfterReturning("execution(* com.rest.demo.controller.*.*(..))")
	public void afterReturning(JoinPoint joinPoint) {
		//advice
		logger.info("Employee returned successfully - "+joinPoint.getSignature());
	}
	
//	@AfterThrowing(pointcut = "execution(* com.rest.demo.controller.*.*(..))", throwing = "e" )
	public void afterThrowing(JoinPoint joinPoint) {
		//advice
		logger.info("Exception is thrown - " +joinPoint.getSignature());
	}
	
	
	
}