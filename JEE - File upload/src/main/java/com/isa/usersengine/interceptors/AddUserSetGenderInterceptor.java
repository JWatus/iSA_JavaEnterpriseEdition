package com.isa.usersengine.interceptors;

import com.isa.usersengine.domain.Gender;
import com.isa.usersengine.domain.User;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

public class AddUserSetGenderInterceptor {

    Logger logger = Logger.getLogger(AddUserSetGenderInterceptor.class.getName());

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {

        Object[] parameters = context.getParameters(); // lista argumentow z ktorymi wywolana byla nasza obserwowana metoda
        for (Object parameter : parameters) {
            User user = (User) parameter;
            if (user.getName().endsWith("a")) {
                user.setGender(Gender.WOMAN);
            } else {
                user.setGender(Gender.MAN);
            }
            logger.info("Gender interceptor: Gender has been set to: " + user.getGender().toString());

        }
        context.setParameters(parameters);
        return context.proceed();

    }

}
