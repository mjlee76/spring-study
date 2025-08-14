package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //AOP 어노테이션
@Component //Spring Bean 등록(SpringConfig에 직접 등록도 가능)
public class TimeTraceAop {

    @Around("execution(* hello.hello_spring..*(..))") //공통관심사항(여기선 실행시간)을 어디에 적용할지
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            //다음 메서드로 진행
            /*Object result = joinPoint.proceed();
            return result;*/
            return joinPoint.proceed();//inline 형식으로 변환
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");

        }
    }

}
