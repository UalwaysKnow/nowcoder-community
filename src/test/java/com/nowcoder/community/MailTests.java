package com.nowcoder.community;

import com.nowcoder.community.util.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTests {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testTextMail() {
        mailClient.sendMail("zzzyw526@outlook.com", "TEST", "welcome");
    }
//    public void testTextMail() {
//        mailClient.sendMail("juty112@126.com", "神秘邮件", "我也爱小屁宝");
//    }

    @Test
    public void testHtmlMail() {
        Context context = new Context();
        context.setVariable("username", "my only love");
        // 通过TemplateEngine 和Context 的配合，我们可以使用thymeleaf模版来生产html文件。
        // Context是给thymeleaf模版提供变量的
        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);

        mailClient.sendMail("juty112@126.com", "Sweet mail", content);
    }

}
